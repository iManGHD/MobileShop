package com.armani.myapplication.accessory.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccessoryInfoDTO {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("price")
    @Expose
    private Long price;

    public AccessoryInfoDTO(){}

    public AccessoryInfoDTO(String name , Long price){
        this.name = name ;
        this.price = price ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }


    @Override
    public String toString(){
        return " Name :   " + this.name + "\n" + " Price   :   " + this.price;
    }
}
