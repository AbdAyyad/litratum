package com.atypon.training.litratum.controllers.actions.admin;

import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.model.database.daos.implementations.NormalUserDao;
import com.atypon.training.litratum.model.database.daos.implementations.UserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.INormalUserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.IUserDao;
import com.atypon.training.litratum.model.database.datamodel.NormalUserModel;
import com.atypon.training.litratum.model.database.datamodel.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowAllUsersAction implements IAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Object sessionAttr = session.getAttribute("loggedInAdmin");
        boolean adminIsLoggedIn = sessionAttr == null ? false : (Boolean) sessionAttr;
        if (adminIsLoggedIn) {
            updateUI(session);
            dispatcher = req.getRequestDispatcher(jsp);

        } else {
            dispatcher = req.getRequestDispatcher(JspPath.ADMIN_HOME_PAGE);
        }
        dispatcher.forward(req, resp);
    }

    private void updateUI(HttpSession session) {
        INormalUserDao normalUserDao = new NormalUserDao();
        IUserDao userDao = new UserDao();

        List<NormalUserModel> normals = normalUserDao.selectAll();
        List<String> license = new ArrayList<>(normals.size());
        List<UserModel> users = new ArrayList<>(normals.size());

        for (NormalUserModel model : normals) {
            String licenseId = model.getLicenseId();
            String userId = model.getUserId();
            if (licenseId == null) {
                license.add("no-license");
            } else {
                license.add(licenseId);
            }

            users.add(userDao.getById(userId));
        }

        session.setAttribute("users", users);
        session.setAttribute("license", license);
    }
}
