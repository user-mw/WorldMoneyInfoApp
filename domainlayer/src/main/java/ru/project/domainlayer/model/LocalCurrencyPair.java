package ru.project.domainlayer.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "currencies")
public class LocalCurrencyPair {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "symbol")
    private String mSymbol;
    @ColumnInfo(name = "price")
    private double mPrice;
    @ColumnInfo(name = "bid")
    private double mBid;
    @ColumnInfo(name = "ask")
    private double mAsk;
    @ColumnInfo(name = "timestamp")
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

    public RemoteCurrencyPair toRemoteCurrency() {
        RemoteCurrencyPair pair = new RemoteCurrencyPair();
        pair.setSymbol(mSymbol);
        pair.setPrice(mPrice);
        pair.setAsk(mAsk);
        pair.setBid(mBid);
        pair.setTimestamp(mTimestamp);

        return pair;
    }
}
