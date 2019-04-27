package com.atypon.training.litratum.model.database.daos;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.datatypes.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao {
    @Override
    public void addEntry(Object o) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        User user = (User) o;
        try (Connection con = pool.getConnection()) {
            String sql = "insert into user_table(user_name,user_email,user_password,user_id, logged_in) values (?,?,?,?,?);";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getUserEmail());
            statement.setString(3, user.getUserPassword());
            statement.setString(4, user.getUserId());
            statement.setBoolean(5, user.isLoggedIn());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getSingleEntry(String id) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        Object user = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from user_table where user_id == ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);

            ResultSet result = statement.executeQuery();

            result.next();

            String userName = result.getString(1);
            String userEmail = result.getString(2);
            String userPassword = result.getString(3);
            String userId = result.getString(4);
            boolean loggedIn = result.getBoolean(5);

            user = new User(userId, userName, userEmail, userPassword, loggedIn);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<Object> getAllEntries() {
        List<Object> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        Object user = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from user_table;";
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                String userName = result.getString(1);
                String userEmail = result.getString(2);
                String userPassword = result.getString(3);
                String userId = result.getString(4);
                boolean loggedIn = result.getBoolean(5);

                user = new User(userId, userName, userEmail, userPassword, loggedIn);
                list.add(user);
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void editEntry(Object o) {

    }
}
