package com.atypon.training.litratum.xml;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;

public class XmlParser {
    private XStream xStream;

    public XmlParser() {
        initXsream();
    }

    private void initXsream() {
        xStream = new XStream(new DomDriver());
        xStream.alias("DataBase", DataBase.class);
    }

    public Object read(String filepath) {
        filepath = "/home/aayyad/IdeaProjects/litratum/web/xml/" + filepath;
        File file = new File(filepath);
        return xStream.fromXML(file);
    }

}
