package ru.project.worldmoneyinfo.dependency;

import javax.inject.Singleton;

import dagger.Component;
import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrenciesListViewModel;
import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrencyViewModel;

@Singleton
@Component(modules = {UtilsModule.class})
public interface IUtilsComponent {
    void inject(CurrenciesListViewModel currenciesListViewModel);
    void inject(CurrencyViewModel viewModel);
}
