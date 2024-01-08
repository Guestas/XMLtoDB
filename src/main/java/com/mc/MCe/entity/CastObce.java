package com.mc.MCe.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CastObce")
public class CastObce {

    //defining entity data for database and their naming boolean up is for updating which is defined by user
    @Id
    @Column(name="Kod")
    private Integer code;

    @Column(name="Nazev")
    private String name;

    @ManyToOne
    @JoinColumn(name="Kod_Obce", referencedColumnName = "Kod")
    private Obec villageCodeObec;

    @Transient
    private int villageCodeNumber;

    @Transient
    private boolean up;



    public CastObce() {
    }

    public CastObce(int code, String name, boolean up) {
        this.code = code;
        this.name = name;
        this.up = up;
    }

    public CastObce(int code, String name, Obec villageCodeObec, boolean up) {
        this.code = code;
        this.name = name;
        this.villageCodeObec = villageCodeObec;
        this.up = up;
    }

    public CastObce(int code, String name, int villageCodeNumber, boolean up) {
        this.code = code;
        this.name = name;
        this.villageCodeNumber = villageCodeNumber;
        this.up = up;
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
        return villageCodeObec;
    }

    public void setVillageCode(Obec villageCodeObec) {
        this.villageCodeObec = villageCodeObec;
    }

    public boolean getUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public int getVillageCodeNumber() {
        return villageCodeNumber;
    }

    public void setVillageCodeNumber(int villageCodeNumber) {
        this.villageCodeNumber = villageCodeNumber;
    }

    public static CastObce createCastObce(int code, String name, Obec villageCodeObec, boolean up){
        return new CastObce(code, name, villageCodeObec, up);
    }

    public static CastObce createCastObce(int code, String name, Integer villageCodeNumber, boolean up){
        return new CastObce(code, name, villageCodeNumber, up);
    }

    @Override
    public String toString() {
        return "CastObce code: " + code + " name: " + name;
    }
}
