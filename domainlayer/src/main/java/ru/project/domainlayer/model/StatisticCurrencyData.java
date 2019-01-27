package ru.project.domainlayer.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "statistic_currencies")
public class StatisticCurrencyData extends BaseCurrencyData {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "currency_id")
    private String id;
    @ColumnInfo(name = "symbol")
    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }
}