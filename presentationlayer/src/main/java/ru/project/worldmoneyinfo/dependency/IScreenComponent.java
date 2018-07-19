package ru.project.worldmoneyinfo.dependency;

import dagger.Subcomponent;
import ru.project.worldmoneyinfo.ui.converter_screen.ConverterFragment;
import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrenciesListFragment;

@IScreenScope
@Subcomponent(modules = {RepositoryModule.class, ServiceModule.class, AppDataModule.class})
public interface IScreenComponent {
    void inject(CurrenciesListFragment fragment);
    void inject(ConverterFragment fragment);
}
