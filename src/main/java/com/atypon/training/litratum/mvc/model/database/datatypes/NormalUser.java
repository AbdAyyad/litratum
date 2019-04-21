package com.atypon.training.litratum.mvc.model.database.datatypes;

public class NormalUser{
    private String normalUserId;
    private String userId;

    public NormalUser(String normalUserId, String userId) {
        this.normalUserId = normalUserId;
        this.userId = userId;
    }

    public String getNormalUserId() {
        return normalUserId;
    }

    public String getUserId() {
        return userId;
    }
}
