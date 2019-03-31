package com.atypon.training.litratum.mvc.controllers.classes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ActionInterface {
    default void execute(HttpServletRequest req, HttpServletResponse resp,String args)throws ServletException, IOException {
        switch (req.getMethod()) {
            case "GET":
                doGet(req, resp,args);
                break;
            case "POST":
                doPost(req, resp,args);
                break;
            case "PUT":
                doPut(req, resp,args);
                break;
        }
    }

    default void doGet(HttpServletRequest req, HttpServletResponse resp,String args)throws ServletException, IOException {}
    default void doPost(HttpServletRequest req, HttpServletResponse resp,String args)throws ServletException, IOException {}
    default void doPut(HttpServletRequest req, HttpServletResponse resp,String args)throws ServletException, IOException {}

}
