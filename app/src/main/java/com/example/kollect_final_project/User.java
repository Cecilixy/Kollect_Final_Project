package com.example.kollect_final_project;

import java.io.Serializable;

public class User implements Serializable {
    private int id,fav_artist_id, fav_group_id, premium;
    private String username, password, gender, insta_id;

    public User() {
    }

    public User(int userid, String user_name, String password, String gender, String insta_id) {
        this.id = userid;
        this.username = user_name;
        this.password = password;
        this.gender = gender;
        this.insta_id = insta_id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

//    public void setFav_artist_id(int newId) {this.fav_artist_id = newId;}
//
//    public void setFav_group_id(int newId) {this.fav_group_id = newId;}
//
//    public void setPremium(int newPremium) {this.premium = newPremium;}

    public void setUser_name(String newUserName) {this.username = newUserName;}

    public void setPassword(String newPassword) {this.password = newPassword;}

    public void setGender(String newGender) {this.gender = newGender;}

    public void setInsta_id(String newInstaID) {this.insta_id = newInstaID;}

//    public int getFav_artist_id() {
//        return fav_artist_id;
//    }
//
//    public int getFav_group_id() {
//        return fav_group_id;
//    }
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
