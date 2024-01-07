package com.mc.MCe.service;

import com.mc.MCe.dao.ObecDAO;
import com.mc.MCe.entity.Obec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObecServiceImpl implements ObecService {

    private final ObecDAO obecDAO;

    @Autowired
    public ObecServiceImpl(ObecDAO obecDAO){
        this.obecDAO = obecDAO;
    }

    @Override
    public Obec addObec(Obec obec) {
        return obecDAO.addObec(obec);
    }

    @Override
    public List<Obec> getAllObce() {
        return obecDAO.getAllObce();
    }

    @Override
    public Obec getObecByCode(int code) {
        return obecDAO.getObecByCode(code);
    }
}
