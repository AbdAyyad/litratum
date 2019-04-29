package com.atypon.training.litratum.controllers.actions.user;


import com.atypon.training.litratum.controllers.actions.ActionInterface;
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
        UserDao dao = new UserDao();
        boolean flag;
        RequestDispatcher dispatcher;
        User user = dao.getUserByEmail(email);
        flag = user.verifyPassword(password);

        if (flag) {
            dispatcher = req.getRequestDispatcher(jsp);
            user.setLoggedIn(true);
            req.setAttribute("userName", user.getUserName());
            req.setAttribute("userEmail", user.getUserEmail());
            dao.editEntry(user);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.HOME_PAGE);
        }

        dispatcher.forward(req, resp);
    }

}
