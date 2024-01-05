package com.mc.MCe.dao;

import com.mc.MCe.entity.Obec;

import java.util.List;

public interface ObecDAO {
    Obec addObec(Obec obec);
    List<Obec> getAllObce();
    Obec getObecByCode(int code);
}
