package com.atypon.training.litratum.mvc.model.database;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean verifyPassword(String password) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), getPassword());
        return result.verified;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("username:'").append(username).append('\'');
        sb.append(", password:'").append(password).append('\'');
        sb.append(", email:'").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
