package com.atypon.training.litratum.controllers.servlets;

import com.atypon.training.litratum.controllers.actions.ActionInterface;
import com.atypon.training.litratum.controllers.tools.Constants;
import com.atypon.training.litratum.controllers.tools.XmlTransformer;
import com.atypon.training.litratum.model.xml.Action;
import com.atypon.training.litratum.model.xml.XmlFactory;

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
    private Map<String, String> actionsMapping;
    private Map<String, Action> allActions;


    public static RequestDispatcher getRequestDispatcher(ServletContext context) {
        return context.getRequestDispatcher(PATH);
    }


    @Override
    public void init() throws ServletException {
        super.init();
        Constants.RELATIVE_PATH = this.getServletContext().getRealPath("");
        Constants.XSL_PATH = Constants.RELATIVE_PATH + "xsl/";
        Constants.UNPROCESSED_FOLDER = Constants.RELATIVE_PATH + "unprocessed/";
        Constants.UNZIPPED_FOLDER = Constants.UNPROCESSED_FOLDER + "unzipped/";
        Constants.ZIPPED_FOLDER = Constants.UNPROCESSED_FOLDER + "zipped/";
        initUrlMap();
        createDirectories();
        initActionMap();
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

    private void initUrlMap() {
        try {
            initUrlMapWithException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUrlMapWithException() throws Exception {
        actionsMapping = new HashMap<>();
        String xmlTransformed = XmlTransformer.getXml(Constants.ACTIONS_MAPPING_XML_FILE, Constants.ACTIONS_MAPPING_XSL_FILE);


        StringReader stringReader = new StringReader(xmlTransformed);
        BufferedReader bf = new BufferedReader(stringReader);

        String line = bf.readLine(); // <? xml version="1.0" encode="UTF-8"/>
        String key;
        String value;

        while ((line = bf.readLine()) != null) {
            key = line.trim();
            value = bf.readLine().trim();
            actionsMapping.put(key, value);
        }

    }

    private void initActionMap() {
        try {
            String transformedXml = XmlTransformer.getXml(Constants.ACTIONS_OBJECTS_XML_FILE, Constants.ACTIONS_OBJECTS_XSL_FILE);
            XmlFactory factory = new XmlFactory(transformedXml);
            allActions = factory.getAllActions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            serviceWithException(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void serviceWithException(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String url = (String) req.getAttribute("actionUrl");

        String actionName = getActionMapping(url);
        Action action = allActions.get(actionName);

        Class<?> actionClass = Class.forName(action.getActionClass());
        Constructor constructor = actionClass.getConstructor();
        ActionInterface obj = (ActionInterface) constructor.newInstance();

        obj.execute(req, resp, action.getJsp());
    }

    private String getActionMapping(String url) {
        if (actionsMapping.containsKey(url)) {
            return actionsMapping.get(url);
        }
        return "not-found";
    }

}
