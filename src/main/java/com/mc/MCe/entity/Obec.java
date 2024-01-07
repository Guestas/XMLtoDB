package com.mc.MCe.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Obec")
public class Obec {

    @Id
    @Column(name="Kod", columnDefinition = "INT")
    private Integer code;

    @Column(name="Nazev", columnDefinition = "VARCHAR(255)")
    private String name;

    @Transient
    boolean up;


    public Obec() {
    }

    public Obec(String name, Integer code, boolean up) {
        this.name = name;
        this.code = code;
        this.up = up;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static Obec createObec(String name, Integer code, boolean up){
        return new Obec(name, code, up);
    }

    public boolean getUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public String toString() {
        return "Obec code: " + code + " name: " + name;
    }
}
