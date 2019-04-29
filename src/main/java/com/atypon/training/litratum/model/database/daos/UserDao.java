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
        User user = (User) o;
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "UPDATE user_table SET logged_in = ?, user_email = ?, user_name = ?, user_password = ? WHERE user_id = ?;";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setBoolean(1, user.isLoggedIn());
            statement.setString(2, user.getUserEmail());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getUserPassword());
            statement.setString(5, user.getUserId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean isLoggedIn(String email) {

        boolean retValue = false;
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            StringBuilder sql = new StringBuilder();

            sql.append("select logged_in from user_table where user_email = ");
            sql.append("'");
            sql.append(email);
            sql.append("';");

            PreparedStatement statement = con.prepareStatement(sql.toString());

            ResultSet set = statement.executeQuery();

            set.next();
            retValue = set.getBoolean(1);
            set.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public User getUserByEmail(String email) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        User user = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from user_table where user_email = ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, email);

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

    public String getUserName(String email) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        String userName = "";
        try (Connection con = pool.getConnection()) {
            String sql = "select user_name from user_table where user_email = ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();
            result.next();

            userName = result.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userName;
    }
}
