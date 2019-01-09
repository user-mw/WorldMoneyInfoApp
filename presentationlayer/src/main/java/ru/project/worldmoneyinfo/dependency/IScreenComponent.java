package ru.project.worldmoneyinfo.dependency;

import dagger.Subcomponent;
import ru.project.worldmoneyinfo.utils.CustomViewModelFactory;

@IScreenScope
@Subcomponent(modules = {RepositoryModule.class, ServiceModule.class})
public interface IScreenComponent {
    void inject(CustomViewModelFactory factory);
}
