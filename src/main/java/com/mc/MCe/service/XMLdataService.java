package com.mc.MCe.service;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface XMLdataService {
    Document downloadData(String url) throws IOException, ParserConfigurationException, SAXException;
    List<Map<String, String>> agregateData(Document document, String name, List<String> list);
}
