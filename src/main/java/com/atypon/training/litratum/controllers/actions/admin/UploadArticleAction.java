package com.atypon.training.litratum.controllers.actions.admin;

import com.atypon.training.litratum.controllers.actions.ActionInterface;
import com.atypon.training.litratum.controllers.tools.Authenticator;
import com.atypon.training.litratum.controllers.tools.Constants;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.controllers.tools.RandomGenerator;
import com.atypon.training.litratum.model.database.daos.AdminDao;
import com.atypon.training.litratum.model.database.daos.UnprocessedContentDao;
import com.atypon.training.litratum.model.database.datatypes.Admin;
import com.atypon.training.litratum.model.database.datatypes.UnprocessedContent;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

/*
all code should be after parsing request
* */

public class UploadArticleAction implements ActionInterface {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        // Process the uploaded items
        try {
            Iterator<FileItem> iter = decodeRequest(req).iterator();
        } catch (FileUploadException e) {
            e.printStackTrace();
        }


    }

    private List<FileItem> decodeRequest(HttpServletRequest req) throws FileUploadException {
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(10000000000L);
        // Parse the request
        return upload.parseRequest(req);
    }

    private void writeFileWithException(HttpServletRequest req)  {
        String fileName = Constants.UNPROCESSED_FOLDER + "zipped/" + RandomGenerator.getRandomString() + ".zip";
        UnprocessedContentDao dao = new UnprocessedContentDao();
        int idx = fileName.lastIndexOf('/');
        String unprocessedId = RandomGenerator.getRandomString(64);
        AdminDao adminDao = new AdminDao();
        String adminEmail = req.getParameter("adminEmail");
        Admin admin = adminDao.getAdminByEmail(adminEmail);
        String timeStamp = LocalDate.now().toString();
        UnprocessedContent content = new UnprocessedContent(unprocessedId, fileName.substring(idx + 1), admin.getAdminId(), 0, timeStamp);
        dao.addEntry(content);


        // Set factory constraints
//        factory.setSizeThreshold(100000);
//        File file = new File(fileName);
//        File repo = new File(fileName.substring(0, fileName.length() - 4));
//        factory.setRepository(repo);

        // Create a new file upload handler


        //uploadedFile.createNewFile();
//        iter.next().write(file);
//
//        if (repo.exists()) {
//            repo.delete();
//        }
    }

}
