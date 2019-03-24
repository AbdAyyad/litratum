package com.atypon.training.litratum.mvc.controllers.classes;


import com.atypon.training.litratum.mvc.model.database.ConnectionPool;
import com.atypon.training.litratum.mvc.model.database.User;
import com.atypon.training.litratum.mvc.model.database.daos.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements ActionInterface {


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp,String args) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/SignInForm.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp,String args) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        UserDao dao = new UserDao(pool.getConnection());
        User user = dao.getUser(userName);
        boolean result = user.verifyPassword(password);
        HttpSession session = req.getSession();
        if (result) {
            session.setAttribute("sign_in", true);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/SignInComplete.jsp");
            dispatcher.forward(req, resp);
        } else {
            session.setAttribute("sign_in", false);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/SignInForm.jsp");
            dispatcher.forward(req, resp);
        }
    }

}
