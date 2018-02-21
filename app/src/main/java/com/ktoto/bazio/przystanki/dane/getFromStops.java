package com.ktoto.bazio.przystanki.dane;

public class getFromStops {
    private String nazwa;
    private int stopId1;
    private int stopId2;
    private int stopId3;
    private int stopId4;

    public String getNazwa() {
        return nazwa;
    }

    public getFromStops(String nazwa, int stopId1, int stopId2, int stopId3, int stopId4)
    {
        this.setNazwa(nazwa);
        this.setStopId1(stopId1);
        this.setStopId2(stopId2);
        this.setStopId3(stopId3);
        this.setStopId4(stopId4);
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getStopId1() {
        return stopId1;
    }

    public void setStopId1(int stopId1) {
        this.stopId1 = stopId1;
    }

    public int getStopId2() {
        return stopId2;
    }

    public void setStopId2(int stopId2) {
        this.stopId2 = stopId2;
    }

    public int getStopId3() {
        return stopId3;
    }

    public void setStopId3(int stopId3) {
        this.stopId3 = stopId3;
    }

    public int getStopId4() {
        return stopId4;
    }

    public void setStopId4(int stopId4) {
        this.stopId4 = stopId4;
    }


}