package com.mc.MCe.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

@Service
public class XMLdataServiceImpl implements XMLdataService{

    // in this class I defined downloading data from web unziping them it is in first method and second method is for parsing them
    //

    protected static final Logger logger = LogManager.getLogger(XMLdataServiceImpl.class);

    @Override
    public Document downloadData(String urlS) throws IOException, ParserConfigurationException, SAXException {
        try {
            URL url = new URL(urlS);
            try  {
                InputStream stream = url.openStream();
                ZipInputStream zipInputStream = new ZipInputStream(stream);

                // get file from zip folder
                zipInputStream.getNextEntry();

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = dbf.newDocumentBuilder();

                Document document = docBuilder.parse(zipInputStream);
                // Parse the XML file from the ZIP
                return document;
            } catch (Exception in){
                in.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // this method get document, name what will be used for extraction and elements which will be saved to database.
    //function returns all extracted values in list
    @Override
    public List<Map<String, String>> agregateData(Document document, String name, List<String> list) {
        List<Map<String, String>> out = new ArrayList<>();

        Element elemen = (Element) document.getElementsByTagName(name).item(0);
        NodeList children = elemen.getChildNodes();

        // this cycle going through all elements for example CastiObce there is 5 of them and get needed data by tag name
        for (int i = 0; i < children.getLength(); i++) {
            Map<String, String> elementMap = new HashMap<>();
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) child;
                elementMap.put("name",element.getNodeName());
                //System.out.println(element.getNodeName());
                list.forEach((data) -> {
                    if (element.getElementsByTagName(data).item(0) != null){
                        Node dataNode = element.getElementsByTagName(data).item(0);
                        elementMap.put(dataNode.getNodeName(), dataNode.getTextContent());
                        //System.out.println(dataNode.getNodeName() + " " + dataNode.getTextContent());
                    }
                });
                out.add(elementMap);
            }
        }
        return out;
    }
}
