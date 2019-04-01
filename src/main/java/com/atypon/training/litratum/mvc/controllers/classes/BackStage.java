package com.atypon.training.litratum.mvc.controllers.classes;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BackStage implements ActionInterface {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, String args) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("inside backstage");
        try {
            writeFile(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        InputStream inputStream = req.getInputStream();
//        BufferedInputStream bf = new BufferedInputStream(inputStream);
//        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File("/home/aayyad/Desktop/file.zip")));
//        byte[] buffer = new byte[512];
//        while (bf.available() > 0) {
//            bf.read(buffer, 0, 512);
//            System.out.println(Arrays.toString(buffer));
//            outputStream.write(buffer);
//        }
//        outputStream.flush();
//        bf.close();
//        outputStream.close();
    }

    private void writeFile(HttpServletRequest req) throws Exception {
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        System.out.println(isMultipart);
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
        factory.setSizeThreshold(100000);
        factory.setRepository(new File("/home/aayyad/Desktop/ol"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(10000000000L);

        // Parse the request
        List<FileItem> items = upload.parseRequest(req);
        // Process the uploaded items
        Iterator<FileItem> iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = iter.next();

            if (item.isFormField()) {
                //processFormField(item);
            } else {
                File uploadedFile = new File("/home/aayyad/Desktop/file.zip");
                item.write(uploadedFile);
            }
        }

    }
}
