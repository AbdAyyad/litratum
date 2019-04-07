package com.atypon.training.litratum.mvc.model.database.daos;

import java.sql.Connection;

public class UnprocessedDao extends Dao {
    public UnprocessedDao(Connection con) {
        super(con);
    }

    @Override
    public void addEntry(Object o) {
        String query = "INSERT INTO unprocessed_content (file_name) VALUES (?);";
        insertQuery(query, o.toString());
    }
}
