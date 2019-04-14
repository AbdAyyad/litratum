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

    private static void createDirectory(String directory) throws IOException {
        StringBuilder str = new StringBuilder();
        String[] pieces = directory.split("/");
        for (int i = 0; i + 1 < pieces.length; ++i) {
            str.append(pieces[i]);
            str.append('/');
            Path path = Paths.get(str.toString());
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }

        }
    }

    private static void unZipWithException(String filePath, String outputDirectoryPath) throws IOException {
        ZipFile zipFile = new ZipFile(filePath);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        byte[] buffer = new byte[512];
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            createDirectory(outputDirectoryPath + "/" + entry.toString());
            FileOutputStream outputStream = new FileOutputStream(outputDirectoryPath + "/" + entry.toString());
            InputStream in = zipFile.getInputStream(entry);
            while (0 < in.available()) {
                int read = in.read(buffer);
                outputStream.write(buffer, 0, read);
            }
            outputStream.close();
            in.close();
        }
        Path path = Paths.get(filePath.substring(0, filePath.length() - 4));
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }
}
