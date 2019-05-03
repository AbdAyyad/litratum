package com.atypon.training.litratum.model.database.daos.implementations;

import com.atypon.training.litratum.model.database.ConnectionPool;
import com.atypon.training.litratum.model.database.daos.interfaces.IDao;
import com.atypon.training.litratum.model.database.datamodel.ProcessedContentModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProcessedContentDao implements IDao<ProcessedContentModel> {

    @Override
    public void add(ProcessedContentModel processedContent) {
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        try (Connection con = pool.getConnection()) {
            String sql = "insert into proccessed_content_table(processed_content_id,file_name,doi,unprocessed_id,time_stamp,backstage_admin_id) values (?,?,?,?,?,?);";
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
}
