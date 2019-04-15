package com.atypon.training.litratum.mvc.model.database;

public class ProcessedContent {
    private int id;
    private String dao;
    private String title;
    private String path;

    public ProcessedContent(int id, String dao, String title, String path) {
        this.id = id;
        this.dao = dao;
        this.title = title;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getDao() {
        return dao;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }
}
