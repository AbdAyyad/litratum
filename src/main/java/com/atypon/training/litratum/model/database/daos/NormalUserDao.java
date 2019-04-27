package com.atypon.training.litratum.model.database.daos;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.datatypes.NormalUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NormalUserDao implements Dao {
    @Override
    public void addEntry(Object o) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        NormalUser normalUser = (NormalUser) o;
        try (Connection con = pool.getConnection()) {
            String sql = "insert into normal_user_table(normal_user_id,user_id,license_id) values (?,?,?);";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, normalUser.getNormalUserId());
            statement.setString(2, normalUser.getUserId());
            statement.setString(3, normalUser.getLicenseId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getSingleEntry(String id) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        Object normalUser = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from normal_user_table where normal_user_id == ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);

            ResultSet result = statement.executeQuery();

            result.next();

            String normalUserId = result.getString(1);
            String userId = result.getString(2);
            String licenseId = result.getString(3);

            normalUser = new NormalUser(normalUserId, userId, licenseId);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return normalUser;
    }

    @Override
    public List<Object> getAllEntries() {
        List<Object> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        Object normalUser = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from normal_user_table;";
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                String normalUserId = result.getString(1);
                String userId = result.getString(2);
                String licenseId = result.getString(3);

                normalUser = new NormalUser(normalUserId, userId, licenseId);
                list.add(normalUser);
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
