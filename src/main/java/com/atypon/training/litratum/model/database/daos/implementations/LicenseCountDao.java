package com.atypon.training.litratum.model.database.daos.implementations;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.daos.interfaces.ISubLicenseDao;
import com.atypon.training.litratum.model.database.datamodel.LicenseCountModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LicenseCountDao implements ISubLicenseDao<LicenseCountModel> {

    @Override
    public LicenseCountModel getById(String id) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        LicenseCountModel licenseCount = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from lisence_count_table where license_count_id == ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();

            result.next();
            String licenseCountId = result.getString(2);
            int count = result.getInt(2);

            licenseCount = new LicenseCountModel(licenseCountId, count);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return licenseCount;
    }

    @Override
    public void update(LicenseCountModel license) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "UPDATE lisence_count_table SET count = ? WHERE license_count_id = ?;";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setInt(1, license.getCount());
            statement.setString(2, license.getLicenseCountId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(LicenseCountModel o) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "insert into lisence_count_table(license_count_id,count) values (?,?);";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, o.getLicenseCountId());
            statement.setInt(2, o.getCount());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
