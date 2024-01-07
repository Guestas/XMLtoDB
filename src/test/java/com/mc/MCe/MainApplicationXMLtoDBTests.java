package com.mc.MCe;

import com.mc.MCe.dao.CastObceDAO;
import com.mc.MCe.dao.ObecDAO;
import com.mc.MCe.entity.CastObce;
import com.mc.MCe.entity.Obec;
import com.mc.MCe.entity.helper.CastiObciAdd;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
class MainApplicationXMLtoDBTests {

	private ObecDAO obecDAO;
	private CastObceDAO castObceDAO;

	@Autowired
	public MainApplicationXMLtoDBTests(ObecDAO obecDAO, CastObceDAO castObceDAO){
		this.obecDAO = obecDAO;
		this.castObceDAO = castObceDAO;
	}

	/*
			Obec methods
	*/

	//testing adding "obec" to DB and after loading it by id
	@Test
	void testSaveAndRetrieveObec() {
		Obec obec = Obec.createObec("Obec", 152, true);
		obecDAO.addObec(obec);

		Obec retrive = obecDAO.getObecByCode(obec.getCode());

		assertNotNull(retrive);
		assertEquals(retrive.getName(), obec.getName());
	}

	//testing get all "obce"
	@Test
	public void testGetAllObce() {
		Obec obec1 = Obec.createObec("Test3o1",1,true);

		Obec obec2 = Obec.createObec("Test3o2",2,true);

		List<Obec> expectedObecList = Arrays.asList(obec1, obec2);

		expectedObecList.forEach(obec -> obecDAO.addObec(obec));


		// Call the method to be tested
		List<Obec> actualObecList = obecDAO.getAllObce();

		// Assertions
		assertNotNull(actualObecList);
		assertEquals(obec1.getName(), actualObecList.get(0).getName());
		assertEquals(obec2.getName(), actualObecList.get(1).getName());
	}


	//testing adding "cast obce" to db with villageCode which not in obce DB should work ok without problem
	@Test
	void testSaveAndRetrieveCastiObce0() {
		CastiObciAdd castiObciAdd = CastiObciAdd.createCastiObce(11, "Cast Obce0", 5, true);

		CastObce castObce = castiObciAdd.getCastObcewithoutVilageCode();
		castObceDAO.addCastObce(castObce);

		CastObce retrive = castObceDAO.getCastObceByCode(castObce.getCode());

		assertNotNull(retrive);
		assertEquals(retrive.getName(), castObce.getName());
	}

	//testing adding "cast obce" to db with village which is null to DB should work ok without problem
	@Test
	void testSaveAndRetrieveCastiObce1() {
		CastiObciAdd castiObciAdd = CastiObciAdd.createCastiObce(12, "Cast Obce1", 45, true);

		CastObce castObce = castiObciAdd.getCastObcewithoutVilageCode();
		castObceDAO.addCastObce(castObce);

		CastObce retrive = castObceDAO.getCastObceByCode(castObce.getCode());

		assertNotNull(retrive);
		assertNull(retrive.getVillageCode());
	}


	/*
			CastiObce methods
	*/
	//testing adding "cast obce" to db with villageCode which is in obce to DB
	@Test
	void testSaveAndRetrieveCastiObce2() {
		Obec obec = Obec.createObec("Obec2", 153, true);
		obecDAO.addObec(obec);

		CastiObciAdd castiObciAdd = CastiObciAdd.createCastiObce(13, "Cast Obce2", 153, true);

		CastObce castObce = castiObciAdd.getCastObcewithoutVilageCode();
		castObce.setVillageCode(obecDAO.getObecByCode(153));

		castObceDAO.addCastObce(castObce);

		CastObce retrive = castObceDAO.getCastObceByCode(castObce.getCode());

		assertNotNull(retrive);
		assertEquals(retrive.getName(), castiObciAdd.getName());
	}

	//testing adding "cast obce" to db with villageCode which is in obce to DB
	@Test
	public void testGetAllCastiObce() {
		CastObce castObce1 = CastiObciAdd
				.createCastiObce(1, "Test3co1", 4, true)
				.getCastObcewithoutVilageCode();

		CastObce castObce2 = CastiObciAdd
				.createCastiObce(2, "Test3co2", 4, true)
				.getCastObcewithoutVilageCode();

		List<CastObce> expectedObecList = Arrays.asList(castObce1, castObce2);

		expectedObecList.forEach(castObec -> castObceDAO.addCastObce(castObec));


		// Call the method to be tested
		List<CastObce> actualObecList = castObceDAO.getAllCastiObce();

		// Assertions
		assertNotNull(actualObecList);
		assertEquals(castObce1.getName(), actualObecList.get(0).getName());
		assertEquals(castObce2.getName(), actualObecList.get(1).getName());
	}


}
