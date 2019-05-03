package com.atypon.training.litratum.model.database.datamodel;

public class LicenseCountModel {
    private String licenseCountId;
    private int count;

    public LicenseCountModel(String licenseCountId, int count) {
        this.licenseCountId = licenseCountId;
        this.count = count;
    }

    public String getLicenseCountId() {
        return licenseCountId;
    }

    public int getCount() {
        return count;
    }
}
