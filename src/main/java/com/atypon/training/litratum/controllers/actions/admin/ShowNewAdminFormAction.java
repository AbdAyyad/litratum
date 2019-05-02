package com.atypon.training.litratum.controllers.actions.admin;

import com.atypon.training.litratum.controllers.actions.Action;
import com.atypon.training.litratum.controllers.tools.LiteratumDispatchter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowNewAdminFormAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        LiteratumDispatchter.dispatchAdminRequest(req, resp, jsp);
    }
}
