package com.atypon.training.litratum.model.database.daos.implementations;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.daos.interfaces.ISubLicenseDao;
import com.atypon.training.litratum.model.database.datamodel.LicenseSubscriptionModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LicenseSubscriptionDao implements ISubLicenseDao<LicenseSubscriptionModel> {

    @Override
    public LicenseSubscriptionModel getById(String id) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        LicenseSubscriptionModel licenseSubscription = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from license_subscription_table where license_subscription_id == ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);

            ResultSet result = statement.executeQuery();

            result.next();
            String licenseSubscriptionId = result.getString(2);
            String endDate = result.getString(1);

            licenseSubscription = new LicenseSubscriptionModel(licenseSubscriptionId, endDate);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return licenseSubscription;
    }

    @Override
    public void update(LicenseSubscriptionModel license) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "UPDATE license_subscription_table SET end_date = ? WHERE license_subscription_id = ?;";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, license.getEndDate());
            statement.setString(2, license.getLicenseSubscriptionId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(LicenseSubscriptionModel o) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "insert into license_subscription_table(end_date,license_subscription_id) values (?,?);";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, o.getEndDate());
            statement.setString(2, o.getLicenseSubscriptionId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
