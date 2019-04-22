package com.atypon.training.litratum.mvc.controllers.actions;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInAction implements ActionInterface {


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp,String args) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/SignInForm.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp,String args) throws ServletException, IOException {
    }

}
