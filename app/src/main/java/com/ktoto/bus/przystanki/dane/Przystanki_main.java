package com.ktoto.bus.przystanki.dane;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Przystanki_main {

    @SerializedName("lastUpdate")
    @Expose
    private String lastUpdate;
    @SerializedName("stops")
    @Expose
    private List<Przystanki> stops = null;

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Przystanki> getStops() {
        return stops;
    }

    public void setStops(List<Przystanki> stops) {
        this.stops = stops;
    }

}