package com.atypon.training.litratum.mvc.model.xml;

import com.atypon.training.litratum.mvc.model.xml.DataBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class XmlFactory {
    private BufferedReader reader;

    public XmlFactory(String data) {
        initReader(data);
    }

    private void initReader(String data) {
        try {
            initReaderWithException(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initReaderWithException(String str) throws Exception {
        reader = new BufferedReader(new StringReader(str));
        reader.readLine();// <? xml version="1.0" encoding="UTF-8?>
    }

    public DataBase getDataBase() {
        try {
            return getDataBaseWithException();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private DataBase getDataBaseWithException() throws IOException {
        String host = reader.readLine();
        int port = Integer.parseInt(reader.readLine());
        String password = reader.readLine();
        String name = reader.readLine();
        String user = reader.readLine();
        return new DataBase(host, name, password, port, user);
    }
}
