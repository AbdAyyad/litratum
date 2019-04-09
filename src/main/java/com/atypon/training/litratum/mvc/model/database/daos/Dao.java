package com.atypon.training.litratum.mvc.model.database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class Dao {
    private Connection con;

    public Dao(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public abstract void addEntry(Object o);

    protected void insertQuery(String sql, Object... args) {
        try (Connection con = this.con) {
            insertQueryWithException(con, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertQueryWithException(Connection con, String sql, Object... args) throws SQLException {
        PreparedStatement statement = con.prepareStatement(sql);
        for (int i = 0; i < args.length; ++i) {
            if (args[i] instanceof Integer) {
                statement.setInt(i + 1, (Integer) args[i]);
            } else if (args[i] instanceof String) {
                statement.setString(i + 1, (String) args[i]);
            }
        }
        statement.execute();
    }

    protected ResultSet getResult(String sql, Connection con) {
        try {
            return getResultWithException(sql, con);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ResultSet getResultWithException(String sql, Connection con) throws SQLException {
        PreparedStatement statement = con.prepareStatement(sql);
        return statement.executeQuery();
    }

    public List<Object> getAll(){
        try {
            return getAllWithException(getCon());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected abstract List<Object> getAllWithException(Connection con) throws SQLException;

}
