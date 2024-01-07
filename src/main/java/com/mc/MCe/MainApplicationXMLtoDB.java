package com.mc.MCe;

import com.mc.MCe.entity.Obec;
import com.mc.MCe.entity.helper.CastiObciAdd;
import com.mc.MCe.service.CastObceService;
import com.mc.MCe.service.ObecService;
import com.mc.MCe.service.XMLdataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.w3c.dom.Document;

import java.util.*;

@SpringBootApplication
public class MainApplicationXMLtoDB {

	public static void main(String[] args) {

		SpringApplication.run(MainApplicationXMLtoDB.class, args);

	}
	@Bean
	@Profile("dev")
	public CommandLineRunner commandLineRunner(ObecService obecService, CastObceService castObceService, XMLdataService xmLdataService){
		return runner -> {
			System.out.println("If data in DB already exist update them? [y/n]");
			Scanner scanner = new Scanner(System.in);
			boolean up = Character.compare(scanner.nextLine().toLowerCase().charAt(0), 'y')==0;


			Document document = xmLdataService.downloadData("https://www.smartform.cz/download/kopidlno.xml.zip");
			if (document==null){
				return;
			}


			List<String> nededObce = new ArrayList<>(Arrays.asList("obi:Kod","obi:Nazev"));
			List<Map<String,String>> ouut = xmLdataService.agregateData(document, "vf:Obce", nededObce);


			List<String> nededCastiObce = new ArrayList<>(Arrays.asList("coi:Kod","coi:Nazev","obi:Kod"));
			ouut.addAll(xmLdataService.agregateData(document, "vf:CastiObci", nededCastiObce));


			ouut.forEach(data -> {
				//System.out.println(data.get("name").split(":")[1]);
				switch (data.get("name")) {
					case "vf:Obec" ->
							obecService.addObec(Obec.createObec(
									data.get("obi:Nazev"),
									Integer.parseInt(data.get("obi:Kod")),
									up
							));

					case "vf:CastObce" ->
							castObceService.addCastObce(CastiObciAdd.createCastiObcei(
									Integer.parseInt(data.get("coi:Kod")),
									data.get("coi:Nazev"),
									Integer.parseInt(data.get("obi:Kod")),
									up
							));
					default -> System.out.println("Nothing to find");
				}
				//System.out.println(data);
			});
		};
	}
}
