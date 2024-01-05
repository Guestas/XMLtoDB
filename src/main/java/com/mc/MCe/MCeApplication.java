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
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class MCeApplication {

	public static void main(String[] args) {

		SpringApplication.run(MCeApplication.class, args);

	}
	@Bean
	public CommandLineRunner commandLineRunner(ObecService obecService, CastObceService castObceService, XMLdataService xmLdataService){
		return runner -> {
/*
			Scanner scanner = new Scanner(System.in);
			String name = scanner.nextLine();
			System.out.println(name);*/


			Document document = xmLdataService.downloadData("https://www.smartform.cz/download/kopidlno.xml.zip");

			// Process the XML document as needed
			long startTime = System.currentTimeMillis();

			List<String> nededObce = new ArrayList<>(Arrays.asList("obi:Kod","obi:Nazev"));
			List<Map<String,String>> ouut = xmLdataService.agregateData(document, "vf:Obce", nededObce);


			List<String> nededCastiObce = new ArrayList<>(Arrays.asList("coi:Kod","coi:Nazev","obi:Kod"));
			ouut.addAll(xmLdataService.agregateData(document, "vf:CastiObci", nededCastiObce));

			System.out.println(System.currentTimeMillis()-startTime);

			ouut.forEach(data -> {
				//System.out.println(data.get("name").split(":")[1]);

				switch (data.get("name")) {
					case "vf:Obec" ->
							obecService.addObec(Obec.createObec(
									data.get("obi:Nazev"),
									Integer.parseInt(data.get("obi:Kod"))
							));

					case "vf:CastObce" ->
							castObceService.addCastObce(CastiObciAdd.createCastiObcei(
									Integer.parseInt(data.get("coi:Kod")),
									data.get("coi:Nazev"),
									Integer.parseInt(data.get("obi:Kod"))
							));
					default -> System.out.println("Nothing to find");
				}



				System.out.println(data);
			});



			//Obec o = new Obec("Name", 1562);
			//obecService.addObec(o);
			//System.out.println(obecService.getAllObce());

			//System.out.println(castObceService.addCastObce(new CastiObciAdd(5,"asdf",1563)));
			//castObceService.getCastObceByCode(4);
		};
	}
}
