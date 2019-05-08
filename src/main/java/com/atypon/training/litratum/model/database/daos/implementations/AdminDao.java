package com.atypon.training.litratum.model.database.daos.implementations;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.daos.interfaces.ISubUserDao;
import com.atypon.training.litratum.model.database.datamodel.AdminModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao implements ISubUserDao<AdminModel> {

    @Override
    public AdminModel getByUserId(String userId) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        AdminModel admin = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from admin_table where user_id = ? limit 1;";
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
    public List<AdminModel> selectAll() {
        List<AdminModel> data = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "select * from admin_table;";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                data.add(getObject(result));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void add(AdminModel o) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "insert into admin_table(admin_id,user_id) values (?,?);";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, o.getAdminId());
            statement.setString(2, o.getUserId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private AdminModel getObject(ResultSet resultSet) throws SQLException {
        String adminId = resultSet.getString(1);
        String userId = resultSet.getString(2);
        return new AdminModel(userId, adminId);
    }
}
