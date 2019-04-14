package com.atypon.training.litratum.mvc.controllers.classes;

import com.atypon.training.litratum.Constants;

import java.io.File;
import java.util.Arrays;

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

        System.out.println(Arrays.toString(getDirectoryContents(xmlDirectory)));

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

}
