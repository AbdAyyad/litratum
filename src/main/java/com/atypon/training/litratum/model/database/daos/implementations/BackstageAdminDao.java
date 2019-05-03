package com.atypon.training.litratum.model.database.daos.implementations;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.daos.interfaces.ISubUserDao;
import com.atypon.training.litratum.model.database.datamodel.BackStageAdminModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
