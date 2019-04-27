package com.atypon.training.litratum.controllers.tools;

public class Constants {
    private Constants() {
    }

    public final static String DATABASE_XML_FILE = "xmlobject/DataBase.xmlobject";
    public final static String DATABASE_XSL_FILE = "xsl/DataBase.xsl";
    public final static int CONNECTION_POOL_MAX_SIZE = 100;
    public final static int CONNECTION_POOL_MIN_SIZE = 10;
    public final static String ACTIONS_XML_FILE = "xmlobject/Actions.xmlobject";
    public final static String ACTIONS_XSL_FILE = "xsl/Action.xsl";
    public final static String ALL_ALLOWED_RANDOM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    // initialize at run time
    public static String RELATIVE_PATH;
    public static String UNPROCESSED_FOLDER;
    public static String UNZIPPED_FOLDER;
    public static String ZIPPED_FOLDER;
    public static String XSL_PATH;

}
