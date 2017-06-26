package com.huebeiro.model;

/**
 * Author: adilson
 * Date: 20/06/17
 */

public class ProductType {
    private int id;
    private String name;

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
}
