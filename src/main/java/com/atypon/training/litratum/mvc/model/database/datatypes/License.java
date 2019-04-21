package com.atypon.training.litratum.mvc.model.database.datatypes;

public class License {
    private int licenseType;
    private String licenseId;
    private String actualLicenseId;
    private String timeStamp;

    public License(int licenseType, String licenseId, String actualLicenseId, String timeStamp) {
        this.licenseType = licenseType;
        this.licenseId = licenseId;
        this.actualLicenseId = actualLicenseId;
        this.timeStamp = timeStamp;
    }

    public int getLicenseType() {
        return licenseType;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public String getActualLicenseId() {
        return actualLicenseId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
