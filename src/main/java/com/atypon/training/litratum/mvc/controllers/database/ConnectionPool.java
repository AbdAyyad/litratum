package com.atypon.training.litratum.mvc.controllers.database;

import com.atypon.training.litratum.Constants;
import com.atypon.training.litratum.mvc.controllers.xml.XmlParser;
import com.atypon.training.litratum.mvc.model.xml.DataBase;
import com.atypon.training.litratum.mvc.model.xml.XmlFactory;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static ConnectionPool instance;

    private BasicDataSource pool;

    private ConnectionPool() {
        initThePool();
    }

    private void initThePool() {
        try {
            initThePoolWithException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initThePoolWithException() throws ClassNotFoundException {
        //  Class.forName("org.apache.commons.dbcp2.BasicDataSource");
        pool = new BasicDataSource();

        DataBase dataBase = getDataBaseObject();

        pool.setUrl(dataBase.getUri());
        pool.setUsername(dataBase.getUser());
        pool.setPassword(dataBase.getPassword());

        pool.setMinIdle(Constants.CONNECTION_POOL_MIN_SIZE);
        pool.setMaxIdle(Constants.CONNECTION_POOL_MAX_SIZE);
    }

    public synchronized static ConnectionPool getConnectionPool() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }


    private DataBase getDataBaseObject() {
        XmlFactory factory = new XmlFactory(XmlParser.getXml("xml/DataBase.xml", "xsl/DataBase.xsl"));
        return factory.getDataBase();
    }

    private Connection getConnectionWithException() throws SQLException {
        return pool.getConnection();
    }

    public Connection getConnection() {
        try {
            return getConnectionWithException();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
