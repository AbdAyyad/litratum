package com.atypon.training.litratum.database;

import com.atypon.training.litratum.xml.DataBase;
import com.atypon.training.litratum.xml.XmlParser;

public class UserDao {
    private static UserDao instance;

    private UserDao() {

    }

    public static synchronized UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }
}
