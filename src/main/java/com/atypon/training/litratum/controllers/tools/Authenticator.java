package com.atypon.training.litratum.controllers.tools;

import com.atypon.training.litratum.model.database.daos.UserDao;

public class Authenticator {
    private Authenticator(){}
    public static boolean isLoggedIn(String email){
        UserDao dao = new UserDao();
        return dao.isLoggedIn(email);
    }
}
