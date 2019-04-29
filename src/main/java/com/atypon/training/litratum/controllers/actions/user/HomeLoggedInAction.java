package com.atypon.training.litratum.controllers.actions.user;

import com.atypon.training.litratum.controllers.actions.ActionInterface;
import com.atypon.training.litratum.controllers.tools.Authenticator;
import com.atypon.training.litratum.controllers.tools.JspPath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeLoggedInAction implements ActionInterface {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        String email = req.getParameter("userEmail");

        boolean flag = Authenticator.isLoggedIn(email);
        RequestDispatcher dispatcher;

        if (flag) {
            req.setAttribute("userEmail", email);
            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.HOME_PAGE);
        }

        dispatcher.forward(req, resp);
    }
}
