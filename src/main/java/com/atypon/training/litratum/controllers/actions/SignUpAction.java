package com.atypon.training.litratum.controllers.actions;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.atypon.training.litratum.model.database.daos.Dao;
import com.atypon.training.litratum.model.database.daos.NormalUserDao;
import com.atypon.training.litratum.model.database.daos.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpAction implements ActionInterface {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp, String args) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/SignUpForm.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, String args) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String userName = req.getParameter("userName");
        String password = req.getParameter("userPassword");
        String email = req.getParameter("userEmail");

        char[] bcryptChars = BCrypt.withDefaults().hashToChar(12, password.toCharArray());

        Dao userDao = new UserDao();
        Dao normalDao = new NormalUserDao();
//        NormalUser normalUser = new NormalUser()
        //        dao.addEntry(user);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/WelcomeMessage.jsp");
//        dispatcher.forward(req, resp);
    }
}
