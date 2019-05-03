package com.atypon.training.litratum.model.database.daos.implementations;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.daos.interfaces.IUserDao;
import com.atypon.training.litratum.model.database.datamodel.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements IUserDao {
    @Override
    public UserModel getByEmail(String email) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        UserModel user = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from user_table where user_email = ? limit 1;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();

            result.next();

            String userName = result.getString(1);
            String userEmail = result.getString(2);
            String userPassword = result.getString(3);
            String userId = result.getString(4);

            user = new UserModel(userId, userName, userEmail, userPassword);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(UserModel user) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "UPDATE user_table SET user_email = ?, user_name = ?, user_password = ? WHERE user_id = ?;";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, user.getUserEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getUserPassword());
            statement.setString(4, user.getUserId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(UserModel o) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "insert into user_table(user_name,user_email,user_password,user_id) values (?,?,?,?);";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, o.getUserName());
            statement.setString(2, o.getUserEmail());
            statement.setString(3, o.getUserPassword());
            statement.setString(4, o.getUserId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
