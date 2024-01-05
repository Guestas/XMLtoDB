package com.mc.MCe.service;

import com.mc.MCe.entity.CastObce;
import com.mc.MCe.entity.helper.CastiObciAdd;

import java.util.List;

public interface CastObceService {
    CastObce addCastObce(CastiObciAdd castiObciAdd);
    List<CastObce> getAllCastiObce();
    CastObce getCastObceByCode(int code);

}
