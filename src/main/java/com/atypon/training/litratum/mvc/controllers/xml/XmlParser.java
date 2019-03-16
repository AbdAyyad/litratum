package com.atypon.training.litratum.mvc.controllers.xml;

import com.atypon.training.litratum.Constants;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringWriter;

public final class XmlParser {
    private XmlParser() {
    }

    public static String getXml(String xmlPath, String xslPath) {
        try {
            return getXmlWithException(xmlPath, xslPath);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getXmlWithException(String xmlPath, String xslPath) throws Exception {
        Document document;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        StringWriter stringWriter = new StringWriter();

        File xml = new File(Constants.RELATIVE_PATH + xmlPath);
        File xsl = new File(Constants.RELATIVE_PATH + xslPath);

        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(xml);

        // Use a Transformer for output
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        StreamSource style = new StreamSource(xsl);
        Transformer transformer = transformerFactory.newTransformer(style);
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(stringWriter);
        transformer.transform(source, result);
        return stringWriter.toString();
    }
}
