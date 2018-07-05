package ru.project.worldmoneyinfo.dependency;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DatabaseModule.class, ApiModule.class})
public interface IApplicationComponent {
    IScreenComponent plusScreenComponent(RepositoryModule repositoryModule, ServiceModule serviceModule);
}
