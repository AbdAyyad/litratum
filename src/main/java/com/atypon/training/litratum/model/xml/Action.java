package com.atypon.training.litratum.model.xml;

public class Action {
    private String actionClass;
    private String jsp;

    protected Action(String actionClass, String jsp) {
        this.actionClass = actionClass;
        this.jsp = jsp;
    }

    public String getActionClass() {
        return actionClass;
    }

    public String getJsp() {
        return jsp;
    }

}
