package com.atypon.training.litratum.controllers.actions.admin;

import com.atypon.training.litratum.controllers.actions.ActionInterface;
import com.atypon.training.litratum.controllers.tools.Authenticator;
import com.atypon.training.litratum.controllers.tools.JspPath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminSignInAction implements ActionInterface {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        String email = req.getParameter("adminEmail");
        String password = req.getParameter("adminPassword");

        RequestDispatcher dispatcher;

        Authenticator.signIn(email, password);
        boolean flag = Authenticator.isLoggedInAdmin(email);

        if (flag) {
            req.setAttribute("adminEmail", email);
            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.ADMIN_HOME_PAGE);
        }

        dispatcher.forward(req, resp);
    }
}
