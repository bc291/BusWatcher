package com.ktoto.bus.przystanki.dane;




public class getFromDelays {
    private int routeId;
    private String headsign;
    private String estimatedTime;




    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getHeadsign() {
        return headsign;
    }

    public void setHeadsign(String headsign) {
        this.headsign = headsign;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }


    public getFromDelays(int routeId, String headsign, String estimatedTime)
    {
        this.setRouteId(routeId);
        this.setHeadsign(headsign);
        this.setEstimatedTime(estimatedTime);
    }

}