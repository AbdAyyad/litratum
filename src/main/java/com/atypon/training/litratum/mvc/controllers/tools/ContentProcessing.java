package com.atypon.training.litratum.mvc.controllers.tools;

import com.atypon.training.litratum.Constants;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ContentProcessing implements Runnable {
    private String zipFileName;

    public ContentProcessing(String zipFileName) {
        this.zipFileName = zipFileName;
    }

    @Override
    public void run() {
        String fileName = Constants.ZIPPED_FOLDER + zipFileName;

        int idx = fileName.lastIndexOf('/');

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

        replaceDtd(xmlPath);

    }

    private void replaceDtd(String xmlPath) {
        try {
            replaceDtdWithException(xmlPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void replaceDtdWithException(String xmlPath) throws IOException {
        StringBuilder str = new StringBuilder();
        str.append(readFile(xmlPath));
        int jatIdx = str.indexOf("JATS-archivearticle1.dtd");
        str.insert(jatIdx, Constants.XSL_PATH);
        Files.delete(Paths.get(xmlPath));
        writeFile(xmlPath, str.toString());
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

    private String readFile(String path) {
        StringBuilder str = new StringBuilder();
        String line;

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            while ((line = bf.readLine()) != null) {
                str.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str.toString();
    }

    private void writeFile(String path, String content) {
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)))) {
            out.write(content);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
