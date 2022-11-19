package com.example.kollect_final_project;

import java.io.Serializable;

public class User implements Serializable {
    private int id , premium;
    private String username, password, gender, insta_id,fav_artist,fav_group;


    public User() {
    }

    public User(String user_name, String password, String gender, String insta_id) {
        this.username = user_name;
        this.password = password;
        this.gender = gender;
        this.insta_id = insta_id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public void setFav_artist(String newId) {this.fav_artist = newId;}

    public void setFav_group(String newId) {this.fav_group = newId;}
//
//    public void setPremium(int newPremium) {this.premium = newPremium;}

    public void setUser_name(String newUserName) {this.username = newUserName;}

    public void setPassword(String newPassword) {this.password = newPassword;}

    public void setGender(String newGender) {this.gender = newGender;}

    public void setInsta_id(String newInstaID) {this.insta_id = newInstaID;}

    public String getFav_artist() {
        return fav_artist;
    }

    public String getFav_group() {
        return fav_group;
    }
//
//    public int getPremium() {
//        return premium;
//    }

    public String getUser_name() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getInsta_id() {
        return insta_id;
    }

    public int getId() {
        return id;
    }



}
