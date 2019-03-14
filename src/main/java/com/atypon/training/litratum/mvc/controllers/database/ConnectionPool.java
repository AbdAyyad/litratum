package com.atypon.training.litratum.mvc.controllers.database;

import com.atypon.training.litratum.Constants;
import com.atypon.training.litratum.mvc.model.xml.DataBase;
import com.atypon.training.litratum.mvc.controllers.xml.XmlParser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance;
    private BlockingQueue<Connection> queue;

    private ConnectionPool() {
        try {
            fillTheQueue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static ConnectionPool getConnectionPool() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private void fillTheQueue() throws ClassNotFoundException, SQLException {
        queue = new ArrayBlockingQueue<Connection>(Constants.CONNECTION_POOL_SIZE);
        Class.forName("org.postgresql.Driver");
        DataBase dataBase = getDataBaseObject();
        for (int i = 0; i < Constants.CONNECTION_POOL_SIZE; ++i) {
            Connection con = DriverManager.getConnection(dataBase.getUri(), dataBase.getUser(), dataBase.getPassword());
            queue.add(con);
        }
    }

    private DataBase getDataBaseObject() {
        XmlParser parser = new XmlParser();
        return (DataBase) parser.read(Constants.DATABASE_XML_FILE);
    }

    public Connection getConnConnection() {
        try {
            return getConnectionWithException();
        } catch (InterruptedException ex) {
            return null;
        }
    }

    private Connection getConnectionWithException() throws InterruptedException {
        return queue.take();
    }

    public void addConnConnection(Connection con) {
        queue.add(con);
    }

}
