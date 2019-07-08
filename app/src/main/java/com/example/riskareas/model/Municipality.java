package com.example.riskareas.model;

public class Municipality {

    private int id;
    private String name;
    private String significance;
    private String header;
    private String area;
    private String latitude;
    private String clime;
    private String location;

    public Municipality() {
    }

    public Municipality(int id, String name, String significance, String header, String area,
                        String latitude, String clime, String location) {
        this.id = id;
        this.name = name;
        this.significance = significance;
        this.header = header;
        this.area = area;
        this.latitude = latitude;
        this.clime = clime;
        this.location = location;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getClime() {
        return clime;
    }

    public void setClime(String clime) {
        this.clime = clime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
