package com.atypon.training.litratum.controllers.actions;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NotFoundAction implements ActionInterface {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/NotFoundAction.jsp");
        dispatcher.forward(req, resp);
    }
}
