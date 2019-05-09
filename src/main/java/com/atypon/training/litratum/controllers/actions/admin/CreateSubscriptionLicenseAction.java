package com.atypon.training.litratum.controllers.actions.admin;

import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.controllers.tools.RandomGenerator;
import com.atypon.training.litratum.model.database.daos.implementations.LicenseDao;
import com.atypon.training.litratum.model.database.daos.implementations.LicenseSubscriptionDao;
import com.atypon.training.litratum.model.database.daos.implementations.NormalUserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.ILicenseDao;
import com.atypon.training.litratum.model.database.daos.interfaces.INormalUserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.ISubLicenseDao;
import com.atypon.training.litratum.model.database.datamodel.LicenseModel;
import com.atypon.training.litratum.model.database.datamodel.LicenseSubscriptionModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

public class CreateSubscriptionLicenseAction implements IAction {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Object sessionAttr = session.getAttribute("loggedInAdmin");
        boolean adminIsLoggedIn = sessionAttr == null ? false : (Boolean) sessionAttr;
        if (adminIsLoggedIn) {
            createSubscriptionLicense(req);
            resp.sendRedirect("/admin/users/");
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(JspPath.ADMIN_HOME_PAGE);
            dispatcher.forward(req, resp);
        }
    }

    private void createSubscriptionLicense(HttpServletRequest req) {
        String date = req.getParameter("date");
        String userId = req.getParameter("userId");

        ISubLicenseDao<LicenseSubscriptionModel> subDao = new LicenseSubscriptionDao();
        ILicenseDao licenseDao = new LicenseDao();
        INormalUserDao normalUserDao = new NormalUserDao();

        String actualId = RandomGenerator.getRandomString(64);
        String licenseId = RandomGenerator.getRandomString(64);

        LicenseModel license = new LicenseModel(2, licenseId, actualId, LocalDate.now().toString());
        LicenseSubscriptionModel actualLicense = new LicenseSubscriptionModel(actualId, date);

        subDao.add(actualLicense);
        licenseDao.add(license);
        normalUserDao.update(userId, licenseId);
    }
}
