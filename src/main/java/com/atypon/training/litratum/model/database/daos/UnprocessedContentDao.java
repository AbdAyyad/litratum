package com.atypon.training.litratum.model.database.daos;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.datatypes.UnprocessedContentModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnprocessedContentDao implements Dao {
    @Override
    public void addEntry(Object o) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        UnprocessedContentModel unprocessedContent = (UnprocessedContentModel) o;
        try (Connection con = pool.getConnection()) {
            String sql = "insert into unprocessed_content_table(unprocessed_content_id,file_name,admin_id,status,time_stamp) values (?,?,?,?,?);";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, unprocessedContent.getUnprocessedContentId());
            statement.setString(2, unprocessedContent.getFileName());
            statement.setString(3, unprocessedContent.getAdminId());
            statement.setInt(4, unprocessedContent.getStatus());
            statement.setString(5, unprocessedContent.getTimeStamp());


            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getSingleEntry(String id) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        Object unprocessedContent = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from unprocessed_content_table where unprocessed_content_id == ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);

            ResultSet result = statement.executeQuery();

            result.next();

            String unprocessedContentId = result.getString(1);
            String fileName = result.getString(2);
            String adminId = result.getString(3);
            int status = result.getInt(4);
            String timeStamp = result.getString(5);

            unprocessedContent = new UnprocessedContentModel(unprocessedContentId, fileName, adminId, status, timeStamp);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unprocessedContent;
    }

    @Override
    public List<Object> getAllEntries() {
        List<Object> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        Object unprocessedContent = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from unprocessed_content_table;";
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                String unprocessedContentId = result.getString(1);
                String fileName = result.getString(2);
                String adminId = result.getString(3);
                int status = result.getInt(4);
                String timeStamp = result.getString(5);

                unprocessedContent = new UnprocessedContentModel(unprocessedContentId, fileName, adminId, status, timeStamp);
                list.add(unprocessedContent);
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
