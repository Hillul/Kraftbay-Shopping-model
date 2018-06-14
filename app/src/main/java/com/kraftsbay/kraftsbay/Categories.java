package com.kraftsbay.kraftsbay;

import android.content.Context;

/**
 * Created by Hillul on 8/31/2017.
 */

public class Categories {
    private String name;
    private String actual_price;
    private String image;
    private String offers;

    public Categories(){

    }

    public Categories(String name, String actual_price, String image,String offers) {
        this.name = name;
        this.actual_price = actual_price;
        this.image = image;
        this.offers=offers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActual_price() {
        return actual_price;
    }

    public void setActual_price(String actual_price) {
        this.actual_price = actual_price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOffers() {return offers;}

    public void setOffers(String offers) {this.offers = offers;}
}

