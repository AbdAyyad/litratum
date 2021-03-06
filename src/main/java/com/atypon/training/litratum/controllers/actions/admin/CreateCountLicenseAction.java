package com.atypon.training.litratum.controllers.actions.admin;

import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.controllers.tools.RandomGenerator;
import com.atypon.training.litratum.model.database.daos.implementations.LicenseCountDao;
import com.atypon.training.litratum.model.database.daos.implementations.LicenseDao;
import com.atypon.training.litratum.model.database.daos.implementations.NormalUserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.ILicenseDao;
import com.atypon.training.litratum.model.database.daos.interfaces.INormalUserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.ISubLicenseDao;
import com.atypon.training.litratum.model.database.datamodel.LicenseCountModel;
import com.atypon.training.litratum.model.database.datamodel.LicenseModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

public class CreateCountLicenseAction implements IAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Object sessionAttr = session.getAttribute("loggedInAdmin");
        boolean adminIsLoggedIn = sessionAttr == null ? false : (Boolean) sessionAttr;
        if (adminIsLoggedIn) {
            createCountLicense(req);
            resp.sendRedirect("/admin/users/");
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(JspPath.ADMIN_HOME_PAGE);
            dispatcher.forward(req, resp);
        }
    }

    private void createCountLicense(HttpServletRequest req) {
        int count = Integer.valueOf(req.getParameter("count"));
        String userId = req.getParameter("userId");

        ISubLicenseDao<LicenseCountModel> countDao = new LicenseCountDao();
        ILicenseDao licenseDao = new LicenseDao();
        INormalUserDao normalUserDao = new NormalUserDao();

        String actualId = RandomGenerator.getRandomString(64);
        String licenseId = RandomGenerator.getRandomString(64);

        LicenseCountModel actualLicense = new LicenseCountModel(actualId, count);
        LicenseModel license = new LicenseModel(1, licenseId, actualId, LocalDate.now().toString());

        countDao.add(actualLicense);
        licenseDao.add(license);
        normalUserDao.update(userId, licenseId);
    }
}
