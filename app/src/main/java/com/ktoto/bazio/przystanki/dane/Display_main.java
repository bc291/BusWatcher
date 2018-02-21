package com.ktoto.bazio.przystanki.dane;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class Display_main {

    @SerializedName("lastUpdate")
    @Expose
    private String lastUpdate;
    @SerializedName("displays")
    @Expose
    private List<Display> displays = null;

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Display> getDisplays() {
        return displays;
    }

    public void setDisplays(List<Display> displays) {
        this.displays = displays;
    }

}