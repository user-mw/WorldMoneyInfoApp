package ru.project.worldmoneyinfo.dependency;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AppDataModule {
    public static final String PAIRS_KEY = "Pairs";
    public static final String API_KEY = "ApiKey";
    private String mCurrenciesPairs;
    private String mApiKey;

    public AppDataModule(String pairs, String apiKey) {
        mCurrenciesPairs = pairs;
        mApiKey = apiKey;
    }

    @Provides
    @IScreenScope
    @Named(PAIRS_KEY)
    public String providePairs() {
        return mCurrenciesPairs;
    }

    @Provides
    @IScreenScope
    @Named(API_KEY)
    public String provideApiKey() {
        return mApiKey;
    }
}
