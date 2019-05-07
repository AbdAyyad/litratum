package com.atypon.training.litratum.controllers.actions.user;

import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.Constants;
import com.atypon.training.litratum.controllers.tools.JspPath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowSingleArticleAction implements IAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object sessionAttr = session.getAttribute("loggedInUser");
        boolean loggedInUser = sessionAttr == null ? false : (Boolean) sessionAttr;
        RequestDispatcher dispatcher;
        if (loggedInUser) {
            String doi = req.getParameter("doi");
            String path = Constants.PROCESSED_FOLDER + doi + ".html";
            session.setAttribute("article", path);
            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.HOME_PAGE);
        }
        dispatcher.forward(req, resp);
    }
}
