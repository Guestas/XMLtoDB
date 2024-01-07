package com.mc.MCe.service;

import com.mc.MCe.dao.CastObceDAO;
import com.mc.MCe.dao.ObecDAO;
import com.mc.MCe.entity.CastObce;
import com.mc.MCe.entity.helper.CastiObciAdd;
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
    public CastObce addCastObce(CastiObciAdd castiObciAdd) {
        CastObce o = castiObciAdd.getCastObcewithoutVilageCode();
        o.setVillageCode(obecDAO.getObecByCode(castiObciAdd.getVillageCode()));
        return castObceDAO.addCastObce(o);
    }

    @Override
    public List<CastObce> getAllCastiObce() {
        return null;
    }

    @Override
    public CastObce getCastObceByCode(int code) {
        return castObceDAO.getCastObceByCode(code);
    }
}
