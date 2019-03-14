package com.atypon.training.litratum.mvc.controllers;

import com.atypon.training.litratum.database.daos.UserDao;
import com.atypon.training.litratum.mvc.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "front Servlet", urlPatterns = {"/front"})
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User("abd","12345678");
        PrintWriter out = resp.getWriter();
        out.println(user.toString());
        UserDao dao = UserDao.getInstance();
        dao.addUser(user);
    }
}
