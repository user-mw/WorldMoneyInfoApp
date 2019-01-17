package ru.project.datalayer.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.project.domainlayer.model.LocalCurrencyData;
import ru.project.domainlayer.model.StatisticCurrencyData;

@Dao
public interface ICurrenciesDao {
    @Query("SELECT * FROM currencies WHERE symbol IN (:pairs)")
    List<LocalCurrencyData> getCurrencies(String[] pairs);

    @Query("SELECT * FROM statistic_currencies WHERE symbol = :currencyPair ORDER BY timestamp DESC LIMIT 100")
    List<StatisticCurrencyData> getStatistic(String currencyPair);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCurrencies(List<LocalCurrencyData> currencies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStatistic(List<StatisticCurrencyData> currencies);
}
