package com.atypon.training.litratum.mvc.controllers.classes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignIn implements ActionInterface {

    public SignIn() {
    }


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("inside sign in class");
    }

}
