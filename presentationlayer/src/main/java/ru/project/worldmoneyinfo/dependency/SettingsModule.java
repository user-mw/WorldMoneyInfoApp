package ru.project.worldmoneyinfo.dependency;

import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

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
        return PreferenceManager.getDefaultSharedPreferences(mApplication);
    }
}
