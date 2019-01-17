package ru.project.datalayer.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.project.domainlayer.model.LocalCurrencyData;
import ru.project.domainlayer.model.StatisticCurrencyData;

@Database(entities = {LocalCurrencyData.class, StatisticCurrencyData.class}, exportSchema = false, version = 1)
public abstract class CurrenciesDatabase extends RoomDatabase {
    public abstract ICurrenciesDao getDao();
}
