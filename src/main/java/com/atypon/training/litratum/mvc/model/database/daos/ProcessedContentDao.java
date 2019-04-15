package com.atypon.training.litratum.mvc.model.database.daos;

import com.atypon.training.litratum.mvc.model.database.ProcessedContent;
import com.atypon.training.litratum.mvc.model.database.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcessedContentDao extends Dao {

    public ProcessedContentDao(Connection con) {
        super(con);
    }

    @Override
    public void addEntry(Object o) {
        String query = "INSERT INTO processed_content (dao,title,path) VALUES (?,?,?);";
        ProcessedContent content = (ProcessedContent) o;
        insertQuery(query, content.getDao(), content.getTitle(), content.getPath());
    }

    @Override
    protected List<Object> getAllWithException(Connection con) throws SQLException {
        String query = "SELECT * FROM  processed_content;";
        ResultSet result = getResult(query, con);
        List<Object> list = new ArrayList<>();

        while (result.next()) {
            int id = result.getInt(1);
            String dao = result.getString(2);
            String title = result.getString(3);
            String path = result.getString(4);
            ProcessedContent content = new ProcessedContent(id, dao, title, path);
            list.add(content);
        }

        result.close();
        return list;
    }

    @Override
    protected Object getEntryWithException(Connection con, int id) throws SQLException {
        final StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM  processed_content where id = ");
        query.append(id);
        query.append(';');
        ResultSet result = getResult(query.toString(), con);


        result.next();

        String dao = result.getString(2);
        String title = result.getString(3);
        String path = result.getString(4);
        ProcessedContent content = new ProcessedContent(id, dao, title, path);


        result.close();
        return content;
    }
}
