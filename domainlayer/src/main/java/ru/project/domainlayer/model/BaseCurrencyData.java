package ru.project.domainlayer.model;

import android.arch.persistence.room.ColumnInfo;

public class BaseCurrencyData {
    @ColumnInfo(name = "price")
    protected String price;
    @ColumnInfo(name = "bid")
    protected String bid;
    @ColumnInfo(name = "ask")
    protected String ask;
    @ColumnInfo(name = "timestamp")
    protected int timestamp;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
