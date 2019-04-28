package com.atypon.training.litratum.controllers.actions;

import com.atypon.training.litratum.model.database.daos.UserDao;
import com.atypon.training.litratum.model.database.datatypes.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignOutAction implements ActionInterface {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        String email = req.getParameter("userEmail");
        UserDao dao = new UserDao();
        User user = dao.getUserByEmail(email);
        user.setLoggedIn(false);
        dao.editEntry(user);
        RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
        dispatcher.forward(req, resp);
    }
}
