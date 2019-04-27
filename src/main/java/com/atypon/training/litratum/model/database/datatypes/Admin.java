package com.atypon.training.litratum.model.database.datatypes;

public class Admin{
    private String userId;
    private String adminId;

    public Admin(String userId, String adminId) {
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
