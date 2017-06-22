package com.huebeiro.model;

/**
 * Author: adilson
 * Date: 20/06/17
 */

public class ProductType {
    private int id;
    private int name;

    public static final String TABLE_NAME = "ProductType";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
