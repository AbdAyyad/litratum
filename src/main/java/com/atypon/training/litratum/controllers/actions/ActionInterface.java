package com.atypon.training.litratum.controllers.actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ActionInterface {
    default void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        switch (req.getMethod()) {
            case "GET":
                doGet(req, resp, jsp);
                break;
            case "POST":
                doPost(req, resp, jsp);
                break;
            case "PUT":
                doPut(req, resp, jsp);
                break;
        }
    }

    default void doGet(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
    }

    default void doPost(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
    }

    default void doPut(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
    }

}
