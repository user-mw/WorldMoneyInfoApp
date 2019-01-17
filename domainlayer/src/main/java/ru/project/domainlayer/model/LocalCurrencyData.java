package ru.project.domainlayer.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "currencies")
public class LocalCurrencyData extends BaseCurrencyData {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "symbol")
    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(@NonNull String symbol) {
        this.symbol = symbol;
    }

    public RemoteCurrencyData toRemoteCurrency() {
        RemoteCurrencyData currencyData = new RemoteCurrencyData();
        currencyData.setSymbol(symbol);
        currencyData.setPrice(price);
        currencyData.setAsk(ask);
        currencyData.setBid(bid);
        currencyData.setTimestamp(timestamp);

        return currencyData;
    }
}