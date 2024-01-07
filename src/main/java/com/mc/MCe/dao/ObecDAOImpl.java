package com.mc.MCe.dao;

import com.mc.MCe.entity.Obec;
import com.mc.MCe.service.XMLdataServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ObecDAOImpl implements ObecDAO{

    protected static final Logger logger = LogManager.getLogger(XMLdataServiceImpl.class);

    private final EntityManager entityManager;
    @Autowired
    public ObecDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public Obec addObec(Obec obec) {
        if (entityManager.find(Obec.class, obec.getCode())!=null && obec.getUp()) {
            logger.warn("Already exist updating values Kod Obce: " + obec.getCode());
            return entityManager.merge(obec);
        } else if (entityManager.find(Obec.class, obec.getCode())==null) {
            logger.info("Adding new Obec: " + obec.getCode());
            return entityManager.merge(obec);
        } else {
            logger.warn("Already exist values not used Kod Obce: " + obec.getCode());
            return null;
        }
    }

    @Override
    public List<Obec> getAllObce() {

        TypedQuery<Obec> query = entityManager
                .createQuery("SELECT o FROM Obec o", Obec.class);
        List<Obec> result = query.getResultList();
        return result.isEmpty()?null:result;
    }

    @Override
    public Obec getObecByCode(int code) {
        TypedQuery<Obec> query = entityManager
                .createQuery("SELECT o FROM Obec o WHERE code=:thedata", Obec.class)
                .setParameter("thedata", code);

        List<Obec> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            logger.warn("No Obce found for id: " + code);
            return null;
        } else {
            //System.out.println("ID is: "+resultList.get(0).getCode());
            return resultList.get(0);
        }
    }
}
