package com.atypon.training.litratum.mvc.model.database;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
        sb.append('}');
        return sb.toString();
    }
}
