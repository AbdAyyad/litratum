package com.atypon.training.litratum.model.xml;

import com.atypon.training.litratum.model.database.datatypes.ArticleMeta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

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


    public DataBase getDataBase() throws IOException {
        String host = reader.readLine().trim();
        int port = Integer.parseInt(reader.readLine().trim());
        String password = reader.readLine().trim();
        String name = reader.readLine().trim();
        String user = reader.readLine().trim();
        return new DataBase(host, name, password, port, user);
    }

    public ArticleMeta getArticleMeta() throws IOException {
        String author = reader.readLine().trim();
        String doi = reader.readLine().trim();
        String date = reader.readLine().trim();
        String title = reader.readLine().trim();
        return new ArticleMeta(author, doi, date, title);
    }

    public Map<String, Action> getAllActions() throws IOException {
        Map<String, Action> map = new HashMap<>();
        String name;
        String className;
        String jsp;

        while ((name = reader.readLine()) != null) {
            name = name.trim();
            className = reader.readLine().trim();
            jsp = reader.readLine().trim();
            Action action = new Action(className, jsp);
            map.put(name, action);
        }
        return map;
    }
}
