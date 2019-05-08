package com.atypon.training.litratum.model.database.daos.implementations;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.daos.interfaces.ISubUserDao;
import com.atypon.training.litratum.model.database.datamodel.BackStageAdminModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BackstageAdminDao implements ISubUserDao<BackStageAdminModel> {

    @Override
    public void add(BackStageAdminModel o) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "insert into backstage_admin_table(backstage_admin_id,user_id) values (?,?);";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, o.getBackStageAdminId());
            statement.setString(2, o.getUserId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public BackStageAdminModel getByUserId(String userId) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        BackStageAdminModel admin = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from backstage_admin_table where user_id = ? limit 1;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, userId);

            ResultSet result = statement.executeQuery();

            result.next();

            admin = getObject(result);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public List<BackStageAdminModel> selectAll() {
        List<BackStageAdminModel> data = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "select * from backstage_admin_table;";
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

    private BackStageAdminModel getObject(ResultSet result) throws SQLException {
        String backStageAdminId = result.getString(1);
        String userId = result.getString(2);
        return new BackStageAdminModel(backStageAdminId, userId);
    }
}
