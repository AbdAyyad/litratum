package com.atypon.training.litratum.mvc.model.database.daos;

import com.atypon.training.litratum.mvc.model.database.ConnectionPool;
import com.atypon.training.litratum.mvc.model.database.datatypes.LicenseCount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LicenseCountDao implements Dao {
    @Override
    public void addEntry(Object o) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        LicenseCount license = (LicenseCount) o;
        try (Connection con = pool.getConnection()) {
            String sql = "insert into license_count_table(license_count_id,count) values (?,?);";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, license.getLicenseCountId());
            statement.setInt(2, license.getCount());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getSingleEntry(String id) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        Object licenseCount = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from license_count_table where license_count_id == ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            String licenseCountId = result.getString(2);
            int count = result.getInt(2);
            licenseCount = new LicenseCount(licenseCountId, count);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return licenseCount;
    }

    @Override
    public List<Object> getAllEntries() {
        List<Object> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "select * from license_count_table;";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String licenseCountId = result.getString(2);
                int count = result.getInt(2);
                LicenseCount licenseCount = new LicenseCount(licenseCountId, count);
                list.add(licenseCount);
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
