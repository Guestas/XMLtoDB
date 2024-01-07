package com.mc.MCe.dao;

import com.mc.MCe.entity.CastObce;
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
public class CastObceDAOImpl implements CastObceDAO {

    protected static final Logger logger = LogManager.getLogger(XMLdataServiceImpl.class);
    private final EntityManager entityManager;

    @Autowired
    public CastObceDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public CastObce addCastObce(CastObce castObce) {

        if (entityManager.find(CastObce.class, castObce.getCode())!=null && castObce.getUp()) {
            logger.warn("Already exist updating values Kod CastiObce: " + castObce.getCode());
            return entityManager.merge(castObce);
        } else if (entityManager.find(CastObce.class, castObce.getCode())==null) {
            logger.info("Adding new CastObce: " + castObce.getCode());
            return entityManager.merge(castObce);
        } else {
            logger.warn("Already exist values not used Kod CastiObce: " + castObce.getCode());
            return null;
        }
    }

    @Override
    public List<CastObce> getAllCastiObce() {
        TypedQuery<CastObce> query = entityManager
                .createQuery("SELECT c FROM CastObce c", CastObce.class);
        List<CastObce> result = query.getResultList();
        return result.isEmpty()?null:result;
    }

    @Override
    public CastObce getCastObceByCode(int code) {
        TypedQuery<CastObce> query = entityManager
                .createQuery("SELECT c FROM CastObce c WHERE code=:thedata", CastObce.class)
                .setParameter("thedata", code);

        List<CastObce> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            logger.warn("No CastObce found for id: " + code);
            return null;
        } else {
            return resultList.get(0);
        }
    }
}
