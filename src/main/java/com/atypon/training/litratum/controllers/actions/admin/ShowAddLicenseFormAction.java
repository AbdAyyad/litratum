package com.atypon.training.litratum.controllers.actions.admin;

import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.LiteratumDispatchter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowAddLicenseFormAction implements IAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        HttpSession session = req.getSession();
        session.setAttribute("updatedUserId", userId);
        LiteratumDispatchter.dispatchAdminRequest(req, resp, jsp);
    }

}
