package com.ktoto.bus.przystanki.dane;


        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Przystanki {

    @SerializedName("stopId")
    @Expose
    private Integer stopId;
    @SerializedName("stopCode")
    @Expose
    private Object stopCode;
    @SerializedName("stopName")
    @Expose
    private Object stopName;
    @SerializedName("stopShortName")
    @Expose
    private String stopShortName;
    @SerializedName("stopDesc")
    @Expose
    private String stopDesc;
    @SerializedName("subName")
    @Expose
    private String subName;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("stopLat")
    @Expose
    private Double stopLat;
    @SerializedName("stopLon")
    @Expose
    private Double stopLon;
    @SerializedName("zoneId")
    @Expose
    private Object zoneId;
    @SerializedName("zoneName")
    @Expose
    private String zoneName;
    @SerializedName("stopUrl")
    @Expose
    private String stopUrl;
    @SerializedName("locationType")
    @Expose
    private Object locationType;
    @SerializedName("parentStation")
    @Expose
    private Object parentStation;
    @SerializedName("stopTimezone")
    @Expose
    private String stopTimezone;
    @SerializedName("wheelchairBoarding")
    @Expose
    private Object wheelchairBoarding;
    @SerializedName("virtual")
    @Expose
    private Object virtual;
    @SerializedName("nonpassenger")
    @Expose
    private Object nonpassenger;
    @SerializedName("depot")
    @Expose
    private Object depot;
    @SerializedName("ticketZoneBorder")
    @Expose
    private Object ticketZoneBorder;
    @SerializedName("onDemand")
    @Expose
    private Object onDemand;
    @SerializedName("activationDate")
    @Expose
    private String activationDate;

    public Integer getStopId() {
        return stopId;
    }

    public void setStopId(Integer stopId) {
        this.stopId = stopId;
    }

    public Object getStopCode() {
        return stopCode;
    }

    public void setStopCode(Object stopCode) {
        this.stopCode = stopCode;
    }

    public Object getStopName() {
        return stopName;
    }

    public void setStopName(Object stopName) {
        this.stopName = stopName;
    }

    public String getStopShortName() {
        return stopShortName;
    }

    public void setStopShortName(String stopShortName) {
        this.stopShortName = stopShortName;
    }

    public String getStopDesc() {
        return stopDesc;
    }

    public void setStopDesc(String stopDesc) {
        this.stopDesc = stopDesc;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getStopLat() {
        return stopLat;
    }

    public void setStopLat(Double stopLat) {
        this.stopLat = stopLat;
    }

    public Double getStopLon() {
        return stopLon;
    }

    public void setStopLon(Double stopLon) {
        this.stopLon = stopLon;
    }

    public Object getZoneId() {
        return zoneId;
    }

    public void setZoneId(Object zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getStopUrl() {
        return stopUrl;
    }

    public void setStopUrl(String stopUrl) {
        this.stopUrl = stopUrl;
    }

    public Object getLocationType() {
        return locationType;
    }

    public void setLocationType(Object locationType) {
        this.locationType = locationType;
    }

    public Object getParentStation() {
        return parentStation;
    }

    public void setParentStation(Object parentStation) {
        this.parentStation = parentStation;
    }

    public String getStopTimezone() {
        return stopTimezone;
    }

    public void setStopTimezone(String stopTimezone) {
        this.stopTimezone = stopTimezone;
    }

    public Object getWheelchairBoarding() {
        return wheelchairBoarding;
    }

    public void setWheelchairBoarding(Object wheelchairBoarding) {
        this.wheelchairBoarding = wheelchairBoarding;
    }

    public Object getVirtual() {
        return virtual;
    }

    public void setVirtual(Object virtual) {
        this.virtual = virtual;
    }

    public Object getNonpassenger() {
        return nonpassenger;
    }

    public void setNonpassenger(Object nonpassenger) {
        this.nonpassenger = nonpassenger;
    }

    public Object getDepot() {
        return depot;
    }

    public void setDepot(Object depot) {
        this.depot = depot;
    }

    public Object getTicketZoneBorder() {
        return ticketZoneBorder;
    }

    public void setTicketZoneBorder(Object ticketZoneBorder) {
        this.ticketZoneBorder = ticketZoneBorder;
    }

    public Object getOnDemand() {
        return onDemand;
    }

    public void setOnDemand(Object onDemand) {
        this.onDemand = onDemand;
    }

    public String getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

}