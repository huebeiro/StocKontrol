package com.huebeiro.model;

/**
 * Author: adilson
 * Date: 20/06/17
 */

public class ProductType {
    private int id;
    private String name;
    private double price;

    public static final String TABLE_NAME = "ProductType";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
