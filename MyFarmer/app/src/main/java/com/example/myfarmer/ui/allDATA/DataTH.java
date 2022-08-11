package com.example.myfarmer.ui.allDATA;

public class DataTH {
    private String date;
    private String time;
    private String temperature;
    private String Humidity;

    public DataTH(String date, String time, String temperature, String humidity) {
        this.date = date;
        this.time = time;
        this.temperature = temperature;
        Humidity = humidity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }
}
