package com.atypon.training.litratum.mvc.controllers.tools;

import com.atypon.training.litratum.mvc.controllers.actions.ActionInterface;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NotFound implements ActionInterface {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp, String args) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/NotFound.jsp");
        dispatcher.forward(req, resp);
    }
}
