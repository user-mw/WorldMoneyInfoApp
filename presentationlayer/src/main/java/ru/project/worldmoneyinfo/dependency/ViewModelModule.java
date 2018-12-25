package ru.project.worldmoneyinfo.dependency;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrenciesListViewModel;
import ru.project.worldmoneyinfo.utils.CustomViewModelFactory;

@Module
public class ViewModelModule {
    private CustomViewModelFactory factory = new CustomViewModelFactory();
    private Fragment lifeCycleOwner;

    public ViewModelModule(Fragment fragment) {
        lifeCycleOwner = fragment;
    }

    @Provides
    public CurrenciesListViewModel provideCurrenciesListViewModel() {
        return ViewModelProviders.of(lifeCycleOwner, factory).get(CurrenciesListViewModel.class);
    }
}
