package com.example.riskareas.model;

public class Municipality {

    private int id;
    private String name;
    private String significance;
    private String header;
    private String area;
    private String clime;
    private String altitude;
    private String latitude;
    private String longitude;

    public Municipality() {
    }

    public Municipality(int id, String name, String significance, String header, String area,
                        String clime, String altitude, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.significance = significance;
        this.header = header;
        this.area = area;
        this.clime = clime;
        this.altitude = altitude;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignificance() {
        return significance;
    }

    public void setSignificance(String significance) {
        this.significance = significance;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getClime() {
        return clime;
    }

    public void setClime(String clime) {
        this.clime = clime;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
