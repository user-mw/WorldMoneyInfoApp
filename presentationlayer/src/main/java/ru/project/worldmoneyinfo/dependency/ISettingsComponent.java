package ru.project.worldmoneyinfo.dependency;

import dagger.Subcomponent;
import ru.project.worldmoneyinfo.ui.settings_screen.SettingsFragment;

@IScreenScope
@Subcomponent(modules = {RepositoryModule.class, ServiceModule.class, SettingsAdditionalModule.class})
public interface ISettingsComponent {
    void inject(SettingsFragment fragment);
}
