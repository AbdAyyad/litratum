package com.atypon.training.litratum.controllers.tools;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LiteratumDispatchter {
    private LiteratumDispatchter() {
    }

    public static void dispatchAdminReequest(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher;
        boolean flag = (Boolean) session.getAttribute("adminLoggedIn");
        if (flag) {
            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.ADMIN_HOME_PAGE);
        }
        dispatcher.forward(req, resp);
    }
}
