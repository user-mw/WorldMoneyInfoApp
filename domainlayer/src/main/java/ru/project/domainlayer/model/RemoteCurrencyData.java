package ru.project.domainlayer.model;

import com.google.gson.annotations.SerializedName;

public class RemoteCurrencyData {
    @SerializedName("symbol")
    private String symbol;
    @SerializedName("price")
    private String price;
    @SerializedName("bid")
    private String bid;
    @SerializedName("ask")
    private String ask;
    @SerializedName("timestamp")
    private int timestamp;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

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

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public LocalCurrencyData toLocalCurrency() {
        LocalCurrencyData currencyData = new LocalCurrencyData();
        currencyData.setSymbol(symbol);
        currencyData.setPrice(price);
        currencyData.setAsk(ask);
        currencyData.setBid(bid);
        currencyData.setTimestamp(timestamp);

        return currencyData;
    }

    public StatisticCurrencyData toStatisticCurrency() {
        StatisticCurrencyData currencyData = new StatisticCurrencyData();
        currencyData.setId(timestamp + "+" + symbol);
        currencyData.setSymbol(symbol);
        currencyData.setPrice(price);
        currencyData.setAsk(ask);
        currencyData.setBid(bid);
        currencyData.setTimestamp(timestamp);

        return currencyData;
    }
}