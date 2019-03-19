package com.atypon.training.litratum.mvc.model.database.daos;

import com.atypon.training.litratum.mvc.model.database.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends Dao {
    public UserDao(Connection con) {
        super(con);
    }

    public void addUser(User user) {
        String query = "INSERT INTO user_table (user_name, user_password) VALUES (?,?);";
        insertQuery(query, user.getUsername(), user.getPassword());
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
        PreparedStatement statement = con.prepareStatement(sql.toString());
        ResultSet result = statement.executeQuery();
        result.next();

        String password = result.getString(2);

        User user = new User(username, password);

        result.close();
        return user;
    }
}
