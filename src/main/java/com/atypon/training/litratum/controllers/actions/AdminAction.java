package com.atypon.training.litratum.controllers.actions;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class AdminAction implements ActionInterface {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, String args) throws ServletException, IOException {
        switch (args) {
            case "content":
                contentPostRequest(req, resp);
                break;
            case "users":
                usersPostRequest(req, resp);
                break;
            default:
                noArgsPostRequest(req, resp);
        }
    }

    private void contentPostRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writeFile(req);
        resp.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/ContentUploaded.jsp");
        dispatcher.forward(req, resp);
    }

    private void usersPostRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    private void noArgsPostRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        usersPostRequest(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp, String args) throws ServletException, IOException {
        switch (args) {
            case "content":
                contentGetRequest(req, resp);
                break;
            case "users":
                usersGetRequest(req, resp);
                break;
            default:
                noArgsGetRequest(req, resp);
        }
    }


    private void contentGetRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/ContentForm.jsp");
        dispatcher.forward(req, resp);
    }

    private void usersGetRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ConnectionPool pool = ConnectionPool.getConnectionPool();
//        UserDao dao = new UserDao(pool.getConnection());
//        List<Object> users = dao.getAll();
//        req.setAttribute("users", users);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/AdminAction.jsp");
//        dispatcher.forward(req, resp);
    }

    private void noArgsGetRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        usersGetRequest(req, resp);
    }


    private void writeFile(HttpServletRequest req) {
        try {
            writeFileWithException(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeFileWithException(HttpServletRequest req) throws Exception {
//        String fileName = Constants.UNPROCESSED_FOLDER + "zipped/" + RandomGenerator.getRandomString() + ".zip";
//        Dao dao = new UnprocessedDao(ConnectionPool.getConnectionPool().getConnection());
//        int idx = fileName.lastIndexOf('/');
//        dao.addEntry(fileName.substring(idx + 1));

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
//        factory.setSizeThreshold(100000);
//        File file = new File(fileName);
//        File repo = new File(fileName.substring(0, fileName.length() - 4));
//        factory.setRepository(repo);

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(10000000000L);

        // Parse the request
        List<FileItem> items = upload.parseRequest(req);
        // Process the uploaded items
        Iterator<FileItem> iter = items.iterator();

        //uploadedFile.createNewFile();
//        iter.next().write(file);
//
//        if (repo.exists()) {
//            repo.delete();
//        }
    }
}
