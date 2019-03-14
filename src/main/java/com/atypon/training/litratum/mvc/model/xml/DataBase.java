package com.atypon.training.litratum.mvc.model.xml;

public class DataBase {
    String host;
    String name;
    String password;
    int port;
    String user;

    public DataBase(String host, String name, String password, int port, String user) {
        this.host = host;
        this.name = name;
        this.password = password;
        this.port = port;
        this.user = user;
    }

    public String getUri() {
        StringBuilder builder = new StringBuilder();
        builder.append("jdbc:postgresql://");
        builder.append(host);
        builder.append(":");
        builder.append(port);
        builder.append("/");
        builder.append(name);

        return builder.toString();
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DataBase{");
        sb.append("host='").append(host).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", port=").append(port);
        sb.append('}');
        return sb.toString();
    }
}
