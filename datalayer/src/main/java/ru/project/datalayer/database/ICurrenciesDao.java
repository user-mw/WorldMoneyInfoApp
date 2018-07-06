package ru.project.datalayer.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.project.domainlayer.model.LocalCurrencyPair;

@Dao
public interface ICurrenciesDao {
    @Query("SELECT * FROM currencies WHERE symbol IN (:pairs)")
    List<LocalCurrencyPair> getCurrencies(String[] pairs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCurrencies(List<LocalCurrencyPair> currencies);
}
