package com.ktoto.bus.przystanki.dane;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Delay {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("delayInSeconds")
    @Expose
    private Integer delayInSeconds;
    @SerializedName("estimatedTime")
    @Expose
    private String estimatedTime;
    @SerializedName("headsign")
    @Expose
    private String headsign;
    @SerializedName("routeId")
    @Expose
    private Integer routeId;
    @SerializedName("tripId")
    @Expose
    private Integer tripId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("theoreticalTime")
    @Expose
    private String theoreticalTime;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("trip")
    @Expose
    private Integer trip;
    @SerializedName("vehicleCode")
    @Expose
    private Integer vehicleCode;
    @SerializedName("vehicleId")
    @Expose
    private Integer vehicleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDelayInSeconds() {
        return delayInSeconds;
    }

    public void setDelayInSeconds(Integer delayInSeconds) {
        this.delayInSeconds = delayInSeconds;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getHeadsign() {
        return headsign;
    }

    public void setHeadsign(String headsign) {
        this.headsign = headsign;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTheoreticalTime() {
        return theoreticalTime;
    }

    public void setTheoreticalTime(String theoreticalTime) {
        this.theoreticalTime = theoreticalTime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getTrip() {
        return trip;
    }

    public void setTrip(Integer trip) {
        this.trip = trip;
    }

    public Integer getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(Integer vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

}