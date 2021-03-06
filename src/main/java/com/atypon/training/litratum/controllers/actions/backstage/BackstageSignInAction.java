package com.atypon.training.litratum.controllers.actions.backstage;

import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.model.database.daos.implementations.BackstageAdminDao;
import com.atypon.training.litratum.model.database.daos.implementations.UserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.ISubUserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.IUserDao;
import com.atypon.training.litratum.model.database.datamodel.BackStageAdminModel;
import com.atypon.training.litratum.model.database.datamodel.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BackstageSignInAction implements IAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        String email = req.getParameter("backstageEmail");
        String password = req.getParameter("backstagePassword");

        IUserDao userDao = new UserDao();
        ISubUserDao<BackStageAdminModel> backstageAdminDao = new BackstageAdminDao();

        UserModel user = userDao.getByEmail(email);
        BackStageAdminModel admin = backstageAdminDao.getByUserId(user.getUserId());

        boolean isBackstageAdminVerified = user.verifyPassword(password) && admin != null;

        if (isBackstageAdminVerified) {
            HttpSession session = req.getSession();

            session.setMaxInactiveInterval(7200);
            session.setAttribute("backstageAdminEmail", email);
            session.setAttribute("backstageAdminId", admin.getBackStageAdminId());
            session.setAttribute("backstageAdminLoggedIn", true);

            resp.sendRedirect("/backstage/content");

        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(JspPath.BACKSTAGE_HOME_PAGE);
            dispatcher.forward(req, resp);
        }
    }
}
