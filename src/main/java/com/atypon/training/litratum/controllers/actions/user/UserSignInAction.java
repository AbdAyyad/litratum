package com.atypon.training.litratum.controllers.actions.user;


import com.atypon.training.litratum.controllers.actions.ActionInterface;
import com.atypon.training.litratum.controllers.tools.Authenticator;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.model.database.daos.UserDao;
import com.atypon.training.litratum.model.database.datatypes.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserSignInAction implements ActionInterface {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        String email = req.getParameter("userEmail");
        String password = req.getParameter("userPassword");
        boolean flag;
        RequestDispatcher dispatcher;

        Authenticator.signIn(email, password);
        flag = Authenticator.isLoggedInUser(email);

        if (flag) {
            UserDao dao = new UserDao();
            User user = dao.getUserByEmail(email);
            dispatcher = req.getRequestDispatcher(jsp);
            req.setAttribute("userName", user.getUserName());
            req.setAttribute("userEmail", user.getUserEmail());
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.HOME_PAGE);
        }

        dispatcher.forward(req, resp);
    }

}
