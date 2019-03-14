package com.atypon.training.litratum.mvc.controllers.database.daos;

import com.atypon.training.litratum.mvc.controllers.database.ConnectionPool;
import com.atypon.training.litratum.mvc.model.database.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String query = "INSERT INTO user_table (username, user_password) VALUES (?,?);";
        insertQuery(query, user.getUsername(), user.getPassword());
    }

    private void insertQuery(String sql, Object... args) {
        try {
            insertQueryWithException(sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertQueryWithException(String sql, Object... args) throws SQLException {
        Connection con = pool.getConnConnection();
        PreparedStatement statement = con.prepareStatement(sql);
        for (int i = 0; i < args.length; ++i) {
            if (args[i] instanceof Integer) {
                statement.setInt(i + 1, (Integer) args[i]);
            } else if (args[i] instanceof String) {
                statement.setString(i + 1, (String) args[i]);
            }
        }
        statement.execute();
        pool.addConnConnection(con);
        con = null;
    }

    public User getUser(String username) {
        try {
            return getUserWithException(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User getUserWithException(String username) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM user_table WHERE username = ");
        sql.append('\'');
        sql.append(username);
        sql.append('\'');
        Connection con = pool.getConnConnection();
        PreparedStatement statement = con.prepareStatement(sql.toString());
        ResultSet result = statement.executeQuery();
        result.next();

        String password = result.getString(2);

        User user = new User(username, password);

        pool.addConnConnection(con);
        con = null;
        System.out.println(result.getObject(1));
        result.close();
        return user;
    }
}
