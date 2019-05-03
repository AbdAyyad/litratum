package com.atypon.training.litratum.controllers.actions.admin;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.JspPath;
import com.atypon.training.litratum.controllers.tools.RandomGenerator;
import com.atypon.training.litratum.model.database.daos.implementations.AdminDao;
import com.atypon.training.litratum.model.database.daos.implementations.BackstageAdminDao;
import com.atypon.training.litratum.model.database.daos.implementations.UserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.ISubUserDao;
import com.atypon.training.litratum.model.database.daos.interfaces.IUserDao;
import com.atypon.training.litratum.model.database.datamodel.AdminModel;
import com.atypon.training.litratum.model.database.datamodel.BackStageAdminModel;
import com.atypon.training.litratum.model.database.datamodel.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateNewAdminAction implements IAction {
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

            IUserDao userDao = new UserDao();
            userDao.add(user);

            if (option.equals("admin")) {
                AdminModel admin = new AdminModel(userId, adminId);
                ISubUserDao<AdminModel> dao = new AdminDao();
                dao.add(admin);
            } else if (option.equals("backstage")) {
                BackStageAdminModel admin = new BackStageAdminModel(adminId, userId);
                ISubUserDao<BackStageAdminModel> dao = new BackstageAdminDao();
                dao.add(admin);
            }
            dispatcher = req.getRequestDispatcher(jsp);
        } else {
            dispatcher = req.getRequestDispatcher(JspPath.ADMIN_HOME_PAGE);
        }
        dispatcher.forward(req,resp);
    }
}
