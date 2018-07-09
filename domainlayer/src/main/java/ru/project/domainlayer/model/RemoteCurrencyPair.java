package ru.project.domainlayer.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class RemoteCurrencyPair {
    @SerializedName("symbol")
    private String mSymbol;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("bid")
    private String mBid;
    @SerializedName("ask")
    private String mAsk;
    @SerializedName("timestamp")
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

    public LocalCurrencyPair toLocalCurrency() {
        LocalCurrencyPair pair = new LocalCurrencyPair();
        pair.setSymbol(mSymbol);
        pair.setPrice(mPrice);
        pair.setAsk(mAsk);
        pair.setBid(mBid);
        pair.setTimestamp(mTimestamp);

        return pair;
    }
}
