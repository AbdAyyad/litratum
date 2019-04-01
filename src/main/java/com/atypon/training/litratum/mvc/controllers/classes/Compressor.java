package com.atypon.training.litratum.mvc.controllers.classes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Compressor {
    private Compressor() {
    }

    public static void unZip(String filePath, String outputDirectoryPath) {
        try {
            unZipWithException(filePath, outputDirectoryPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void unZipWithException(String filePath, String outputDirectoryPath) throws IOException {
        ZipFile zipFile = new ZipFile(filePath);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        byte[] buffer = new byte[512];
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (!entry.isDirectory()) {
                FileOutputStream outputStream = new FileOutputStream(outputDirectoryPath + "/" + entry.toString());
                InputStream in = zipFile.getInputStream(entry);
                while (0 < in.available()) {
                    int read = in.read(buffer);
                    outputStream.write(buffer, 0, read);
                }
                outputStream.close();
                in.close();
            } else {
                Path path = Paths.get(outputDirectoryPath + "/" + entry.toString());
                Files.createDirectory(path);
            }
        }
    }
}
