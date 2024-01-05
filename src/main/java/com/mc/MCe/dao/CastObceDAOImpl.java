package com.mc.MCe.dao;

import com.mc.MCe.entity.CastObce;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CastObceDAOImpl implements CastObceDAO {


    private final EntityManager entityManager;

    @Autowired
    public CastObceDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public CastObce addCastObce(CastObce castObce) {
        return entityManager.merge(castObce);
    }

    @Override
    public List<CastObce> getAllCastiObce() {
        TypedQuery<CastObce> query = entityManager.createQuery("SELECT c FROM CastObce c", CastObce.class);
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
            return null;
        } else {
            return resultList.get(0);
        }
    }
}
