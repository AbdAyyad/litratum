package com.atypon.training.litratum.mvc.controllers.classes;

import com.atypon.training.litratum.mvc.model.database.ConnectionPool;
import com.atypon.training.litratum.mvc.model.database.daos.UnprocessedDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class BackStage implements ActionInterface {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, String args) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("id");
        resp.setContentType("text/html");
        out.println("start processing" + id);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp, String args) throws ServletException, IOException {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        UnprocessedDao dao = new UnprocessedDao(pool.getConnection());
        req.setAttribute("datalist", dao.getAll());
        resp.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/backstage/Backstage.jsp");
        dispatcher.forward(req, resp);
    }

}
