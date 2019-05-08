package com.atypon.training.litratum.controllers.tools;

import java.io.File;

public class Constants {
    private Constants() {
    }

    public final static int CONNECTION_POOL_MAX_SIZE = 100;
    public final static int CONNECTION_POOL_MIN_SIZE = 10;
    public final static String ALL_ALLOWED_RANDOM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public final static String RELATIVE_PATH = "C:\\Users\\abday\\IdeaProjects\\litratum\\web\\";
    //    public final static String RELATIVE_PATH = "/home/aayyad/IdeaProjects/litratum/web/";
    public final static String UNPROCESSED_FOLDER = Constants.RELATIVE_PATH + "unprocessed" + File.separator;
    public final static String PROCESSED_FOLDER = Constants.RELATIVE_PATH + "processed" + File.separator;
    public final static String UNZIPPED_FOLDER = Constants.UNPROCESSED_FOLDER + "unzipped" + File.separator;
    public final static String ZIPPED_FOLDER = Constants.UNPROCESSED_FOLDER + "zipped" + File.separator;
    public final static String XSL_PATH = Constants.RELATIVE_PATH + "xsl" + File.separator;
    public final static String XML_PATH = Constants.RELATIVE_PATH + "xml" + File.separator;
    public final static String DATABASE_XML_FILE = Constants.XML_PATH + "DataBase.xml";
    public final static String DATABASE_XSL_FILE = Constants.XSL_PATH + "DataBase.xsl";
    public final static String ACTIONS_MAPPING_XML_FILE = Constants.XML_PATH + "UrlMapping.xml";
    public final static String ACTIONS_MAPPING_XSL_FILE = Constants.XSL_PATH + "UrlMapping.xsl";
    public final static String ACTIONS_OBJECTS_XML_FILE = Constants.XML_PATH + "Actions.xml";
    public final static String ACTIONS_OBJECTS_XSL_FILE = Constants.XSL_PATH + "Actions.xsl";
    public final static String ARTICLE_META_XSL_FILE = Constants.XSL_PATH + "ArticleMeta.xsl";
    public final static String ARTICLE_HTML_XSL_FILE = Constants.XSL_PATH + "ArticleHtml.xsl";
    public final static String DTD_FILE = Constants.XSL_PATH + "JATS-archivearticle1.dtd";
}
