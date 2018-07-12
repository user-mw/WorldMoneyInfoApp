package ru.project.worldmoneyinfo.dependency;

import dagger.Subcomponent;
import ru.project.worldmoneyinfo.ui.settings_screen.SettingsFragment;

@Subcomponent(modules = {SettingsAdditionalModule.class})
public interface ISettingsAdditionalComponent {
    void inject(SettingsFragment fragment);
}
