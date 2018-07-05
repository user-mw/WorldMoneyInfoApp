package ru.project.domainlayer.model;

import com.google.gson.annotations.SerializedName;

public class RemoteCurrencyPair {
    @SerializedName("symbol")
    private String mSymbol;
    @SerializedName("price")
    private double mPrice;
    @SerializedName("bid")
    private double mBid;
    @SerializedName("ask")
    private double mAsk;
    @SerializedName("timestamp")
    private int mTimestamp;

    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String symbol) {
        mSymbol = symbol;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public double getBid() {
        return mBid;
    }

    public void setBid(double bid) {
        mBid = bid;
    }

    public double getAsk() {
        return mAsk;
    }

    public void setAsk(double ask) {
        mAsk = ask;
    }

    public int getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(int timestamp) {
        mTimestamp = timestamp;
    }
}
