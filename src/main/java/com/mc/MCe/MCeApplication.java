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

		long k = System.currentTimeMillis()-startTime;

		startTime = System.currentTimeMillis();
		//returnData(document, "vf:Obce",0);
		//returnData(document, "vf:CastiObci",0);
		System.out.println(System.currentTimeMillis()-startTime +" "+ k);


		// Close the input streams
		zipInputStream.close();

	}

	void returnData(Document document, String name, int in){
		Element element = (Element) document.getElementsByTagName(name).item(0);
		NodeList children = element.getChildNodes();


		// Process the child nodes
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);

			// Check if the child is an element node
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				//return name which will be later used for sorting
				System.out.println("-".repeat(in)+"Element Name: " + child.getNodeName()+" "+ in);

				//if node is final it will return text context later it will be data for db
				if (child.getChildNodes().getLength()==1)
					System.out.println(" ".repeat(5)+"Text Content: " + child.getTextContent());
				else {
					returnData(child.getOwnerDocument(), child.getNodeName(), in+1);
				}

			}
		}
	}

	void returnData2(Document document, String name, int in){
		Element element = (Element) document.getElementsByTagName(name).item(0);
		NodeList children = element.getChildNodes();


		List<Node> nodes = IntStream.range(0, children.getLength())
				.mapToObj(children::item)
				.filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
				.toList();

// Use forEach with lambda expression on the List
		nodes.forEach(node -> {
			// System.out.println(node.getNodeName());
			//return name which will be later used for sorting
			System.out.println("-".repeat(in)+"Element Name: " + node.getNodeName()+" "+ in);

			//if node is final it will return text context later it will be data for db
			if (node.getChildNodes().getLength()==1)
				System.out.println(" ".repeat(5)+"Text Content: " + node.getTextContent());
			else {
				returnData2(node.getOwnerDocument(), node.getNodeName(), in+1);
			}
		});

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



/*
<vf:Obec gml:id="OB.573060">
            <obi:Kod>573060</obi:Kod>
            <obi:Nazev>Kopidlno</obi:Nazev>
            <obi:StatusKod>3</obi:StatusKod>
            <obi:Okres>
               <oki:Kod>3604</oki:Kod>
            </obi:Okres>
            <obi:Pou>
               <pui:Kod>2186</pui:Kod>
            </obi:Pou>
            <obi:PlatiOd>2019-07-10T00:00:00</obi:PlatiOd>
            <obi:IdTransakce>2937100</obi:IdTransakce>
            <obi:GlobalniIdNavrhuZmeny>2042164</obi:GlobalniIdNavrhuZmeny>
            <obi:MluvnickeCharakteristiky>
               <com:Pad2>Kopidlna</com:Pad2>
               <com:Pad3>Kopidlnu</com:Pad3>
               <com:Pad4>Kopidlno</com:Pad4>
               <com:Pad6>Kopidlně</com:Pad6>
               <com:Pad7>Kopidlnem</com:Pad7>
            </obi:MluvnickeCharakteristiky>
            <obi:NutsLau>CZ0522573060</obi:NutsLau>
            <obi:Geometrie>
               <obi:DefinicniBod>
                  <gml:MultiPoint gml:id="DOB.573060"
                                  srsName="urn:ogc:def:crs:EPSG::5514"
                                  srsDimension="2">
                     <gml:pointMembers>
                        <gml:Point gml:id="DOB.573060.1">
                           <gml:pos>-679188.00 -1024096.00</gml:pos>
                        </gml:Point>
                     </gml:pointMembers>
                  </gml:MultiPoint>
               </obi:DefinicniBod>
            </obi:Geometrie>
         </vf:Obec>
*/