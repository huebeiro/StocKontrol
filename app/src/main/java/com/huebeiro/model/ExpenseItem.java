package com.huebeiro.model;

/**
 * Author: adilson
 * Date: 22/06/17
 */

public class ExpenseItem {

    public static final String TABLE_NAME = "ExpenseItem";

    private int id;
    private int expense;
    private int product;
    private int quantity;

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
