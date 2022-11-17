package com.example.kollect_final_project;

import java.io.Serializable;

public class Blacklist implements Serializable {
    private int id ,reportNum;
    private String InstagramID, paypalID;
    private byte[] images;


    public Blacklist() {
    }

    public Blacklist(String InstagramID, String paypalID, int reportNum, byte[] images) {
        this.InstagramID = InstagramID;
        this.paypalID = paypalID;
        this.reportNum = reportNum;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReportNum() {
        return reportNum;
    }

    public void setReportNum(int reportNum) {
        this.reportNum = reportNum;
    }

    public String getInstagramID() {
        return InstagramID;
    }

    public void setInstagramID(String instagramID) {
        InstagramID = instagramID;
    }

    public String getPaypalID() {
        return paypalID;
    }

    public void setPaypalID(String paypalID) {
        this.paypalID = paypalID;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }
}