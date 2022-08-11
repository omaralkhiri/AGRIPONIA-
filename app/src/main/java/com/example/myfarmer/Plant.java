package com.example.myfarmer;

public class Plant {
    private int id;
    private String plantname;

    public Plant(int id, String plantname) {
        this.id = id;
        this.plantname = plantname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlantname() {
        return plantname;
    }

    public void setPlantname(String plantname) {
        this.plantname = plantname;
    }
}
