package com.example.myfarmer;

public class Users {
    private int id;
    private String name;
    private String gender;
    private String birthday;
    private String phone;
    private String email;
    private String job;

    public Users(int id,String name, String gender, String birthday, String phone, String email, String job) {
        this.id=id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.job = job;
    }
    public Users(int id,String name,String phone,String email,String job){
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.email=email;
        this.job=job;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
