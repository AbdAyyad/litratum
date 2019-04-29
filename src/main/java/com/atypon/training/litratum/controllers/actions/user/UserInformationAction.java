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

public class UserInformationAction implements ActionInterface {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        String email = req.getParameter("userEmail");
        boolean flag = Authenticator.isLoggedIn(email);
        RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
        if (flag) {
            UserDao dao = new UserDao();
            User user = dao.getUserByEmail(email);
            req.setAttribute("userName", user.getUserEmail());
            req.setAttribute("user", user);
            req.setAttribute("license","not implemented");
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.HOME_PAGE);
        }
        dispatcher.forward(req, resp);
    }
}
