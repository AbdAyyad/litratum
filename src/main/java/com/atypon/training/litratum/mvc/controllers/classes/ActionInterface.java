package com.atypon.training.litratum.mvc.controllers.classes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ActionInterface {
    void execute(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException;
}
