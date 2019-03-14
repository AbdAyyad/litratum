package com.atypon.training.litratum.xml;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;

public class XmlParser {
    private XStream xStream;

    public XmlParser() {
        initXStream();
    }

    private void initXStream() {
        xStream = new XStream(new DomDriver());
        xStream.alias("DataBase", DataBase.class);
    }

    public Object read(String fileName) {
        fileName = "/home/aayyad/IdeaProjects/litratum/web/xml/" + fileName;
        File file = new File(fileName);
        return xStream.fromXML(file);
    }

}
