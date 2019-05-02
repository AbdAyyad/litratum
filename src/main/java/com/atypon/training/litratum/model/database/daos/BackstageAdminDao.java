package com.atypon.training.litratum.model.database.daos;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.datatypes.BackStageAdminModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BackstageAdminDao implements Dao {
    @Override
    public void addEntry(Object o) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        BackStageAdminModel admin = (BackStageAdminModel) o;
        try (Connection con = pool.getConnection()) {
            String sql = "insert into backstage_admin_table(backstage_admin_id,user_id) values (?,?);";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, admin.getBackStageAdminId());
            statement.setString(2, admin.getUserId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getSingleEntry(String id) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        Object admin = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from backstage_admin_table where backstage_admin_id = ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            String backStageAdminId = result.getString(1);
            String userId = result.getString(2);
            admin = new BackStageAdminModel(backStageAdminId, userId);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public List<Object> getAllEntries() {
        List<Object> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "select * from backstage_admin_table;";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String backStageAdminId = result.getString(1);
                String userId = result.getString(2);
                BackStageAdminModel admin = new BackStageAdminModel(backStageAdminId, userId);
                list.add(admin);
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

    public BackStageAdminModel getBackstageAdminByUserId(String userId) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        BackStageAdminModel admin = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from backstage_admin_table where user_id = ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, userId);

            ResultSet result = statement.executeQuery();

            result.next();

            String backStageAdminId = result.getString(1);
            admin = new BackStageAdminModel(backStageAdminId, userId);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}
