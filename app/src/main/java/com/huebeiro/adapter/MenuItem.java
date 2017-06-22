package com.huebeiro.adapter;

/**
 * Author: adilson
 * Date: 19/06/17
 */

public class MenuItem {

    private int iconId;
    private String label;

    public MenuItem(int id, String labelText){
        iconId = id;
        label = labelText;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
