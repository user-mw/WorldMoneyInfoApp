package ru.project.worldmoneyinfo.dependency;

import dagger.Subcomponent;
import ru.project.worldmoneyinfo.ui.converter_screen.ConverterFragment;
import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrenciesListFragment;
import ru.project.worldmoneyinfo.utils.CustomViewModelFactory;

@IScreenScope
@Subcomponent(modules = {RepositoryModule.class, ServiceModule.class})
public interface IScreenComponent {
    //void inject(CurrenciesListFragment fragment);
    void inject(ConverterFragment fragment);
    void inject(CustomViewModelFactory factory);
}
