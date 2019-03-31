package com.atypon.training.litratum.mvc.controllers.classes;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipClass {
    public void undone() throws Exception {
        String zippedFile = "/home/aayyad/Desktop/aaaa.zip";
        String outputFolder = "/home/aayyad/Desktop/ol";

        ZipFile zipFile = new ZipFile(zippedFile);
        //ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(outputFolder));

        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        System.out.println(entries);
        byte[] buffer = new byte[512];
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (!entry.isDirectory()) {
                FileOutputStream outputStream = new FileOutputStream(outputFolder + "/" + entry.toString());
                //outputStream.putNextEntry(entry);
                InputStream in = zipFile.getInputStream(entry);
                while (0 < in.available()) {
                    int read = in.read(buffer);
                    outputStream.write(buffer, 0, read);
                }
                outputStream.close();
                in.close();
                System.out.println(entry.getName());
            } else {
                Path path = Paths.get(outputFolder + "/" + entry.toString());
                Files.createDirectory(path);
            }
        }
    }
}
