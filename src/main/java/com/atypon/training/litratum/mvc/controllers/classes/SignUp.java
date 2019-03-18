package com.atypon.training.litratum.mvc.controllers.classes;

import com.atypon.training.litratum.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUp implements ActionInterface {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getMethod());
        resp.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/SignUp.jsp");
        dispatcher.forward(req,resp);
//        resp.sendRedirect(Constants.RELATIVE_PATH + "jsp/SignUp.jsp");
    }

}
