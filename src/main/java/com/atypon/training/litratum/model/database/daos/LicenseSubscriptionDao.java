package com.atypon.training.litratum.model.database.daos;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.datatypes.LicenseSubscriptionModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LicenseSubscriptionDao implements Dao {
    @Override
    public void addEntry(Object o) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        LicenseSubscriptionModel license = (LicenseSubscriptionModel) o;
        try (Connection con = pool.getConnection()) {
            String sql = "insert into license_subscription_table(end_date,license_subscription) values (?,?);";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, license.getEndDate());
            statement.setString(2, license.getLicenseSubscriptionId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getSingleEntry(String id) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        Object licenseSubscription = null;
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
    public List<Object> getAllEntries() {
        List<Object> list = new ArrayList<>();

        ConnectionPool pool = ConnectionPool.getConnectionPool();
        Object licenseSubscription = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from license_subscription_table;";
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String licenseSubscriptionId = result.getString(2);
                String endDate = result.getString(1);

                licenseSubscription = new LicenseSubscriptionModel(licenseSubscriptionId, endDate);
                list.add(licenseSubscription);
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
