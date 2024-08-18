package com.armani.myapplication.basket;

import java.util.List;

public class BasketDTO {
    private List<BasketMobile> basketMobiles;
    private List<BasketAccessory> basketAccessories;

    public BasketDTO() {
    }

    public BasketDTO(List<BasketMobile> basketMobiles, List<BasketAccessory> basketAccessories) {
        this.basketMobiles = basketMobiles;
        this.basketAccessories = basketAccessories;
    }

    public List<BasketMobile> getBasketMobiles() {
        return basketMobiles;
    }

    public void setBasketMobiles(List<BasketMobile> basketMobiles) {
        this.basketMobiles = basketMobiles;
    }

    public List<BasketAccessory> getBasketAccessories() {
        return basketAccessories;
    }

    public void setBasketAccessories(List<BasketAccessory> basketAccessories) {
        this.basketAccessories = basketAccessories;
    }
}
