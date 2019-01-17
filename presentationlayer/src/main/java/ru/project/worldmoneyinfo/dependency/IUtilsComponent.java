package ru.project.worldmoneyinfo.dependency;

import javax.inject.Singleton;

import dagger.Component;
import ru.project.worldmoneyinfo.ui.converter_screen.ConverterViewModel;
import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrenciesListViewModel;
import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrencyViewHolder;
import ru.project.worldmoneyinfo.ui.statistic_screen.StatisticViewModel;

@Singleton
@Component(modules = {UtilsModule.class})
public interface IUtilsComponent {
    void inject(CurrenciesListViewModel currenciesListViewModel);
    void inject(CurrencyViewHolder viewHolder);
    void inject(ConverterViewModel converterViewModel);
    void inject(StatisticViewModel statisticViewModel);
}