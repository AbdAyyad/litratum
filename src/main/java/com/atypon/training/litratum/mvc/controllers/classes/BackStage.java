package com.atypon.training.litratum.mvc.controllers.classes;

import com.atypon.training.litratum.Constants;
import com.atypon.training.litratum.mvc.model.database.ConnectionPool;
import com.atypon.training.litratum.mvc.model.database.daos.Dao;
import com.atypon.training.litratum.mvc.model.database.daos.UnprocessedDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

public class BackStage implements ActionInterface {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, String args) throws ServletException, IOException {

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp, String args) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/Backstage.jsp");
        dispatcher.forward(req, resp);
    }

}
