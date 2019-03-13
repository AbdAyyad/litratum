package com.atypon.training.litratum.xml;

public class DataBase {
    String host;
    String name;
    String password;
    int port;

    public DataBase(String host, String name, String password, int port) {
        this.host = host;
        this.name = name;
        this.password = password;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
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
