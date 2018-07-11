package ru.project.worldmoneyinfo.dependency;

import javax.inject.Singleton;

import dagger.Component;
import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrencyViewModel;

@Singleton
@Component(modules = {DatabaseModule.class, ApiModule.class})
public interface IApplicationComponent {
    IScreenComponent plusScreenComponent(RepositoryModule repositoryModule, ServiceModule serviceModule, AppDataModule appDataModule);

    void injectUtils(CurrencyViewModel viewModel);
}
