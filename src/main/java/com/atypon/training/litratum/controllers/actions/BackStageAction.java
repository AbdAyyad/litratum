package com.atypon.training.litratum.controllers.actions;

import com.atypon.training.litratum.model.database.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BackStageAction {


    public void doPost(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        int id = Integer.valueOf(req.getParameter("id"));
        resp.setContentType("text/html");
        ConnectionPool pool = ConnectionPool.getConnectionPool();
//        Dao dao = new UnprocessedDao(pool.getConnection());
//        UnprocessedContent content = (UnprocessedContent) dao.getEntry(id);
//        out.println("start processing" + content.getFileName());
//        Runnable task = new ContentProcessing(content.getFileName());

        // for debugging purpose
//        task.run();

        //ThreadPool threadPool = ThreadPool.getInstance();
        //threadPool.execute(task);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
  //      UnprocessedDao dao = new UnprocessedDao(pool.getConnection());
//        req.setAttribute("datalist", dao.getAll());
//        resp.setContentType("text/html;charset=UTF-8");
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/backstage/Backstage.jsp");
//        dispatcher.forward(req, resp);
    }
}
