package com.atypon.training.litratum.mvc.controllers.database.daos;

import com.atypon.training.litratum.mvc.controllers.database.ConnectionPool;
import com.atypon.training.litratum.mvc.model.database.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }

    public void addUser(User user) {
        String query = "INSERT INTO user_table (user_name, user_password) VALUES (?,?);";
        insertQuery(query, user.getUsername(), user.getPassword());
    }

    private void insertQuery(String sql, Object... args) {
        try(Connection con = this.con) {
            insertQueryWithException(con,sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertQueryWithException(Connection con,String sql, Object... args) throws SQLException {
        PreparedStatement statement = con.prepareStatement(sql);
        for (int i = 0; i < args.length; ++i) {
            if (args[i] instanceof Integer) {
                statement.setInt(i + 1, (Integer) args[i]);
            } else if (args[i] instanceof String) {
                statement.setString(i + 1, (String) args[i]);
            }
        }
        statement.execute();
    }

    public User getUser(String username) {
        try (Connection con = this.con){
            return getUserWithException(con,username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User getUserWithException(Connection con,String username) throws SQLException {
        StringBuilder sql = new StringBuilder();
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
