package com.atypon.training.litratum.mvc.controllers.classes;

import com.atypon.training.litratum.Constants;
import com.atypon.training.litratum.mvc.model.database.ConnectionPool;
import com.atypon.training.litratum.mvc.model.database.daos.Dao;
import com.atypon.training.litratum.mvc.model.database.daos.UnprocessedDao;
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
        String fileName = Constants.UNPROCESSED_FOLDER + "zipped/" + RandomGenerator.getRandomFileName() + ".zip";
        Dao dao = new UnprocessedDao(ConnectionPool.getConnectionPool().getConnection());
        int idx = fileName.lastIndexOf('/');
        dao.addEntry(fileName.substring(idx + 1));

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
        factory.setSizeThreshold(100000);
        File file = new File(fileName);
        factory.setRepository(new File(fileName.substring(0, fileName.length() - 4)));

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

        Thread th = new Thread(new ContentProcessing(fileName));
        th.run();
    }
}
