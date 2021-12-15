package com.example.androidproject;

import java.io.Serializable;

public class GnomoItem implements Serializable {
    private String id;
    private String num;
    private String color;

    public GnomoItem(String id, String num, String color) {
        this.id = id;
        this.num = num;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
