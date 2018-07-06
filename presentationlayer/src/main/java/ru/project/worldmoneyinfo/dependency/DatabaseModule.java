package ru.project.worldmoneyinfo.dependency;

import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.project.datalayer.database.CurrenciesDatabase;
import ru.project.datalayer.database.ICurrenciesDao;
import ru.project.worldmoneyinfo.MainApplication;

@Module
public class DatabaseModule {
    private MainApplication mApplicationInstance;

    public DatabaseModule(MainApplication applicationInstance) {
        mApplicationInstance = applicationInstance;
    }

    @Provides
    @Singleton
    public CurrenciesDatabase provideDatabase() {
        return Room.databaseBuilder(mApplicationInstance, CurrenciesDatabase.class, "currencies_database")
                .build();
    }

    @Provides
    @Singleton
    public ICurrenciesDao provideDao(CurrenciesDatabase database) {
        return database.getDao();
    }

}
