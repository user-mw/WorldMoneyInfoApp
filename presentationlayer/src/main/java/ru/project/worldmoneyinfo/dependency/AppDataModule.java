package ru.project.worldmoneyinfo.dependency;

import dagger.Module;
import dagger.Provides;

@Module
public class AppDataModule {
    private String apiKey;

    public AppDataModule(String apiKey) {
        this.apiKey = apiKey;
    }

    @Provides
    @IScreenScope
    public String provideApiKey() {
        return apiKey;
    }
}