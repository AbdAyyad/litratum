package com.atypon.training.litratum.model.database.datatypes;

public class ProcessedContent {
    private String backStageAdminId;
    private String processedContentId;
    private String fileName;
    private String doi;
    private String timeStamp;
    private String unprocessedId;

    public ProcessedContent(String backStageAdminId, String processedContentId, String fileName, String doi, String timeStamp, String unprocessedId) {
        this.backStageAdminId = backStageAdminId;
        this.processedContentId = processedContentId;
        this.fileName = fileName;
        this.doi = doi;
        this.timeStamp = timeStamp;
        this.unprocessedId = unprocessedId;
    }

    public String getBackStageAdminId() {
        return backStageAdminId;
    }

    public String getProcessedContentId() {
        return processedContentId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDoi() {
        return doi;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getUnprocessedId() {
        return unprocessedId;
    }
}
