package com.atypon.training.litratum.controllers.actions.admin;

import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.model.database.daos.implementations.NormalUserDao;
import com.atypon.training.litratum.model.database.daos.implementations.UserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.INormalUserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.IUserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteUserAction implements IAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Object sessionAttr = session.getAttribute("loggedInAdmin");
        boolean adminIsLoggedIn = sessionAttr == null ? false : (Boolean) sessionAttr;
        RequestDispatcher dispatcher;
        if (adminIsLoggedIn) {
            String userId = req.getParameter("userId");
            String email = req.getParameter("email");

            INormalUserDao normalUserDao = new NormalUserDao();
            IUserDao userDao = new UserDao();

            normalUserDao.delete(userId);
            userDao.delete(userId);

            session.setAttribute("deletedUserEmail", email);
            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.ADMIN_HOME_PAGE);
        }
        dispatcher.forward(req, resp);
    }
}
