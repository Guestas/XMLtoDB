package com.mc.MCe.service;

import com.mc.MCe.dao.CastObceDAO;
import com.mc.MCe.dao.ObecDAO;
import com.mc.MCe.entity.CastObce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CastObceServiceImpl implements CastObceService {
    //connecting main with database functions which are in dao file
    private final ObecDAO obecDAO;
    private final CastObceDAO castObceDAO;
    @Autowired
    public CastObceServiceImpl(ObecDAO obecDAO, CastObceDAO castObceDAO){
        this.obecDAO = obecDAO;
        this.castObceDAO = castObceDAO;
    }

    @Override
    public CastObce addCastObce(CastObce castObce) {
        castObce.setVillageCode(obecDAO.getObecByCode(castObce.getVillageCodeNumber()));
        return castObceDAO.addCastObce(castObce);
    }

    @Override
    public List<CastObce> getAllCastiObce() {
        return castObceDAO.getAllCastiObce();
    }

    @Override
    public CastObce getCastObceByCode(int code) {
        return castObceDAO.getCastObceByCode(code);
    }
}
