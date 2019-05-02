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

public class ShowUserInformationAction implements ActionInterface {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        boolean loggedInUser = (Boolean) session.getAttribute("loggedInUser");
        RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
        if (loggedInUser) {
            String email = (String) session.getAttribute("userEmail");
            UserDao dao = new UserDao();
            User user = dao.getUserByEmail(email);
            session.setAttribute("userName", user.getUserEmail());
            session.setAttribute("user", user);
            session.setAttribute("license", "not implemented");
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.HOME_PAGE);
        }
        dispatcher.forward(req, resp);
    }
}
