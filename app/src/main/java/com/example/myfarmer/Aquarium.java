package com.example.myfarmer;

public class Aquarium {
    private int fishid;
    private String fishname;
    private String time;
    private double temperature;
    private double humidity;
    private String date;

    public Aquarium(int fishid,String fishname, double temperature, double humidity) {
        this.fishid=fishid;
        this.fishname = fishname;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public Aquarium( String date, String time, double temperature, double humidity ) {
        this.time = time;
        this.temperature = temperature;
        this.humidity = humidity;
        this.date = date;
    }

    public int getFishid() {
        return fishid;
    }

    public void setFishid(int fishid) {
        this.fishid = fishid;
    }

    public String getFishname() {
        return fishname;
    }

    public void setFishname(String fishname) {
        this.fishname = fishname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
