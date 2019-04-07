package com.atypon.training.litratum.mvc.controllers.classes;

public class ContentProcessing implements Runnable {
    String zipFileName;

    public ContentProcessing(String zipFileName) {
        this.zipFileName = zipFileName;
    }

    @Override
    public void run() {
        String fileName = zipFileName;
        String directory = fileName.substring(0, fileName.length() - 4);
        Compressor.unZip(fileName, directory);
    }

}
