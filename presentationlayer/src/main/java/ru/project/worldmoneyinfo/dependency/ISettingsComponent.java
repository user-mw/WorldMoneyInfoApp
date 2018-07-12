package ru.project.worldmoneyinfo.dependency;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SettingsModule.class})
public interface ISettingsComponent {
    ISettingsAdditionalComponent plusAdditionalComponent(SettingsAdditionalModule module);
}
