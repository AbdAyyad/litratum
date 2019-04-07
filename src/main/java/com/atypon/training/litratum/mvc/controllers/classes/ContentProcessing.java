package com.atypon.training.litratum.mvc.controllers.classes;

import com.atypon.training.litratum.Constants;

public class ContentProcessing implements Runnable {
    private String zipFileName;

    public ContentProcessing(String zipFileName) {
        this.zipFileName = zipFileName;
    }

    @Override
    public void run() {
        String fileName = zipFileName;

        int idx = fileName.lastIndexOf('/');

        String directory = Constants.UNZIPPED_FOLDER + fileName.substring(idx);
        Compressor.unZip(fileName, directory);
    }

}
