package com.atypon.training.litratum.model.database.datamodel;

import java.util.Objects;

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

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnprocessedContentModel)) return false;
        UnprocessedContentModel that = (UnprocessedContentModel) o;
        return getStatus() == that.getStatus() &&
                getUnprocessedContentId().equals(that.getUnprocessedContentId()) &&
                getFileName().equals(that.getFileName()) &&
                getAdminId().equals(that.getAdminId()) &&
                getTimeStamp().equals(that.getTimeStamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUnprocessedContentId(), getFileName(), getAdminId(), getStatus(), getTimeStamp());
    }
}
