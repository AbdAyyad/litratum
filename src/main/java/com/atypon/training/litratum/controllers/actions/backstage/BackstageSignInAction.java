package com.atypon.training.litratum.controllers.actions.backstage;

import com.atypon.training.litratum.controllers.actions.Action;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.model.database.daos.BackstageAdminDao;
import com.atypon.training.litratum.model.database.daos.UserDao;
import com.atypon.training.litratum.model.database.datatypes.BackStageAdminModel;
import com.atypon.training.litratum.model.database.datatypes.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BackstageSignInAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        String email = req.getParameter("backstageEmail");
        String password = req.getParameter("backstagePassword");

        UserDao userDao = new UserDao();
        BackstageAdminDao backstageAdminDao = new BackstageAdminDao();

        UserModel user = userDao.getUserByEmail(email);
        BackStageAdminModel admin = backstageAdminDao.getBackstageAdminByUserId(user.getUserId());

        boolean isBackstageAdminVerified = user.verifyPassword(password) && admin != null;

        RequestDispatcher dispatcher;
        if (isBackstageAdminVerified) {
            HttpSession session = req.getSession();

            session.setMaxInactiveInterval(7200);
            session.setAttribute("backstageAdminEmail", email);
            session.setAttribute("backstageAdminId", admin.getBackStageAdminId());
            session.setAttribute("backstageAdminLoggedIn", true);

            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.BACKSTAGE_HOME_PAGE);
        }
        dispatcher.forward(req, resp);
    }
}
