package com.example.kollect_final_project;

import java.io.Serializable;

public class Post implements Serializable {
    private int id,price, status;
    private String seller_name,artist_name,artist_group;

    public void setGroups(String nGroups) {
        this.artist_group = nGroups;
    }

    public void setPrice(int newPrice) {
        this.price = newPrice;
    }

    public void setSellerName(String newname) {
        this.seller_name = newname;
    }

    public void setArtistname(String newname) {
        this.artist_name = newname;
    }


    public void setId(int newId) {
        this.id = newId;
    }

    public void setStatus(int nstatus) {
        this.status = nstatus;
    }


    public int getId() {
        return id;
    }

    public String getSellerName() {
        return seller_name;
    }

    public String getArtistName(){
        return artist_name;
    }

    public int getPrice( ) {
        return price;
    }

    public String getGroups( ) {
        return artist_group;
    }

    public int getStatus(){
        return status;
    }

}
