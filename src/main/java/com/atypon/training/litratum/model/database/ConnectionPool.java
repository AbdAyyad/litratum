package com.atypon.training.litratum.model.database;

import com.atypon.training.litratum.controllers.tools.Constants;
import com.atypon.training.litratum.controllers.tools.XmlTransformer;
import com.atypon.training.litratum.model.xmlobject.DataBase;
import com.atypon.training.litratum.model.xmlobject.XmlFactory;
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
            initPoolWithException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initPoolWithException() throws Exception {
        DataBase dataBase = getDataBaseObject();

        pool = new BasicDataSource();

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


    private DataBase getDataBaseObject() throws Exception {
        String xml = XmlTransformer.getXml(Constants.DATABASE_XML_FILE, Constants.DATABASE_XSL_FILE);
        XmlFactory factory = new XmlFactory(xml);
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
