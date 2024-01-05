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


    public Obec() {
    }

    public Obec(String name, Integer code) {
        this.name = name;
        this.code = code;
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

    public static Obec createObec(String name, Integer code){
        return new Obec(name, code);
    }
}
