package com.atypon.training.litratum.controllers.actions.user;


import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.model.database.daos.implementations.NormalUserDao;
import com.atypon.training.litratum.model.database.daos.implementations.UserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.INormalUserDao;
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

        IUserDao dao = new UserDao();
        INormalUserDao normalDao = new NormalUserDao();

        UserModel user = dao.getByEmail(email);
        NormalUserModel normalUser = normalDao.getByUserId(user.getUserId());
        boolean userIsVerified = user.verifyPassword(password) && normalUser != null;

        if (userIsVerified) {
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(7200);
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("userEmail", user.getUserEmail());
            session.setAttribute("loggedInUser", true);

            resp.sendRedirect("/articles/");
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(JspPath.HOME_PAGE);
            dispatcher.forward(req, resp);
        }
    }
}
