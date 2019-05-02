package com.atypon.training.litratum.model.database.datatypes;

public class AdminModel {
    private String userId;
    private String adminId;

    public AdminModel(String userId, String adminId) {
        this.userId = userId;
        this.adminId = adminId;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getUserId() {
        return userId;
    }
}
