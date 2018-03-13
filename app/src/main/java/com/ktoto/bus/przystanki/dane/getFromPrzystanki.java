package com.ktoto.bus.przystanki.dane;


public class getFromPrzystanki {
    private int stopId;
    private String stopDesc;
    private double stopLat;
    private double stopLon;
    private String zoneName;






    public String getStopDesc() {
        return stopDesc;
    }

    public void setStopDesc(String stopDesc) {
        this.stopDesc = stopDesc;
    }


    public int getStopId() {
        return stopId;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
    }


    public double getstopLat() {
        return stopLat;
    }

    public void setstopLat(double stopLat) {
        this.stopLat = stopLat;
    }

    public double getstopLon() {
        return stopLon;
    }

    public void setstopLon(double stopLon) {
        this.stopLon = stopLon;
    }

    public String getzoneName() {
        return zoneName;
    }

    public void setzoneName(String zoneName) {
        this.zoneName = zoneName;
    }


    public getFromPrzystanki(String stopDesc,int stopId, double stopLat, double stopLon, String zoneName)
    {

        this.setStopDesc(stopDesc);
        this.setStopId(stopId);
        this.setstopLat(stopLat);
        this.setstopLon(stopLon);
        this.setzoneName(zoneName);

    }

}