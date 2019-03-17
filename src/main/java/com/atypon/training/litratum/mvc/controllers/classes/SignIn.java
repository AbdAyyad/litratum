package com.atypon.training.litratum.mvc.controllers.classes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class SignIn implements ActionInterface {

    public SignIn() {
    }


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            executeWithException(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void executeWithException(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("inside sign in class");
    }
}
