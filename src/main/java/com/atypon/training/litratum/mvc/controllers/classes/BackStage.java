package com.atypon.training.litratum.mvc.controllers.classes;

import com.atypon.training.litratum.Constants;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

public class BackStage implements ActionInterface {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, String args) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("inside backstage");
        writeFile(req);
    }

    private void writeFile(HttpServletRequest req) {
        try {
            writeFileWithException(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeFileWithException(HttpServletRequest req) throws Exception {
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
        factory.setSizeThreshold(100000);
        File file = new File(Constants.UNPROCESSED_FOLDER + RandomGenerator.getRandomFileName()+".zip");
        factory.setRepository(file);

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(10000000000L);

        // Parse the request
        List<FileItem> items = upload.parseRequest(req);
        // Process the uploaded items
        Iterator<FileItem> iter = items.iterator();

        //uploadedFile.createNewFile();
        iter.next().write(file);
    }
}
