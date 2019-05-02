package com.atypon.training.litratum.controllers.actions.admin;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.atypon.training.litratum.controllers.actions.Action;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.controllers.tools.RandomGenerator;
import com.atypon.training.litratum.model.database.daos.AdminDao;
import com.atypon.training.litratum.model.database.daos.BackstageAdminDao;
import com.atypon.training.litratum.model.database.daos.UserDao;
import com.atypon.training.litratum.model.database.datatypes.AdminModel;
import com.atypon.training.litratum.model.database.datatypes.BackStageAdminModel;
import com.atypon.training.litratum.model.database.datatypes.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateNewAdminAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();
        boolean adminIsLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");

        if (adminIsLoggedIn) {
            String email = req.getParameter("newAdminEmail");
            String password = req.getParameter("newAdminPassword");
            String username = req.getParameter("newAdminName");
            String option = req.getParameter("optradio");

            String userId = RandomGenerator.getRandomString(64);
            String adminId = RandomGenerator.getRandomString(64);

            char[] bcryptChars = BCrypt.withDefaults().hashToChar(12, password.toCharArray());
            password = new String(bcryptChars);

            UserModel user = new UserModel(userId, username, email, password);

            UserDao userDao = new UserDao();
            userDao.addEntry(user);

            if (option.equals("admin")) {
                AdminModel admin = new AdminModel(userId, adminId);
                AdminDao dao = new AdminDao();
                dao.addEntry(admin);
            } else if (option.equals("backstage")) {
                BackStageAdminModel admin = new BackStageAdminModel(adminId, userId);
                BackstageAdminDao dao = new BackstageAdminDao();
                dao.addEntry(admin);
            }
            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.ADMIN_HOME_PAGE);
        }
        dispatcher.forward(req,resp);
    }
}
