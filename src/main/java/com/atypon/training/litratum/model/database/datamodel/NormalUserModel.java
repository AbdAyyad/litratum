package com.atypon.training.litratum.model.database.datamodel;

public class NormalUserModel {
    private String normalUserId;
    private String userId;
    private String licenseId;

    public NormalUserModel(String normalUserId, String userId, String licenseId) {
        this.normalUserId = normalUserId;
        this.userId = userId;
        this.licenseId = licenseId;
    }

    public NormalUserModel(String normalUserId, String userId) {
        this.normalUserId = normalUserId;
        this.userId = userId;
    }

    public String getNormalUserId() {
        return normalUserId;
    }

    public String getUserId() {
        return userId;
    }

    public String getLicenseId() {
        return licenseId;
    }
}
