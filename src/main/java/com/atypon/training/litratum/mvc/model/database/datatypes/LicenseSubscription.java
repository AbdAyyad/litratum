package com.atypon.training.litratum.mvc.model.database.datatypes;

public class LicenseSubscription {
    private String licenseSubscriptionId;
    private String endDate;

    public LicenseSubscription(String licenseSubscriptionId, String endDate) {
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
