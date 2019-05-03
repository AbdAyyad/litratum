package com.atypon.training.litratum.controllers.actions.user;


import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.model.database.daos.implementations.NormalUserDao;
import com.atypon.training.litratum.model.database.daos.implementations.UserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.INormalUser;
import com.atypon.training.litratum.model.database.daos.interfaces.IUserDao;
import com.atypon.training.litratum.model.database.datamodel.NormalUserModel;
import com.atypon.training.litratum.model.database.datamodel.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserSignInAction implements IAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        String email = req.getParameter("userEmail");
        String password = req.getParameter("userPassword");
        RequestDispatcher dispatcher;

        IUserDao dao = new UserDao();
        INormalUser normalDao = new NormalUserDao();

        UserModel user = dao.getByEmail(email);
        NormalUserModel normalUser = normalDao.getByUserId(user.getUserId());
        boolean userIsVerified = user.verifyPassword(password) && normalUser != null;

        if (userIsVerified) {
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(7200);
            dispatcher = req.getRequestDispatcher(jsp);
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("userEmail", user.getUserEmail());
            session.setAttribute("loggedInUser", true);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.HOME_PAGE);
        }
        dispatcher.forward(req, resp);
    }
}
