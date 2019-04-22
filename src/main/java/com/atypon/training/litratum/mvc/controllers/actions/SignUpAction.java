package com.atypon.training.litratum.mvc.controllers.actions;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.atypon.training.litratum.mvc.model.database.ConnectionPool;

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

        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        char[] bcryptChars = BCrypt.withDefaults().hashToChar(12, password.toCharArray());

//        ConnectionPool pool = ConnectionPool.getConnectionPool();
//        UserDao dao = new UserDao(pool.getConnection());
//        UserAction user = new UserAction(0, userName, String.copyValueOf(bcryptChars), email);
//        dao.addEntry(user);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/WelcomeMessage.jsp");
//        dispatcher.forward(req, resp);
    }
}
