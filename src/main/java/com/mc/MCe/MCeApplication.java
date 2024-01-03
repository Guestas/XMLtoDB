package com.mc.MCe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipInputStream;

@SpringBootApplication
public class MCeApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(MCeApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Welcome to the Simple Spring Application!");

		//BufferedInputStream in = new BufferedInputStream(new URL("https://www.smartform.cz/download/kopidlno.xml.zip").openStream());


		URL url = new URL("https://www.smartform.cz/download/kopidlno.xml.zip");
		InputStream stream = url.openStream();

		ZipInputStream zipInputStream = new ZipInputStream(stream);

		// get file from zip folder
		zipInputStream.getNextEntry();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbf.newDocumentBuilder();

		// Parse the XML file from the ZIP
		Document document = docBuilder.parse(zipInputStream);

		// Process the XML document as needed
		returnData(document, "vf:Obec",0);
		returnData(document, "vf:CastObce",0);
		//System.out.println(document.getElementsByTagName("obi:Kod").getLength());
		//System.out.println(document.getElementsByTagName("vf:CastObce").getLength());


		// Close the input streams
		zipInputStream.close();

	}

	public void returnData(Document document, String name, int in){
		Element element = (Element) document.getElementsByTagName(name).item(0);
		NodeList children = element.getChildNodes();


		// Process the child nodes
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);

			// Check if the child is an element node
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("-".repeat(in)+"Element Name: " + child.getNodeName()+" "+ in);

				if (child.getChildNodes().getLength()==1)
					System.out.println(" ".repeat(5)+"Text Content: " + child.getTextContent());
				else {
					returnData(child.getOwnerDocument(), child.getNodeName(), in+1);
				}

			}
		}
	}

}
