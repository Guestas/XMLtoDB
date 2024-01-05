package com.mc.MCe.entity.helper;

import com.mc.MCe.entity.CastObce;

public class CastiObciAdd {
    private Integer id;

    private Integer code;

    private String name;

    private int villageCode;

    public CastiObciAdd() {
    }

    public CastiObciAdd(Integer code, String name, int villageCode) {
        this.code = code;
        this.name = name;
        this.villageCode = villageCode;
    }

    public CastObce getCastObcewithoutVilageCode(){
        return new CastObce(code, name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(int villageCode) {
        this.villageCode = villageCode;
    }
}
