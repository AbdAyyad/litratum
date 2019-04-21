package com.atypon.training.litratum.mvc.model.database.datatypes;

public class LicenseSubscription{
    private String licenseSubscriptionId;
    private String endDate;
    private String licenseId;

    public LicenseSubscription(String licenseSubscriptionId, String endDate, String licenseId) {
        this.licenseSubscriptionId = licenseSubscriptionId;
        this.endDate = endDate;
        this.licenseId = licenseId;
    }

    public String getLicenseSubscriptionId() {
        return licenseSubscriptionId;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getLicenseId() {
        return licenseId;
    }
}
