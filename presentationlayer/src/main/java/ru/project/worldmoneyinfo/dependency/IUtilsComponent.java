package ru.project.worldmoneyinfo.dependency;

import dagger.Component;
import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrencyViewModel;

@Component
public interface IUtilsComponent {
    void inject(CurrencyViewModel viewModel);
}
