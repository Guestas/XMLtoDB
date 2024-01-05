package com.mc.MCe.dao;

import com.mc.MCe.entity.CastObce;

import java.util.List;

public interface CastObceDAO {
    CastObce addCastObce(CastObce castObce);
    List<CastObce> getAllCastiObce();
    CastObce getCastObceByCode(int code);
}
