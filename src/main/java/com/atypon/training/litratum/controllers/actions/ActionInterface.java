package com.atypon.training.litratum.controllers.actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ActionInterface {
    void execute(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException;
}
