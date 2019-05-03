package com.atypon.training.litratum.model.database.daos.implementations;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.daos.interfaces.IUnprocessedContentDao;
import com.atypon.training.litratum.model.database.datamodel.UnprocessedContentModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnprocessedContentDao implements IUnprocessedContentDao {

    @Override
    public List<UnprocessedContentModel> getAll() {
        List<UnprocessedContentModel> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        UnprocessedContentModel unprocessedContent = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from unprocessed_content_table;";
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                unprocessedContent = getObject(result);
                list.add(unprocessedContent);
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public UnprocessedContentModel getById(String unprocessedContentId) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        UnprocessedContentModel unprocessedContent = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from unprocessed_content_table where unprocessed_content_id == ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, unprocessedContentId);

            ResultSet result = statement.executeQuery();

            result.next();

            unprocessedContent = getObject(result);

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unprocessedContent;

    }

    @Override
    public void add(UnprocessedContentModel o) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "insert into unprocessed_content_table(unprocessed_content_id,file_name,admin_id,status,time_stamp) values (?,?,?,?,?);";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, o.getUnprocessedContentId());
            statement.setString(2, o.getFileName());
            statement.setString(3, o.getAdminId());
            statement.setInt(4, o.getStatus());
            statement.setString(5, o.getTimeStamp());


            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private UnprocessedContentModel getObject(ResultSet result) throws SQLException {
        String unprocessedContentId = result.getString(1);
        String fileName = result.getString(2);
        String adminId = result.getString(3);
        int status = result.getInt(4);
        String timeStamp = result.getString(5);
        return new UnprocessedContentModel(unprocessedContentId, fileName, adminId, status, timeStamp);
    }
}
