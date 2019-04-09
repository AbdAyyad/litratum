package com.atypon.training.litratum.mvc.model.database.daos;

import com.atypon.training.litratum.mvc.model.database.UnprocessedContent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnprocessedDao extends Dao {
    public UnprocessedDao(Connection con) {
        super(con);
    }

    @Override
    public void addEntry(Object o) {
        String query = "INSERT INTO unprocessed_content (file_name) VALUES (?);";
        insertQuery(query, o.toString());
    }

    @Override
    protected List<Object> getAllWithException(Connection con) throws SQLException {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM unprocessed_content;");
        ResultSet result = getResult(sql.toString(), con);
        List<Object> list = new ArrayList<>();

        while (result.next()) {
            int id = result.getInt(2);
            String fileName = result.getString(1);
            UnprocessedContent content = new UnprocessedContent(id, fileName);
            list.add(content);
        }

        result.close();
        return list;
    }
}
