package com.atypon.training.litratum.mvc.controllers.servlets;

import com.atypon.training.litratum.Constants;
import com.atypon.training.litratum.mvc.controllers.classes.ActionInterface;
import com.atypon.training.litratum.mvc.model.Action;
import com.atypon.training.litratum.xml.XmlParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


@WebServlet(name = "front Servlet", urlPatterns = {"/r/*"})
public class FrontController extends HttpServlet {
    private Map<String, String> actionsClassMap;

    @Override
    public void init() throws ServletException {
        super.init();
        Constants.RELATIVE_PATH = this.getServletContext().getRealPath("");
        Constants.UNPROCESSED_FOLDER = Constants.RELATIVE_PATH + "unprocessed/";
        Constants.UNZIPPED_FOLDER = Constants.UNPROCESSED_FOLDER + "unzipped/";
        Constants.ZIPPED_FOLDER = Constants.UNPROCESSED_FOLDER + "zipped/";
        initMap();
        createDirectories();
    }

    private void createDirectories() {
        try {
            createDirectoriesWithException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDirectoriesWithException() throws IOException {
        Path workingDirectory = Paths.get(Constants.UNPROCESSED_FOLDER);
        if (!Files.exists(workingDirectory)) {
            Files.createDirectory(workingDirectory);
        }
    }

    private void initMap() {
        try {
            initMapWithException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initMapWithException() throws IOException {
        actionsClassMap = new HashMap<>();
        String xmlTransformed = XmlParser.getXml(Constants.ACTIONS_XML_FILE, Constants.ACTIONS_XSL_FILE);
        StringReader stringReader = new StringReader(xmlTransformed);
        BufferedReader bf = new BufferedReader(stringReader);

        String line = bf.readLine(); // <? xml version="1.0" encode="UTF-8"/>
        String key;
        String value;

        while ((line = bf.readLine()) != null) {
            key = line.trim();
            value = bf.readLine().trim();
            actionsClassMap.put(key, value);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            serviceWithException(req, resp);
        } catch (ClassNotFoundException e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/NotFound.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void serviceWithException(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Action action = getAction(req);
        Class<?> actionClass = Class.forName(actionsClassMap.get(action.getClassName()));
        Constructor constructor = actionClass.getConstructor();
        ActionInterface obj = (ActionInterface) constructor.newInstance();
        obj.execute(req, resp, action.getArg());
    }

    private Action getAction(HttpServletRequest req) {
        int idx = req.getRequestURI().indexOf("/r/");
        String url = req.getRequestURI().substring(idx + 3);
        StringTokenizer tok = new StringTokenizer(url, "/");
        String className = tok.nextToken();
        String args = "";
        if (tok.hasMoreTokens()) {
            args = tok.nextToken();
        }
        return new Action(className, args);
    }


}
