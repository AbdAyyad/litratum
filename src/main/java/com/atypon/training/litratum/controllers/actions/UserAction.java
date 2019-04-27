package com.atypon.training.litratum.controllers.actions;

import com.atypon.training.litratum.controllers.tools.JspPath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAction implements ActionInterface {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        switch (jsp) {
            case "info":
                showInfoPage(req, resp);
                break;
            case "welcome":
            default:
                showHomePage(req, resp);
        }
    }

    private void showHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(JspPath.HOME_PAGE);
        dispatcher.forward(req, resp);
    }

    private void showInfoPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(JspPath.INFO_PAGE);
        dispatcher.forward(req, resp);
    }
}
