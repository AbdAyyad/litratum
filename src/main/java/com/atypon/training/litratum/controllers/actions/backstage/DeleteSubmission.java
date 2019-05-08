package com.atypon.training.litratum.controllers.actions.backstage;

import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.Constants;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.model.database.daos.implementations.ArticleMetaDao;
import com.atypon.training.litratum.model.database.daos.implementations.UnprocessedContentDao;
import com.atypon.training.litratum.model.database.daos.interfaces.IArticleMetaDao;
import com.atypon.training.litratum.model.database.daos.interfaces.IUnprocessedContentDao;
import com.atypon.training.litratum.model.database.datamodel.ArticleMetaModel;
import com.atypon.training.litratum.model.database.datamodel.UnprocessedContentModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DeleteSubmission implements IAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();
        Object sessionAttr = session.getAttribute("backstageAdminLoggedIn");
        boolean isAdminLoggedIn = sessionAttr == null ? false : (Boolean) sessionAttr;

        if (isAdminLoggedIn) {
            String contentId = req.getParameter("unprocessedContentId");
            updateUI(contentId, session);
            deleteUnprocessed(contentId);
            deleteCached(contentId);
            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.BACKSTAGE_HOME_PAGE);
        }
        dispatcher.forward(req, resp);
    }

    private void deleteFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    private void deleteCached(String unprocessedId) throws IOException {
        IArticleMetaDao dao = new ArticleMetaDao();
        ArticleMetaModel meta = dao.getByUnprocessedId(unprocessedId);
        dao.delete(unprocessedId);
        String path = Constants.PROCESSED_FOLDER + meta.getDoi() + ".html";
        deleteFile(path);
    }

    private void deleteUnprocessed(String unprocessedId) throws IOException {
        IUnprocessedContentDao dao = new UnprocessedContentDao();
        UnprocessedContentModel content = dao.getById(unprocessedId);

        String path = Constants.ZIPPED_FOLDER + content.getFileName();
        deleteFile(path);

        dao.delete(unprocessedId);

    }

    private void updateUI(String unprocessedId, HttpSession session) {
        IUnprocessedContentDao dao = new UnprocessedContentDao();
        UnprocessedContentModel content = dao.getById(unprocessedId);

        List<UnprocessedContentModel> data = (List<UnprocessedContentModel>) session.getAttribute("unprocessedContents");
        data.remove(content);

        session.setAttribute("unprocessedContents", data);

    }
}
