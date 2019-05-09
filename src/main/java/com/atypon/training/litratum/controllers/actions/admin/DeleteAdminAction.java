package com.atypon.training.litratum.controllers.actions.admin;

import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.model.database.daos.implementations.AdminDao;
import com.atypon.training.litratum.model.database.daos.implementations.UserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.ISubUserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.IUserDao;
import com.atypon.training.litratum.model.database.datamodel.AdminModel;
import com.atypon.training.litratum.model.database.datamodel.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DeleteAdminAction implements IAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Object sessionAttr = session.getAttribute("loggedInAdmin");
        boolean adminIsLoggedIn = sessionAttr == null ? false : (Boolean) sessionAttr;
        if (adminIsLoggedIn) {
            IUserDao userDao = new UserDao();
            ISubUserDao<AdminModel> adminDao = new AdminDao();

            String userId = req.getParameter("userId");

            userDao.delete(userId);
            adminDao.delete(userId);
            removeAdminFromUI(session, userId);

            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.ADMIN_HOME_PAGE);
        }
        dispatcher.forward(req, resp);
    }

    private void removeAdminFromUI(HttpSession session, String userId) {
        List<UserModel> users = (List<UserModel>) session.getAttribute("admins");
        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getUserId().equals(userId)) {
                users.remove(i);
                break;
            }
        }
        session.setAttribute("admins", users);
    }
}
