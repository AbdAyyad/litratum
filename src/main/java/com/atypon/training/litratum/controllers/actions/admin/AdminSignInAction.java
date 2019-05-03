package com.atypon.training.litratum.controllers.actions.admin;

import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.model.database.daos.implementations.AdminDao;
import com.atypon.training.litratum.model.database.daos.implementations.UserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.ISubUserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.IUserDao;
import com.atypon.training.litratum.model.database.datamodel.AdminModel;
import com.atypon.training.litratum.model.database.datamodel.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminSignInAction implements IAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        String email = req.getParameter("adminEmail");
        String password = req.getParameter("adminPassword");
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        IUserDao userDao = new UserDao();
        ISubUserDao<AdminModel> adminDao = new AdminDao();

        UserModel user = userDao.getByEmail(email);
        AdminModel admin = adminDao.getByUserId(user.getUserId());
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
