package com.atypon.training.litratum.model.database.datatypes;

public class BackStageAdminModel {
    private String backStageAdminId;
    private String userId;

    public BackStageAdminModel(String backStageAdminId, String userId) {
        this.backStageAdminId = backStageAdminId;
        this.userId = userId;
    }

    public String getBackStageAdminId() {
        return backStageAdminId;
    }

    public String getUserId() {
        return userId;
    }
}
