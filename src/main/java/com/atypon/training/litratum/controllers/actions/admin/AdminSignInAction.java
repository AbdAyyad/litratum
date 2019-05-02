package com.atypon.training.litratum.controllers.actions.admin;

import com.atypon.training.litratum.controllers.actions.ActionInterface;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.model.database.daos.AdminDao;
import com.atypon.training.litratum.model.database.daos.UserDao;
import com.atypon.training.litratum.model.database.datatypes.Admin;
import com.atypon.training.litratum.model.database.datatypes.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminSignInAction implements ActionInterface {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        String email = req.getParameter("adminEmail");
        String password = req.getParameter("adminPassword");
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        UserDao userDao = new UserDao();
        AdminDao adminDao = new AdminDao();
        User user = userDao.getUserByEmail(email);
        Admin admin = adminDao.getAdminByUserId(user.getUserId());
        boolean adminVerified = user.verifyPassword(password) && admin != null;

        if (adminVerified) {
            session.setAttribute("adminEmail", email);
            session.setAttribute("adminId", admin.getAdminId());
            session.setAttribute("loggedInAdmin", true);
            session.setMaxInactiveInterval(7200);
            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.ADMIN_HOME_PAGE);
        }

        dispatcher.forward(req, resp);
    }
}
