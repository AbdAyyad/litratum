package com.atypon.training.litratum.controllers.actions.backstage;

import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.Constants;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.model.database.daos.implementations.UnprocessedContentDao;
import com.atypon.training.litratum.model.database.daos.interfaces.IUnprocessedContentDao;
import com.atypon.training.litratum.model.database.datamodel.UnprocessedContentModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DeleteSubmission implements IAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();
        Object sessionAttr = session.getAttribute("backstageAdminLoggedIn");
        boolean isAdminLoggedIn = sessionAttr == null ? false : (Boolean) sessionAttr;

        if (isAdminLoggedIn) {
            IUnprocessedContentDao dao = new UnprocessedContentDao();
            String contentId = req.getParameter("unprocessedContentId");
            UnprocessedContentModel content = dao.getById(contentId);

            String path = Constants.ZIPPED_FOLDER + content.getFileName();
            deleteFile(path);

            dao.delete(contentId);

            List<UnprocessedContentModel> data = (List<UnprocessedContentModel>) session.getAttribute("unprocessedContents");
            data.remove(content);

            session.setAttribute("unprocessedContents", data);
            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.BACKSTAGE_HOME_PAGE);
        }
        dispatcher.forward(req, resp);
    }

    private void deleteFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if(Files.exists(path)){
            Files.delete(path);
        }
    }
}
