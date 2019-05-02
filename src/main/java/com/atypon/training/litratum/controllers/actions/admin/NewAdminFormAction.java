package com.atypon.training.litratum.controllers.actions.admin;

import com.atypon.training.litratum.controllers.actions.ActionInterface;
import com.atypon.training.litratum.controllers.tools.LiteratumDispatchter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewAdminFormAction implements ActionInterface {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        LiteratumDispatchter.dispatchAdminReequest(req, resp, jsp);
    }
}
