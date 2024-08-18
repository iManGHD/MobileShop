package com.armani.myapplication.basket;

import android.support.annotation.NonNull;

public class BasketMobile {
    private Long id;
    private String name;
    private String companyName;

    public BasketMobile() {
    }

    public BasketMobile(Long id, String name, String companyName) {
        this.id = id;
        this.name = name;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @NonNull
    @Override
    public String toString() {
        return this.companyName + "  " + this.name;
    }
}
