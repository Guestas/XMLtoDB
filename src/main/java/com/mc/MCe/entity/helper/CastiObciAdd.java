package com.mc.MCe.entity.helper;

import com.mc.MCe.entity.CastObce;

public class CastiObciAdd {
    //this class helps with adding new data to database if i need CastObce and from table Obce load Obec for conect tables together
    private Integer id;

    private Integer code;

    private String name;

    private int villageCode;

    private boolean up;

    public CastiObciAdd() {
    }

    public CastiObciAdd(Integer code, String name, int villageCode,boolean up ) {
        this.code = code;
        this.name = name;
        this.villageCode = villageCode;
        this.up = up;
    }

    public CastObce getCastObcewithoutVilageCode(){
        return new CastObce(code, name, up);
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

    public boolean getUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public static CastiObciAdd createCastiObce(int code, String name, Integer villageCode, boolean up){
        return new CastiObciAdd(code, name, villageCode, up);
    }
}
