package ru.project.worldmoneyinfo.dependency;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.project.domainlayer.utils.ComputingUtil;
import ru.project.worldmoneyinfo.MainApplication;
import ru.project.worldmoneyinfo.utils.CurrencyUtil;
import ru.project.domainlayer.utils.DateUtil;
import ru.project.worldmoneyinfo.IApiData;

@Module
public class UtilsModule {
    private MainApplication applicationInstance;

    public UtilsModule(MainApplication application) {
        applicationInstance = application;
    }

    @Provides
    @Singleton
    public String provideApiKey() {
        // use your own key for result
        return IApiData.KEY;
    }

    @Provides
    public CurrencyUtil provideCurrencyUtil() {
        return new CurrencyUtil(applicationInstance);
    }

    @Provides
    @Singleton
    public ComputingUtil provideComputingUtil() {
        return new ComputingUtil();
    }

    @Provides
    @Singleton
    public DateUtil provideDateUtil() {
        return new DateUtil();
    }
}