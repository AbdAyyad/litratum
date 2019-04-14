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
        String[] directories = new File(directory).list();
        System.out.println(Arrays.toString(directories));

    }

}
