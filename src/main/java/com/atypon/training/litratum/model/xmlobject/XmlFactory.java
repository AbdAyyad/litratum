package com.atypon.training.litratum.model.xmlobject;

import com.atypon.training.litratum.controllers.tools.Constants;
import com.atypon.training.litratum.controllers.tools.XmlTransformer;
import com.atypon.training.litratum.model.Action;
import com.atypon.training.litratum.model.database.datatypes.ArticleMeta;

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
        reader.readLine();// <? xmlobject version="1.0" encoding="UTF-8?>
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
        String host = reader.readLine().trim();
        int port = Integer.parseInt(reader.readLine().trim());
        String password = reader.readLine().trim();
        String name = reader.readLine().trim();
        String user = reader.readLine().trim();
        return new DataBase(host, name, password, port, user);
    }

    private ArticleMeta getArticleMeta() throws IOException {
        String author = reader.readLine().trim();
        String doi = reader.readLine().trim();
        String date = reader.readLine().trim();
        String title = reader.readLine().trim();
        return new ArticleMeta(author, doi, date, title);
    }
}
