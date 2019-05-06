package com.atypon.training.litratum.controllers.tools;

public class Constants {
    private Constants() {
    }

    public final static int CONNECTION_POOL_MAX_SIZE = 100;
    public final static int CONNECTION_POOL_MIN_SIZE = 10;
    public final static String ALL_ALLOWED_RANDOM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";


    // initialize at run time
    public static String RELATIVE_PATH;
    public static String UNPROCESSED_FOLDER;
    public static String PROCESSED_FOLDER;
    public static String UNZIPPED_FOLDER;
    public static String ZIPPED_FOLDER;
    public static String XSL_PATH;
    public static String XML_PATH;
    public static String DATABASE_XML_FILE;
    public static String DATABASE_XSL_FILE;
    public static String ACTIONS_MAPPING_XML_FILE;
    public static String ACTIONS_MAPPING_XSL_FILE;
    public static String ACTIONS_OBJECTS_XML_FILE;
    public static String ACTIONS_OBJECTS_XSL_FILE;
    public static String ARTICLE_META_XSL_FILE;
    public static String ARTICLE_HTML_XSL_FILE;
    public static String DTD_FILE;
}
