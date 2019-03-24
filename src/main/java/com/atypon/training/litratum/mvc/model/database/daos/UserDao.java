package com.atypon.training.litratum.mvc.model.database.daos;

import com.atypon.training.litratum.mvc.model.database.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends Dao {
    public UserDao(Connection con) {
        super(con);
    }

    public void addUser(User user) {
        String query = "INSERT INTO user_table (user_name, user_password, user_email) VALUES (?,?,?);";
        insertQuery(query, user.getUsername(), user.getPassword(), user.getEmail());
    }

    public User getUser(String username) {
        try {
            return getUserWithException(getCon(), username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User getUserWithException(Connection con, String username) throws SQLException {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM user_table WHERE user_name = ");
        sql.append('\'');
        sql.append(username);
        sql.append('\'');
        sql.append(';');

        ResultSet result = getResult(sql.toString(), con);
        result.next();

        String password = result.getString(2);
        String email = result.getString(3);

        User user = new User(username, password, email);

        result.close();
        return user;
    }

    public List<User> getAllUsers() {
        try {
            return getAllUsersWithException(getCon());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<User> getAllUsersWithException(Connection con) throws SQLException {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM user_table;");
        ResultSet result = getResult(sql.toString(), con);
        List<User> list = new ArrayList<>();

        while (result.next()) {
            String username = result.getString(1);
            String password = result.getString(2);
            String email = result.getString(3);
            User user = new User(username, password, email);
            list.add(user);
        }

        result.close();
        return list;
    }
}
