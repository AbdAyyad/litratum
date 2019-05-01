package com.atypon.training.litratum.controllers.actions.admin;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.atypon.training.litratum.controllers.actions.ActionInterface;
import com.atypon.training.litratum.controllers.tools.Authenticator;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.controllers.tools.RandomGenerator;
import com.atypon.training.litratum.model.database.daos.AdminDao;
import com.atypon.training.litratum.model.database.daos.BackstageAdminDao;
import com.atypon.training.litratum.model.database.daos.UserDao;
import com.atypon.training.litratum.model.database.datatypes.Admin;
import com.atypon.training.litratum.model.database.datatypes.BackStageAdmin;
import com.atypon.training.litratum.model.database.datatypes.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateNewAdminAction implements ActionInterface {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
        String currentEmail = req.getParameter("adminEmail");
        String email = req.getParameter("newAdminEmail");
        String password = req.getParameter("newAdminPassword");
        String username = req.getParameter("newAdminName");
        String option = req.getParameter("optradio");

        RequestDispatcher dispatcher;
        boolean flag = Authenticator.isLoggedInAdmin(currentEmail);

        if (flag) {
            String userId = RandomGenerator.getRandomString(64);
            String adminId = RandomGenerator.getRandomString(64);

            char[] bcryptChars = BCrypt.withDefaults().hashToChar(12, password.toCharArray());
            password = new String(bcryptChars);

            User user = new User(userId, username, email, password, true);

            UserDao userDao = new UserDao();
            userDao.addEntry(user);

            if (option.equals("admin")) {
                Admin admin = new Admin(userId, adminId);
                AdminDao dao = new AdminDao();
                dao.addEntry(admin);
            } else if (option.equals("backstage")) {
                BackStageAdmin admin = new BackStageAdmin(adminId, userId);
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
