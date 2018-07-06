package ru.project.datalayer.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.project.domainlayer.model.LocalCurrencyPair;

@Database(entities = {LocalCurrencyPair.class}, version = 1)
public abstract class CurrenciesDatabase extends RoomDatabase {
    public abstract ICurrenciesDao getDao();
}
