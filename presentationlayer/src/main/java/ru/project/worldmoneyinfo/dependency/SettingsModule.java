package ru.project.worldmoneyinfo.dependency;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.project.worldmoneyinfo.MainApplication;

@Module
public class SettingsModule {
    private MainApplication mApplication;

    public SettingsModule(MainApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSettings() {
        return mApplication.getSharedPreferences("CurrentAppSettings", Context.MODE_PRIVATE);
    }
}
