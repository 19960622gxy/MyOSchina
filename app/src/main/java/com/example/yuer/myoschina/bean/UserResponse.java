package com.example.yuer.myoschina.bean;

/**
 * Created by Yuer on 2017/5/13.
 */

public class UserResponse {

    /**
     * gender : female
     * name : 成住坏空
     * location : 江苏 南京
     * id : 3453639
     * avatar : https://static.oschina.net/uploads/user/1726/3453639_50.jpg?t=1494227369000
     * email : 8f1e5f0d-3783-4d7f-9a46-78b89a3ffd39
     * url : https://my.oschina.net/u/3453639
     */

    private String gender;
    private String name;
    private String location;
    private int id;
    private String avatar;
    private String email;
    private String url;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
