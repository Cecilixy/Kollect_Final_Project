package com.example.kollect_final_project;

import java.io.Serializable;

public class Post implements Serializable {
    private int id,price;
    private String objectName,groupName;

    public void setGroups(String nGroups) {
        this.groupName = nGroups;
    }

    public void setPrice(int newPrice) {
        this.price = newPrice;
    }

    public void setName(String newname) {
        this.objectName = newname;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public int getId( ) {
        return id;
    }

    public String getName( ) {
        return objectName;
    }

    public int getPrice( ) {
        return price;
    }

    public String getGroups( ) {
        return groupName;
    }

}
