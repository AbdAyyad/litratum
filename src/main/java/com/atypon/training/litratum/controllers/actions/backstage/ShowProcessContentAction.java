package com.atypon.training.litratum.controllers.actions.backstage;

import com.atypon.training.litratum.controllers.actions.IAction;
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
import java.util.List;

public class ShowProcessContentAction implements IAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();
        Object sessionAttr = session.getAttribute("backstageAdminLoggedIn");
        boolean isAdminLoggedIn = sessionAttr == null ? false : (Boolean) sessionAttr;

        if (isAdminLoggedIn) {
            IUnprocessedContentDao dao = new UnprocessedContentDao();
            List<UnprocessedContentModel> content = dao.getAll();
            session.setAttribute("unprocessedContents", content);
            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.BACKSTAGE_HOME_PAGE);
        }
        dispatcher.forward(req, resp);
    }
}
