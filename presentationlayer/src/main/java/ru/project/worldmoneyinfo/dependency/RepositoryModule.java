package ru.project.worldmoneyinfo.dependency;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.project.datalayer.repository.CurrenciesDatabaseRepository;
import ru.project.datalayer.repository.CurrenciesServerRepository;
import ru.project.datalayer.repository.SettingsRepository;
import ru.project.domainlayer.repository.ICurrenciesRepository;
import ru.project.domainlayer.repository.ISettingsRepository;

@Module
public class RepositoryModule {

    @Provides
    @IScreenScope
    @Named(ICurrenciesRepository.REMOTE_REPOSITORY)
    ICurrenciesRepository provideRemoteRepository(CurrenciesServerRepository repository) {
        return repository;
    }

    @Provides
    @IScreenScope
    @Named(ICurrenciesRepository.LOCAL_REPOSITORY)
    ICurrenciesRepository provideLocalRepository(CurrenciesDatabaseRepository repository) {
        return repository;
    }

    @Provides
    @IScreenScope
    ISettingsRepository provideSettingsRepository(SettingsRepository settingsRepository) {
        return settingsRepository;
    }
}
