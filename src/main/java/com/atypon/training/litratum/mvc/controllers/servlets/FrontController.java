package com.atypon.training.litratum.mvc.controllers.servlets;

import com.atypon.training.litratum.Constants;
import com.atypon.training.litratum.mvc.controllers.actions.ActionInterface;
import com.atypon.training.litratum.mvc.model.Action;
import com.atypon.training.litratum.xml.XmlParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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


@WebServlet(name = "front Servlet", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    private static final String PATH = "/FrontController";
    private static Action action;


    public static RequestDispatcher getRequestDispatcher(ServletContext context) {
        return context.getRequestDispatcher(PATH);
    }

    private Map<String, String> actionsClassMap;

    @Override
    public void init() throws ServletException {
        super.init();
        Constants.RELATIVE_PATH = this.getServletContext().getRealPath("");
        Constants.XSL_PATH = Constants.RELATIVE_PATH + "xsl/";
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
        Action action = getAction();
        Class<?> actionClass = Class.forName(actionsClassMap.get(action.getClassName()));
        Constructor constructor = actionClass.getConstructor();
        ActionInterface obj = (ActionInterface) constructor.newInstance();
        obj.execute(req, resp, action.getArg());
    }

    private synchronized static Action getAction() {
        return action;
    }

    public synchronized static void setAction(Action action) {
        FrontController.action = action;
    }


}
