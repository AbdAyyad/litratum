package com.atypon.training.litratum.mvc.model.database;

public class UnprocessedContent {
    private int id;
    private String fileName;

    public UnprocessedContent(int id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }
}
