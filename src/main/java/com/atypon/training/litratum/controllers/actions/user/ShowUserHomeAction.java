package com.atypon.training.litratum.controllers.actions.user;

import com.atypon.training.litratum.controllers.actions.Action;
import com.atypon.training.litratum.controllers.tools.JspPath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowUserHomeAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher;

        boolean loggedInUser = (Boolean) session.getAttribute("loggedInUser");

        if (loggedInUser) {
            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.HOME_PAGE);
        }

        dispatcher.forward(req, resp);
    }
}
