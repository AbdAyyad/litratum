package com.atypon.training.litratum.controllers.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
        //get the zip file content
        ZipInputStream zis;
//get the zipped file list entry
        ZipEntry ze;
        zis = new ZipInputStream(new FileInputStream(filePath));
        ze = zis.getNextEntry();

        while (ze != null) {
            String fileName = ze.getName();
            File newFile = new File(outputDirectoryPath + File.separator + fileName);

//create all non exists folders
//else you will hit FileNotFoundException for compressed folder
            new File(newFile.getParent()).mkdirs();

            FileOutputStream fos = new FileOutputStream(newFile);

            int len;
            byte[] buffer = new byte[1024];
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }

            fos.close();

            ze = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();
    }
}
