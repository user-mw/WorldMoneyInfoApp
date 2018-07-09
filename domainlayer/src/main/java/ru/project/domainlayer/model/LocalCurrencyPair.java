package ru.project.domainlayer.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.math.BigDecimal;

@Entity(tableName = "currencies")
public class LocalCurrencyPair {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "symbol")
    private String mSymbol;
    @ColumnInfo(name = "price")
    private String mPrice;
    @ColumnInfo(name = "bid")
    private String mBid;
    @ColumnInfo(name = "ask")
    private String mAsk;
    @ColumnInfo(name = "timestamp")
    private int mTimestamp;

    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String symbol) {
        mSymbol = symbol;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getBid() {
        return mBid;
    }

    public void setBid(String bid) {
        mBid = bid;
    }

    public String getAsk() {
        return mAsk;
    }

    public void setAsk(String ask) {
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
