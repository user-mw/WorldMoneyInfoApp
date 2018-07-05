package ru.project.worldmoneyinfo.dependency;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ru.project.datalayer.repository.CurrenciesServerRepository;
import ru.project.domainlayer.repository.ICurrenciesRepository;

@Module
public class RepositoryModule {

    @Provides
    @IScreenScope
    @Named(ICurrenciesRepository.REMOTE_REPOSITORY)
    ICurrenciesRepository provideRemoteRepository(CurrenciesServerRepository repository) {
        return repository;
    }
}
