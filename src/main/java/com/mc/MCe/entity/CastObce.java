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

    @Transient
    private boolean up;

    public CastObce() {
    }

    public CastObce(int code, String name, boolean up) {
        this.code = code;
        this.name = name;
        this.up = up;
    }

    public CastObce(int code, String name, Obec villageCode, boolean up) {
        this.code = code;
        this.name = name;
        this.villageCode = villageCode;
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
        return villageCode;
    }

    public void setVillageCode(Obec villageCode) {
        this.villageCode = villageCode;
    }

    public boolean getUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    @Override
    public String toString() {
        return "CastObce code: " + code + " name: " + name;
    }
}
