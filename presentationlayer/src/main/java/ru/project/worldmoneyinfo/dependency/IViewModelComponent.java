package ru.project.worldmoneyinfo.dependency;

import dagger.Subcomponent;
import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrenciesListFragment;

@IScreenScope
@Subcomponent(modules = {ViewModelModule.class})
public interface IViewModelComponent {
    void inject(CurrenciesListFragment fragment);
}
