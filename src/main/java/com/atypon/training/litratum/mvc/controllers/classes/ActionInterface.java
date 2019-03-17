package com.atypon.training.litratum.mvc.controllers.classes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionInterface {
    void execute(HttpServletRequest req, HttpServletResponse resp);
}
