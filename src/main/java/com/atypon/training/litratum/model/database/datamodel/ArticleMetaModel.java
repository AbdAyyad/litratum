package com.atypon.training.litratum.model.database.datamodel;

public class ArticleMetaModel {
    private String author;
    private String doi;
    private String releaseDate;
    private String title;

    public ArticleMetaModel(String author, String doi, String releaseDate, String title) {
        this.author = author;
        this.doi = doi;
        this.releaseDate = releaseDate;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDoi() {
        return doi;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }
}
