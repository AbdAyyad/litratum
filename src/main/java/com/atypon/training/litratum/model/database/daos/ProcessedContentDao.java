package com.atypon.training.litratum.model.database.daos;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.datatypes.ProcessedContentModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcessedContentDao implements Dao {
    @Override
    public void addEntry(Object o) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        ProcessedContentModel processedContent = (ProcessedContentModel) o;
        try (Connection con = pool.getConnection()) {
            String sql = "insert into processed_content_table(processed_content_id,file_name,doi,unprocessed_id,time_stamp,backstage_admin_id) values (?,?,?,?,?,?);";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, processedContent.getProcessedContentId());
            statement.setString(2, processedContent.getFileName());
            statement.setString(3, processedContent.getDoi());
            statement.setString(4, processedContent.getUnprocessedId());
            statement.setString(5, processedContent.getTimeStamp());
            statement.setString(6, processedContent.getBackStageAdminId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getSingleEntry(String id) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        Object processedContent = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from processed_content_table where processed_content_id == ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);

            ResultSet result = statement.executeQuery();

            result.next();

            String processedContentId = result.getString(1);
            String fileName = result.getString(2);
            String doi = result.getString(3);
            String unprocessedId = result.getString(4);
            String timeStamp = result.getString(5);
            String backstageAdminId = result.getString(6);

            processedContent = new ProcessedContentModel(backstageAdminId, processedContentId, fileName, doi, timeStamp, unprocessedId);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return processedContent;
    }

    @Override
    public List<Object> getAllEntries() {
        List<Object> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        Object processedContent = null;
        try (Connection con = pool.getConnection()) {
            String sql = "select * from processed_content_table;";
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                String processedContentId = result.getString(1);
                String fileName = result.getString(2);
                String doi = result.getString(3);
                String unprocessedId = result.getString(4);
                String timeStamp = result.getString(5);
                String backstageAdminId = result.getString(6);

                processedContent = new ProcessedContentModel(backstageAdminId, processedContentId, fileName, doi, timeStamp, unprocessedId);
                list.add(processedContent);
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
