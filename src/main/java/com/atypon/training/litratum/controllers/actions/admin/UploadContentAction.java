package com.atypon.training.litratum.controllers.actions.admin;

import com.atypon.training.litratum.controllers.actions.Action;
import com.atypon.training.litratum.controllers.tools.Constants;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.controllers.tools.RandomGenerator;
import com.atypon.training.litratum.model.database.daos.UnprocessedContentDao;
import com.atypon.training.litratum.model.database.datatypes.UnprocessedContentModel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class UploadContentAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher;
            HttpSession session = req.getSession();
            boolean adminIsLoggedIn = (Boolean) session.getAttribute("loggedInAdmin");
            if (adminIsLoggedIn) {
                String adminId = (String) session.getAttribute("adminId");
                FileItem file = decodeRequest(req).get(0);
                writeFile(file);
                addToDataBase(file.getName(), adminId);
                dispatcher = req.getRequestDispatcher(jsp);
            } else {
                dispatcher = req.getRequestDispatcher(JspPath.ADMIN_HOME_PAGE);
            }
            dispatcher.forward(req, resp);
        } catch (Exception e) {
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

    private void addToDataBase(String fileName, String adminId) {
        UnprocessedContentDao dao = new UnprocessedContentDao();
        String unprocessedId = RandomGenerator.getRandomString(64);
        String timeStamp = LocalDate.now().toString();
        UnprocessedContentModel content = new UnprocessedContentModel(unprocessedId, fileName, adminId, 0, timeStamp);
        dao.addEntry(content);
    }

    private void writeFile(FileItem fileItem) throws Exception {
        String fileName = Constants.ZIPPED_FOLDER + fileItem.getName();
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
        factory.setSizeThreshold(100000);
        File file = new File(fileName);
        File repo = new File(fileName.substring(0, fileName.length() - 4));
        factory.setRepository(repo);


        //uploadedFile.createNewFile();
        fileItem.write(file);

        if (repo.exists()) {
            repo.delete();
        }
    }
}
