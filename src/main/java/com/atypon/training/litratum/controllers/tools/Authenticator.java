package com.atypon.training.litratum.controllers.tools;

import com.atypon.training.litratum.model.database.daos.AdminDao;
import com.atypon.training.litratum.model.database.daos.UserDao;
import com.atypon.training.litratum.model.database.datatypes.Admin;
import com.atypon.training.litratum.model.database.datatypes.User;

public class Authenticator {
    private Authenticator() {
    }

    public static boolean isLoggedInUser(String email) {
        UserDao dao = new UserDao();
        return dao.isLoggedIn(email);
    }

    public static boolean isLoggedInAdmin(String email) {
        UserDao userDao = new UserDao();
        AdminDao adminDao = new AdminDao();

        User user = userDao.getUserByEmail(email);
        Admin admin = adminDao.getAdminByUserId(user.getUserId());

        return admin != null && user.isLoggedIn();
    }

    public static void signIn(String email, String password) {
        UserDao dao = new UserDao();
        User user = dao.getUserByEmail(email);
        boolean passwordTrue = user.verifyPassword(password);
        if (passwordTrue) {
            user.setLoggedIn(true);
            dao.editEntry(user);
        }
    }
}
