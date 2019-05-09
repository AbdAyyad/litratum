package com.atypon.training.litratum.model.database.daos.implementations;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.daos.interfaces.INormalUserDao;
import com.atypon.training.litratum.model.database.datamodel.NormalUserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NormalUserDao implements INormalUserDao {

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
            user = getObject(result);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<NormalUserModel> selectAll() {
        List<NormalUserModel> data = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "select * from normal_user_table;";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                data.add(getObject(result));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void delete(String userId) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "delete FROM normal_user_table where user_id = ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, userId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private NormalUserModel getObject(ResultSet result) throws SQLException {
        String normalId = result.getString(1);
        String userId = result.getString(2);
        String licenseId = result.getString(3);
        return new NormalUserModel(normalId, userId, licenseId);
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
    public void update(String userId, String licenseId) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "update normal_user_table set license_id = ? where user_id = ?;";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, licenseId);
            statement.setString(2, userId);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
