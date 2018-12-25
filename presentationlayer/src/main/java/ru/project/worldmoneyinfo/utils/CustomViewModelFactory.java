package ru.project.worldmoneyinfo.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import ru.project.domainlayer.service.ICurrenciesService;
import ru.project.domainlayer.service.ISettingsService;
import ru.project.worldmoneyinfo.MainApplication;
import ru.project.worldmoneyinfo.dependency.RepositoryModule;
import ru.project.worldmoneyinfo.dependency.ServiceModule;
import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrenciesListViewModel;

public class CustomViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @Inject
    ICurrenciesService currenciesService;

    @Inject
    ISettingsService settingsService;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        MainApplication.getApplicationComponent().plusScreenComponent(
                new RepositoryModule(),
                new ServiceModule()
        ).inject(this);

        if(modelClass.isAssignableFrom(CurrenciesListViewModel.class)) {
            return (T) new CurrenciesListViewModel(currenciesService, settingsService);
        }

        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}
