package com.atypon.training.litratum.controllers.actions.admin;

import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.model.database.daos.implementations.LicenseDao;
import com.atypon.training.litratum.model.database.daos.implementations.NormalUserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.ILicenseDao;
import com.atypon.training.litratum.model.database.daos.interfaces.INormalUserDao;
import com.atypon.training.litratum.model.database.datamodel.LicenseModel;
import com.atypon.training.litratum.model.database.datamodel.NormalUserModel;

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
        ILicenseDao licenseDao = new LicenseDao();

        List<NormalUserModel> normals = normalUserDao.selectAll();
        List<LicenseModel> license = new ArrayList<>();

        for (NormalUserModel model : normals) {
            license.add(licenseDao.getById(model.getLicenseId()));
        }

        session.setAttribute("normals", normals);
        session.setAttribute("license", license);
    }
}
