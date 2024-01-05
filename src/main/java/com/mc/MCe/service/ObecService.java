package com.mc.MCe.service;

import com.mc.MCe.entity.Obec;

import java.util.List;

public interface ObecService {
    Obec addObec(Obec obec);
    List<Obec> getAllObce();
    Obec getObecByCode(int code);
}
