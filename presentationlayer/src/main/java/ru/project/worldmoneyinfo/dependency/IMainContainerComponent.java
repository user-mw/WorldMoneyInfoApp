package ru.project.worldmoneyinfo.dependency;

import javax.inject.Singleton;

import dagger.Component;
import ru.project.worldmoneyinfo.ui.main_fragment_container.ContainerFragment;

@Singleton
@Component(modules = {MainContainerModule.class})
public interface IMainContainerComponent {
    void inject(ContainerFragment fragment);
}
