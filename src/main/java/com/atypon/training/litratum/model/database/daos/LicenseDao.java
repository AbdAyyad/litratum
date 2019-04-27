package com.atypon.training.litratum.model.database.daos;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.datatypes.License;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LicenseDao implements Dao {
    @Override
    public void addEntry(Object o) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        License license = (License) o;
        try (Connection con = pool.getConnection()) {
            String sql = "insert into license_table(license_type,time_stamp,license_id,actual_license_id) values (?,?,?,?);";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, license.getLicenseType());
            statement.setString(2, license.getTimeStamp());
            statement.setString(3, license.getLicenseId());
            statement.setString(4, license.getActualLicenseId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getSingleEntry(String id) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        Object license = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from license_table where license_id == ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            result.next();

            String licenseId = result.getString(3);
            String actualLicenseId = result.getString(4);
            int licenseType = result.getInt(1);
            String timeStamp = result.getString(2);

            license = new License(licenseType, licenseId, actualLicenseId, timeStamp);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return license;
    }

    @Override
    public List<Object> getAllEntries() {
        List<Object> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "select * from license_table;";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String licenseId = result.getString(3);
                String actualLicenseId = result.getString(4);
                int licenseType = result.getInt(1);
                String timeStamp = result.getString(2);

                License license = new License(licenseType, licenseId, actualLicenseId, timeStamp);
                list.add(license);
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
