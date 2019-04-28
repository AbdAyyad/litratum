package com.atypon.training.litratum.controllers.actions;

import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.model.database.daos.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeLoggedInAction implements ActionInterface {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        String email = req.getParameter("userEmail");
        UserDao dao = new UserDao();

        boolean flag = dao.isLoggedIn(email);
        RequestDispatcher dispatcher;

        if (flag) {
            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.HOME_PAGE);
        }

        dispatcher.forward(req, resp);
    }
}
