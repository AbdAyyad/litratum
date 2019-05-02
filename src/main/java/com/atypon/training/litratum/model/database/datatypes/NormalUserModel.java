package com.atypon.training.litratum.model.database.datatypes;

public class NormalUserModel {
    private String normalUserId;
    private String userId;
    private String LicenseId;

    public NormalUserModel(String normalUserId, String userId, String licenseId) {
        this.normalUserId = normalUserId;
        this.userId = userId;
        LicenseId = licenseId;
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
        return LicenseId;
    }
}
