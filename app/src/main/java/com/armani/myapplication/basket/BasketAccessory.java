package com.armani.myapplication.basket;

import android.support.annotation.NonNull;

public class BasketAccessory {
    private Long id;
    private String name;

    public BasketAccessory() {
    }

    public BasketAccessory(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}
