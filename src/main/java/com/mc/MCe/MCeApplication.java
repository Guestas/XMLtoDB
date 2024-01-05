package com.mc.MCe;

import com.mc.MCe.entity.Obec;
import com.mc.MCe.entity.helper.CastiObciAdd;
import com.mc.MCe.service.CastObceService;
import com.mc.MCe.service.ObecService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipInputStream;

@SpringBootApplication
public class MCeApplication {

	public static void main(String[] args) {

		SpringApplication.run(MCeApplication.class, args);

	}
	@Bean
	public CommandLineRunner commandLineRunner(ObecService obecService, CastObceService castObceService){
		return runner -> {
/*
			Scanner scanner = new Scanner(System.in);
			String name = scanner.nextLine();
			System.out.println(name);*/

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
			List<String> nededObce = new ArrayList<>(Arrays.asList("obi:Kod","obi:Nazev"));
			get(document, "vf:Obce", nededObce);
			System.out.println("-".repeat(200));

			List<String> nededCastiObce = new ArrayList<>(Arrays.asList("coi:Kod","coi:Nazev","obi:Kod"));
			get(document, "vf:CastiObci", nededCastiObce);
			System.out.println(System.currentTimeMillis()-startTime);

			// Close the input streams
			zipInputStream.close();

			Obec o = new Obec("Name", 1562);
			obecService.addObec(o);
			System.out.println(obecService.getAllObce());

			System.out.println(castObceService.addCastObce(new CastiObciAdd(5,"asdf",1563)));
			castObceService.getCastObceByCode(4);
		};
	}

	static void returnData(Document document, String name){
		Element element = (Element) document.getElementsByTagName(name).item(0);
		NodeList children = element.getChildNodes();

		getChild(children, 0);



	}

	static void get(Document document, String name, List<String> list){
		Element element = (Element) document.getElementsByTagName(name).item(0);
		NodeList children = element.getChildNodes();

		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println(child.getNodeName());
				Element e = (Element) child.getChildNodes();
				list.forEach((data) -> {
					Node n = e.getElementsByTagName(data).item(0);
					if (n != null)
						System.out.println(n.getNodeName() + " " + n.getTextContent());
				});


			}
		}
	}

	static void getChild(NodeList children, int in){
		List<String> nededCastiObce = new ArrayList<>(Arrays.asList("coi:Kod","coi:Nazev","obi:Kod"));


		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);

			// Check if the child is an element node

			if (child.getNodeType() == Node.ELEMENT_NODE) {
				System.out.print(child.getNodeName() + " -> ");

				if (child.getChildNodes().getLength() > 1)
					getChild(child.getChildNodes(), in + 1);
				else {
					System.out.println(child.getNodeName() + " -> " + child.getTextContent() + "\n");
				}

			}
		}
	}
}
