package com.atypon.training.litratum.database.daos;

import com.atypon.training.litratum.database.ConnectionPool;
import com.atypon.training.litratum.mvc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    private static UserDao instance;

    private ConnectionPool pool;

    private UserDao() {
        pool = ConnectionPool.getConnectionPool();
    }

    public static synchronized UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public void addUser(User user) {
        try {
            addUserWithException(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addUserWithException(User user) throws SQLException {
        String query = "insert into user_table (username,user_password) values(?, ?);";
        Connection con = pool.getConnConnection();
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.execute();
        pool.addConnConnection(con);
        con = null;
    }
}
