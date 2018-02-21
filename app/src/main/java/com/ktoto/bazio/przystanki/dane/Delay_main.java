package com.ktoto.bazio.przystanki.dane;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

public class Delay_main {

    @SerializedName("lastUpdate")
    @Expose
    private String lastUpdate;
    @SerializedName("delay")
    @Expose
    private List<Delay> delay = null;

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Delay> getDelay() {
        return delay;
    }

    public void setDelay(List<Delay> delay) {
        this.delay = delay;
    }

}
