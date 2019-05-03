package com.atypon.training.litratum.model.database.daos.implementations;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.daos.interfaces.IDao;
import com.atypon.training.litratum.model.database.datamodel.LicenseModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LicenseDao implements IDao<LicenseModel> {

    @Override
    public void add(LicenseModel license) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
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
}
