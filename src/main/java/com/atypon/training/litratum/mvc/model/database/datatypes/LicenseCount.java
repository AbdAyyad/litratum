package com.atypon.training.litratum.mvc.model.database.datatypes;

public class LicenseCount {
    private String licenseCountId;
    private int count;

    public LicenseCount(String licenseCountId, int count) {
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
