package com.atypon.training.litratum.model.database.daos.implementations;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.daos.interfaces.INormalUser;
import com.atypon.training.litratum.model.database.datamodel.NormalUserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NormalUserDao implements INormalUser {

    @Override
    public NormalUserModel getByUserId(String userId) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        NormalUserModel user = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from normal_user_table where user_id = ? limit 1;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, userId);
            ResultSet result = statement.executeQuery();
            result.next();
            String adminId = result.getString(1);
            user = new NormalUserModel(userId, adminId);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void add(NormalUserModel o) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "insert into normal_user_table(normal_user_id,user_id,license_id) values (?,?,?);";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, o.getNormalUserId());
            statement.setString(2, o.getUserId());
            statement.setString(3, o.getLicenseId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(NormalUserModel normalUser) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "update normal_user_table set license_id = ? where normal_user_id = ?;";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, normalUser.getLicenseId());
            statement.setString(2, normalUser.getNormalUserId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
