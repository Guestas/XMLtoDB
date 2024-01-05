package com.mc.MCe.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Obec")
public class Obec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "INT")
    private Integer id;

    @Column(name="Nazev", columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name="Kod", columnDefinition = "INT")
    private Integer code;


    public Obec() {
    }

    public Obec(String name, Integer code) {
        this.name = name;
        this.code = code;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
