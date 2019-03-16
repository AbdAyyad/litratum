package com.atypon.training.litratum.mvc.controllers.servlets;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.atypon.training.litratum.Constants;
import com.atypon.training.litratum.mvc.controllers.database.ConnectionPool;
import com.atypon.training.litratum.mvc.controllers.database.daos.UserDao;
import com.atypon.training.litratum.mvc.model.database.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Map;


@WebServlet(name = "front Servlet", urlPatterns = {"/front"})
public class FrontController extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        Constants.RELATIVE_PATH = this.getServletContext().getRealPath("");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ConnectionPool pool = ConnectionPool.getConnectionPool();
        UserDao dao = new UserDao(pool.getConnection());
        User user = dao.getUser("abd");
        PrintWriter out = resp.getWriter();
        out.println(user.toString());
        out.println(user.verifyPassword("12345678"));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()
        ) {
            System.out.println(entry.getKey());
            System.out.println(Arrays.toString(entry.getValue()));
        }
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        password = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        User user = new User(username, password);
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        UserDao dao = new UserDao(pool.getConnection());
        dao.addUser(user);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println(user.toString());
    }
}
