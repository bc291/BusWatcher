package com.ktoto.bus.przystanki.dane;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;




public class Display {

    @SerializedName("displayCode")
    @Expose
    private Integer displayCode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("idStop1")
    @Expose
    private Integer idStop1;
    @SerializedName("idStop2")
    @Expose
    private Integer idStop2;
    @SerializedName("idStop3")
    @Expose
    private Integer idStop3;
    @SerializedName("idStop4")
    @Expose
    private Integer idStop4;

    public Integer getDisplayCode() {
        return displayCode;
    }

    public void setDisplayCode(Integer displayCode) {
        this.displayCode = displayCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdStop1() {
        return idStop1;
    }

    public void setIdStop1(Integer idStop1) {
        this.idStop1 = idStop1;
    }

    public Integer getIdStop2() {
        return idStop2;
    }

    public void setIdStop2(Integer idStop2) {
        this.idStop2 = idStop2;
    }

    public Integer getIdStop3() {
        return idStop3;
    }

    public void setIdStop3(Integer idStop3) {
        this.idStop3 = idStop3;
    }

    public Integer getIdStop4() {
        return idStop4;
    }

    public void setIdStop4(Integer idStop4) {
        this.idStop4 = idStop4;
    }

}