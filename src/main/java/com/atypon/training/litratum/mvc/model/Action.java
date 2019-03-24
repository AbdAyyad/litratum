package com.atypon.training.litratum.mvc.model;

public class Action {
    private String className;
    private String arg;

    public Action(String className, String arg) {
        this.className = className;
        this.arg = arg;
    }

    public String getClassName() {
        return className;
    }

    public String getArg() {
        return arg;
    }
}
