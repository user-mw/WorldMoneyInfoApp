package ru.project.worldmoneyinfo.dependency;

import dagger.Subcomponent;
import ru.project.worldmoneyinfo.ui.converter_screen.ConverterFragment;
import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrenciesListFragment;
import ru.project.worldmoneyinfo.ui.statistic_screen.StatisticFragment;

@IScreenScope
@Subcomponent(modules = {ViewModelModule.class})
public interface IViewModelComponent {
    void inject(CurrenciesListFragment fragment);
    void inject(ConverterFragment fragment);
    void inject(StatisticFragment fragment);
}
