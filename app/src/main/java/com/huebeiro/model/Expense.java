package com.huebeiro.model;

/**
 * Author: adilson
 * Date: 22/06/17
 */

public class Expense {

    public static final String TABLE_NAME = "Expense";

    private int id;
    private String note;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
