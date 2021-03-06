package com.atypon.training.litratum.controllers.actions;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOutAction implements IAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("isLoggedInUser", false);
        session.setAttribute("isLoggedInAdmin", false);
        session.setAttribute("isLoggedInBackstage", false);
        RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
        session.invalidate();
        dispatcher.forward(req, resp);
    }
}
