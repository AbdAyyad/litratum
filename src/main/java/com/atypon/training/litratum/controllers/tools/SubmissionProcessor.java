package com.atypon.training.litratum.controllers.tools;

import com.atypon.training.litratum.model.database.daos.implementations.ArticleMetaDao;
import com.atypon.training.litratum.model.database.daos.implementations.UnprocessedContentDao;
import com.atypon.training.litratum.model.database.daos.interfaces.IArticleMetaDao;
import com.atypon.training.litratum.model.database.daos.interfaces.IUnprocessedContentDao;
import com.atypon.training.litratum.model.database.datamodel.ArticleMetaModel;
import com.atypon.training.litratum.model.database.datamodel.UnprocessedContentModel;
import com.atypon.training.litratum.model.xml.XmlFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class SubmissionProcessor implements Runnable {
    private String unprocessedContentId;

    public SubmissionProcessor(String unprocessedContentId) {
        this.unprocessedContentId = unprocessedContentId;
    }

    @Override
    public void run() {
        IUnprocessedContentDao dao = new UnprocessedContentDao();
        UnprocessedContentModel model = dao.getById(unprocessedContentId);

        String fileName = Constants.ZIPPED_FOLDER + model.getFileName();
        int idx = fileName.lastIndexOf(File.separator);
        String directory = Constants.UNZIPPED_FOLDER + fileName.substring(idx + 1, fileName.length() - 4);
        Compressor.unZip(fileName, directory);

        String xmlDirectory = getXmlFolderPath(directory);
        String[] directories = getDirectoryContents(xmlDirectory);
        String xmlPath = xmlDirectory;

        if (directories[0].endsWith("xml")) {
            xmlPath += directories[0];
        } else {
            xmlPath += directories[1];
        }

        fixDtd(xmlDirectory);
        ArticleMetaModel meta = getMeta(xmlPath);
        addMetaToDataBase(meta);
        generateHtml(xmlPath, meta.getDoi());
        dao.updateStatus(unprocessedContentId, 3);
        cleanUnZipped(directory);
    }

    private void fixDtd(String directory) {
        try {
            Path source = Paths.get(Constants.DTD_FILE);
            Path destination = Paths.get(directory + "JATS-archivearticle1.dtd");
            Files.copy(source, destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String[] getDirectoryContents(String path) {
        return new File(path).list();
    }

    private String getXmlFolderPath(String unzippedPath) {
        String[] directories = getDirectoryContents(unzippedPath);
        StringBuilder builder = new StringBuilder(unzippedPath);

        builder.append('/');


        if (directories[0].equals("manifest.xml")) {
            builder.append(directories[1]);
        } else {
            builder.append(directories[0]);
        }

        builder.append('/');

        directories = getDirectoryContents(builder.toString());

        if (directories[0].equals("issue-files")) {
            builder.append(directories[1]);
        } else {
            builder.append(directories[0]);
        }
        builder.append('/');

        return builder.toString();
    }

    private void addMetaToDataBase(ArticleMetaModel meta) {
        IArticleMetaDao metaDao = new ArticleMetaDao();
        metaDao.add(meta);
        metaDao.setUnprocessedId(meta.getDoi(), unprocessedContentId);
    }

    private ArticleMetaModel getMeta(String xmlPath) {
        ArticleMetaModel model = null;
        try {
            String xmlTransformed = XmlTransformer.getXml(xmlPath, Constants.ARTICLE_META_XSL_FILE);
            XmlFactory factory = new XmlFactory(xmlTransformed);
            model = factory.getArticleMeta();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    private void generateHtml(String xmlPath, String doi) {
        try {
            String xmlTransformed = XmlTransformer.getXml(xmlPath, Constants.ARTICLE_HTML_XSL_FILE);
            String fileName = Constants.PROCESSED_FOLDER + doi + ".html";
            File file = new File(fileName);
            new File(file.getParent()).mkdirs();
            PrintWriter out = new PrintWriter(new FileWriter(file));
            out.println(xmlTransformed.substring(38));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cleanUnZipped(String directory) {
        try {
            Path path = Paths.get(directory);
            Files.walk(path)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
