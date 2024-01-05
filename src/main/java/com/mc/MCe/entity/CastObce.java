package com.mc.MCe.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CastObce")
public class CastObce {
    @Id
    @Column(name="Kod")
    private Integer code;

    @Column(name="Nazev")
    private String name;

    @ManyToOne
    @JoinColumn(name="Kod_Obce")
    private Obec villageCode;

    public CastObce() {
    }

    public CastObce(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public CastObce(int code, String name, Obec villageCode) {
        this.code = code;
        this.name = name;
        this.villageCode = villageCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Obec getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(Obec villageCode) {
        this.villageCode = villageCode;
    }

    @Override
    public String toString() {
        return " code: " + code + " name: " + name;
    }
}
