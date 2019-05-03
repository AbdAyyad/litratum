package com.atypon.training.litratum.model.database.datamodel;

public class UnprocessedContentModel {
    private String unprocessedContentId;
    private String fileName;
    private String adminId;
    private int status;
    private String timeStamp;

    public UnprocessedContentModel(String unprocessedContentId, String fileName, String adminId, int status, String timeStamp) {
        this.unprocessedContentId = unprocessedContentId;
        this.fileName = fileName;
        this.adminId = adminId;
        this.status = status;
        this.timeStamp = timeStamp;
    }

    public String getUnprocessedContentId() {
        return unprocessedContentId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getAdminId() {
        return adminId;
    }

    public int getStatus() {
        return status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
