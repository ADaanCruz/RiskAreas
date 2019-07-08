package com.example.riskareas.model;

public class Area {

    private int id;
    private int id_municipality;
    private String disaster;

    public Area() {
    }

    public Area(int id, int id_municipality, String disaster) {
        this.id = id;
        this.id_municipality = id_municipality;
        this.disaster = disaster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_municipality() {
        return id_municipality;
    }

    public void setId_municipality(int id_municipality) {
        this.id_municipality = id_municipality;
    }

    public String getDisaster() {
        return disaster;
    }

    public void setDisaster(String disaster) {
        this.disaster = disaster;
    }
}
