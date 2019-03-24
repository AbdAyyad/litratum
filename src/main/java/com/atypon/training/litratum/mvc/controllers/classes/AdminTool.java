package com.atypon.training.litratum.mvc.controllers.classes;

import com.atypon.training.litratum.mvc.model.database.ConnectionPool;
import com.atypon.training.litratum.mvc.model.database.User;
import com.atypon.training.litratum.mvc.model.database.daos.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminTool implements ActionInterface {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        UserDao dao = new UserDao(pool.getConnection());
        List<User> users = dao.getAllUsers();

        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/AdminTool.jsp");
        dispatcher.forward(req, resp);
    }
}
