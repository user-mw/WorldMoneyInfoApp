package ru.project.worldmoneyinfo.dependency;

import dagger.Module;
import dagger.Provides;

@Module
public class AppDataModule {
    private String mApiKey;

    public AppDataModule(String apiKey) {
        mApiKey = apiKey;
    }

    @Provides
    @IScreenScope
    public String provideApiKey() {
        return mApiKey;
    }
}
