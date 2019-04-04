package com.atypon.training.litratum;

public class Constants {
    private Constants() {
    }

    public final static String DATABASE_XML_FILE = "xml/DataBase.xml";
    public final static String DATABASE_XSL_FILE = "xsl/DataBase.xsl";
    public final static int CONNECTION_POOL_MAX_SIZE = 100;
    public final static int CONNECTION_POOL_MIN_SIZE = 10;
    public final static String ACTIONS_XML_FILE = "xml/Actions.xml";
    public final static String ACTIONS_XSL_FILE = "xsl/action.xsl";
    public final static String ALL_ALLOWED_RANDOM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    // initialize at run time
    public static String RELATIVE_PATH;
    public static String UNPROCESSED_FOLDER;

}
