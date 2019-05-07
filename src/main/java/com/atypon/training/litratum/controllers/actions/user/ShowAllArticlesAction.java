package com.atypon.training.litratum.controllers.actions.user;

import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.model.database.daos.implementations.ArticleMetaDao;
import com.atypon.training.litratum.model.database.daos.interfaces.IArticleMetaDao;
import com.atypon.training.litratum.model.database.datamodel.ArticleMetaModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowAllArticlesAction implements IAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object sessionAttr = session.getAttribute("loggedInUser");
        boolean loggedInUser = sessionAttr == null ? false : (Boolean) sessionAttr;
        RequestDispatcher dispatcher;
        if (loggedInUser) {
            IArticleMetaDao dao = new ArticleMetaDao();
            List<ArticleMetaModel> data = dao.getAll();
            session.setAttribute("articles", data);
            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.HOME_PAGE);
        }
        dispatcher.forward(req, resp);
    }
}
