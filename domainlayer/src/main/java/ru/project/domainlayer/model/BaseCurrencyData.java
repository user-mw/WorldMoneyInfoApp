package ru.project.domainlayer.model;

import android.arch.persistence.room.ColumnInfo;

public class BaseCurrencyData {
    @ColumnInfo(name = "price")
    protected String price;
    @ColumnInfo(name = "timestamp")
    protected int timestamp;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}