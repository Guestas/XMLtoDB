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
import java.util.List;
import java.util.stream.IntStream;
import java.util.zip.ZipInputStream;

@SpringBootApplication
public class MCeApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(MCeApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

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
		long startTime = System.currentTimeMillis();
		returnData3(document, "vf:Obce",0);
		returnData3(document, "vf:CastiObci",0);
		System.out.println(System.currentTimeMillis()-startTime);


		// Close the input streams
		zipInputStream.close();

	}

	void returnData3(Document document, String name, int in){
		Element element = (Element) document.getElementsByTagName(name).item(0);
		NodeList children = element.getChildNodes();
		System.out.println(children.getLength());
		getChild(children, in + 1);
	}

	void getChild(NodeList children, int in){
		List<Node> nodes = IntStream.range(0, children.getLength())
				.mapToObj(children::item)
				.filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
				.toList();

		nodes.forEach(node -> {
			System.out.println(" ".repeat(in)+node);
			if (node.getChildNodes().getLength()>1)
				getChild(node.getChildNodes(), in +1);
			else
				System.out.println(" ".repeat(in+1)+node.getTextContent());
		});
	}
}
