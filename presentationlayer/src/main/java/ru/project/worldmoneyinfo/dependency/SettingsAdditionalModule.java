package ru.project.worldmoneyinfo.dependency;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsAdditionalModule {
    private List<String> mCurrenciesNames;

    public SettingsAdditionalModule(List<String> currenciesNames) {
        mCurrenciesNames = currenciesNames;
    }

    @Provides
    @IScreenScope
    public List<String> provideCurrenciesNames() {
        return mCurrenciesNames;
    }
}
