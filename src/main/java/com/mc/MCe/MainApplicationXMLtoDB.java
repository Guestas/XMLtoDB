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

			System.out.println("\n".repeat(2));
			// Ask user if he who'd like to update data if they are in database.
			System.out.print("If data in DB already exist, update/rewrite them? \nWARNIG It could rewrite all data in DB!! [y/n]");
			Scanner scanner = new Scanner(System.in);
			boolean up = scanner.nextLine().toLowerCase().charAt(0) == 'y';
			System.out.println(up?"Data in DB are going to bee rewrote if they already are in DB.":"Data are not going to be rewrote if they are already in DB.");

			//Load data from URL with method in class xmLdataservice if there is some problem method
			// return error log and stop program
			Document document = xmLdataService.downloadData("https://www.smartform.cz/download/kopidlno.xml.zip");
			if (document==null){
				return;
			}

			//Defining what data i will need and adding all to tu List for later use
			List<String> nededDataFromObce = new ArrayList<>(Arrays.asList("obi:Kod","obi:Nazev"));
			List<Map<String,String>> dataForDB = xmLdataService.agregateData(document, "vf:Obce", nededDataFromObce);

			// adding second set CastiObce
			List<String> nededDataFromCastiObce = new ArrayList<>(Arrays.asList("coi:Kod","coi:Nazev","obi:Kod"));
			dataForDB.addAll(xmLdataService.agregateData(document, "vf:CastiObci", nededDataFromCastiObce));


			dataForDB.forEach(data -> {
				// All downloaded data are in different format switch divide them and send them to services Obec and CastObce.
				// Variable up is for updating data. It was defined by user.
				switch (data.get("name")) {
					case "vf:Obec" ->
							obecService.addObec(Obec.createObec(
									data.get("obi:Nazev"),
									Integer.parseInt(data.get("obi:Kod")),
									up
							));

					case "vf:CastObce" ->
							castObceService.addCastObce(CastiObciAdd.createCastiObce(
									Integer.parseInt(data.get("coi:Kod")),
									data.get("coi:Nazev"),
									Integer.parseInt(data.get("obi:Kod")),
									up
							));
					default -> System.out.println("Nothing to find");
				}
				//System.out.println(data);
			});
			System.out.println("\n".repeat(2));
		};
	}
}
