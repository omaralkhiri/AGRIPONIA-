package com.example.myfarmer;

public class info {
    private  static final String IpAddress="192.168.1.34";
    private static String phone;
    private static String id;
    private static int house_id;
    private static int fish_id;
    public static int getFish_id() {
        return fish_id;
    }

    public static void setFish_id(int fish_id) {
        info.fish_id = fish_id;
    }

    public static int getHouse_id() {
        return house_id;
    }

    public static void setHouse_id(int house_id) {
        info.house_id = house_id;
    }

    public static String getIpAddress() {
        return IpAddress;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        info.phone = phone;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        info.id = id;
    }
}
