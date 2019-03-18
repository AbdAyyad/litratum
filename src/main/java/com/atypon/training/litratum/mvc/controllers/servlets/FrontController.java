package com.atypon.training.litratum.mvc.controllers.servlets;

import com.atypon.training.litratum.Constants;
import com.atypon.training.litratum.mvc.controllers.classes.ActionInterface;
import com.atypon.training.litratum.xml.XmlParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "front Servlet", urlPatterns = {"/front"})
public class FrontController extends HttpServlet {
    private Map<String, String> actionsClassMap;

    @Override
    public void init() throws ServletException {
        super.init();
        Constants.RELATIVE_PATH = this.getServletContext().getRealPath("");
        initMap();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void serviceWithException(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String actionName = getAction();
        Class<?> actionClass = Class.forName(actionsClassMap.get(actionName));
        Constructor constructor = actionClass.getConstructor();
        ActionInterface obj = (ActionInterface) constructor.newInstance();
        obj.execute(req, resp);
    }

    private String getAction() {
        return "sign_up";
    }

}
