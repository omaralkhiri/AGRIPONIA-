package com.example.myfarmer;

public class GlassHouse {
    private int id;
    private String glasshousename;

    public GlassHouse(int id, String glasshousename) {
        this.id = id;
        this.glasshousename = glasshousename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGlasshousename() {
        return glasshousename;
    }

    public void setGlasshousename(String glasshousename) {
        this.glasshousename = glasshousename;
    }
}
