package com.mc.MCe.dao;

import com.mc.MCe.entity.CastObce;
import com.mc.MCe.entity.Obec;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
class ObecDAOImplTest {

    private ObecDAO obecDAO;
    private CastObceDAO castObceDAO;

    @Autowired
    public ObecDAOImplTest(ObecDAO obecDAO, CastObceDAO castObceDAO){
        this.obecDAO = obecDAO;
        this.castObceDAO = castObceDAO;
    }

	/*
			Obec methods
	*/


    //testing adding "cast obce" to db with villageCode which not in obce DB should work ok without problem
    @Test
    void testSaveAndRetrieveCastiObce0() {
        CastObce castObce = CastObce.createCastObce(11, "Cast Obce0", 5, true);

        castObceDAO.addCastObce(castObce);

        CastObce retrive = castObceDAO.getCastObceByCode(castObce.getCode());

        assertNotNull(retrive);
        assertEquals(retrive.getName(), castObce.getName());
    }

    //testing adding "cast obce" to db with village which is null to DB should work ok without problem
    @Test
    void testSaveAndRetrieveCastiObce1() {
        CastObce castObce = CastObce.createCastObce(12, "Cast Obce1", 45, true);

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

        CastObce castObce = CastObce.createCastObce(13, "Cast Obce2", 153, true);

        castObce.setVillageCode(obecDAO.getObecByCode(castObce.getVillageCodeNumber()));

        castObceDAO.addCastObce(castObce);

        CastObce retrive = castObceDAO.getCastObceByCode(castObce.getCode());

        assertNotNull(retrive);
        assertEquals(retrive.getName(), castObce.getName());
    }

    //testing adding "cast obce" to db with villageCode which is in obce to DB
    @Test
    public void testGetAllCastiObce() {
        CastObce castObce1 = CastObce
                .createCastObce(1, "Test3co1", 4, true);

        CastObce castObce2 = CastObce
                .createCastObce(2, "Test3co2", 4, true);

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