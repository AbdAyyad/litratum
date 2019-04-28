package com.atypon.training.litratum.model.database.datatypes;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class User {
    private String userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private boolean isLoggedIn;

    public User(String userId, String userName, String userEmail, String userPassword, boolean isLoggedIn) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.isLoggedIn = isLoggedIn;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public boolean verifyPassword(String password) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.getBytes(), this.userPassword.getBytes());
        return result.verified;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
