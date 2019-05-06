package com.atypon.training.litratum.controllers.servlets;

import com.atypon.training.litratum.controllers.actions.IAction;
import com.atypon.training.litratum.controllers.tools.Constants;
import com.atypon.training.litratum.controllers.tools.ContentProcessingService;
import com.atypon.training.litratum.controllers.tools.XmlTransformer;
import com.atypon.training.litratum.model.xml.ActionModel;
import com.atypon.training.litratum.model.xml.XmlFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
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
    private Map<String, ActionModel> allActions;


    public static RequestDispatcher getRequestDispatcher(ServletContext context) {
        return context.getRequestDispatcher(PATH);
    }


    private void runContentProcessingService() {
        new Thread(new ContentProcessingService()).start();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        initConstants();
        initUrlMap();
        createDirectories();
        initActionMap();
        runContentProcessingService();
    }

    private void initConstants() {
        Constants.RELATIVE_PATH = this.getServletContext().getRealPath("");

        Constants.XML_PATH = Constants.RELATIVE_PATH + "xml" + File.separator;
        Constants.DATABASE_XML_FILE = Constants.XML_PATH + "DataBase.xml";
        Constants.ACTIONS_MAPPING_XML_FILE = Constants.XML_PATH + "UrlMapping.xml";
        Constants.ACTIONS_OBJECTS_XML_FILE = Constants.XML_PATH + "Actions.xml";

        Constants.XSL_PATH = Constants.RELATIVE_PATH + "xsl" + File.separator;
        Constants.DATABASE_XSL_FILE = Constants.XSL_PATH + "DataBase.xsl";
        Constants.ACTIONS_MAPPING_XSL_FILE = Constants.XSL_PATH + "UrlMapping.xsl";
        Constants.ARTICLE_META_XSL_FILE = Constants.XSL_PATH + "ArticleMeta.xsl";
        Constants.ACTIONS_OBJECTS_XSL_FILE = Constants.XSL_PATH + "Actions.xsl";
        Constants.ARTICLE_HTML_XSL_FILE = Constants.XSL_PATH + "ArticleHtml.xsl";
        Constants.DTD_FILE = Constants.XSL_PATH + "JATS-archivearticle1.dtd";

        Constants.UNPROCESSED_FOLDER = Constants.RELATIVE_PATH + "unprocessed" + File.separator;
        Constants.PROCESSED_FOLDER = Constants.RELATIVE_PATH + "processed" + File.separator;
        Constants.UNZIPPED_FOLDER = Constants.UNPROCESSED_FOLDER + "unzipped" + File.separator;
        Constants.ZIPPED_FOLDER = Constants.UNPROCESSED_FOLDER + "zipped" + File.separator;
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

        String actionName = url.endsWith("/") && !url.equals("/") ? getActionMapping(url.substring(0, url.length() - 1)) : getActionMapping(url);
        ActionModel action = allActions.get(actionName);

        Class<?> actionClass = Class.forName(action.getActionClass());
        Constructor constructor = actionClass.getConstructor();
        IAction obj = (IAction) constructor.newInstance();

        obj.execute(req, resp, action.getJsp());
    }

    private String getActionMapping(String url) {
        if (actionsMapping.containsKey(url)) {
            return actionsMapping.get(url);
        }
        return "notFound";
    }

}
