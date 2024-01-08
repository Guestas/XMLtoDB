package com.mc.MCe.dao;

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
class CastObceDAOImplTest {

    private ObecDAO obecDAO;

    @Autowired
    public CastObceDAOImplTest(ObecDAO obecDAO){
        this.obecDAO = obecDAO;
    }

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

}