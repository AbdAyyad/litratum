package com.atypon.training.litratum.model.database.datamodel;

public class LicenseSubscriptionModel {
    private String licenseSubscriptionId;
    private String endDate;

    public LicenseSubscriptionModel(String licenseSubscriptionId, String endDate) {
        this.licenseSubscriptionId = licenseSubscriptionId;
        this.endDate = endDate;
    }

    public String getLicenseSubscriptionId() {
        return licenseSubscriptionId;
    }

    public String getEndDate() {
        return endDate;
    }
}
