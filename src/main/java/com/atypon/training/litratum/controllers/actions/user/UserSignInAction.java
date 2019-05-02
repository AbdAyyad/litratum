package com.atypon.training.litratum.controllers.actions.user;


import com.atypon.training.litratum.controllers.actions.ActionInterface;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.model.database.daos.UserDao;
import com.atypon.training.litratum.model.database.datatypes.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserSignInAction implements ActionInterface {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        String email = req.getParameter("userEmail");
        String password = req.getParameter("userPassword");
        RequestDispatcher dispatcher;

        UserDao dao = new UserDao();
        User user = dao.getUserByEmail(email);
        boolean userIsVerified = user.verifyPassword(password);

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
