package com.mc.MCe.service;

import com.mc.MCe.entity.CastObce;

import java.util.List;

public interface CastObceService {
    CastObce addCastObce(CastObce castObce);
    List<CastObce> getAllCastiObce();
    CastObce getCastObceByCode(int code);

}
