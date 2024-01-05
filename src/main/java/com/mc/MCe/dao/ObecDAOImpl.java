package com.mc.MCe.dao;

import com.mc.MCe.entity.Obec;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ObecDAOImpl implements ObecDAO{

    private final EntityManager entityManager;
    @Autowired
    public ObecDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public Obec addObec(Obec obec) {

        return entityManager.merge(obec);
    }

    @Override
    public List<Obec> getAllObce() {

        TypedQuery<Obec> query = entityManager.createQuery("SELECT o FROM Obec o", Obec.class);
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
            System.out.println("No Obce found for id: " + code);
            return null;
        } else {
            System.out.println("ID is: "+resultList.get(0).getCode());
            return resultList.get(0);
        }
    }
}
