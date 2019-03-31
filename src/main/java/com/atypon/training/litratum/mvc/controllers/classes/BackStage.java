package com.atypon.training.litratum.mvc.controllers.classes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BackStage implements ActionInterface {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, String args) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("inside backstage");
    }
}
