package ru.project.worldmoneyinfo.dependency;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.project.domainlayer.service.CurrenciesServiceImpl;
import ru.project.domainlayer.service.ICurrenciesService;
import ru.project.domainlayer.service.ISettingsService;
import ru.project.domainlayer.service.SettingsServiceImpl;

@Module
public class ServiceModule {

    @Provides
    @IScreenScope
    ICurrenciesService provideService(CurrenciesServiceImpl service) {
        return service;
    }

    @Provides
    @IScreenScope
    ISettingsService provideSettings(SettingsServiceImpl settingsService) {
        return settingsService;
    }
}
