package com.atypon.training.litratum.model.database.datatypes;

public class NormalUser{
    private String normalUserId;
    private String userId;
    private String LicenseId;

    public NormalUser(String normalUserId, String userId, String licenseId) {
        this.normalUserId = normalUserId;
        this.userId = userId;
        LicenseId = licenseId;
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
