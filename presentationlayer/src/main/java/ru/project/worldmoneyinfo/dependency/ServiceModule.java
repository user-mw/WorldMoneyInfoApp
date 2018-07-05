package ru.project.worldmoneyinfo.dependency;

import dagger.Module;
import dagger.Provides;
import ru.project.domainlayer.service.CurrenciesServiceImpl;
import ru.project.domainlayer.service.ICurrenciesService;

@Module
public class ServiceModule {

    @Provides
    @IScreenScope
    ICurrenciesService provideService(CurrenciesServiceImpl service) {
        return service;
    }
}
