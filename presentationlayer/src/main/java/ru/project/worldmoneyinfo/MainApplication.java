package ru.project.worldmoneyinfo;

import android.app.Application;
import android.support.v4.app.Fragment;

import java.util.List;

import ru.project.worldmoneyinfo.dependency.ApiModule;
import ru.project.worldmoneyinfo.dependency.DaggerIApplicationComponent;
import ru.project.worldmoneyinfo.dependency.DaggerIMainContainerComponent;
import ru.project.worldmoneyinfo.dependency.DaggerISettingsComponent;
import ru.project.worldmoneyinfo.dependency.DatabaseModule;
import ru.project.worldmoneyinfo.dependency.IApplicationComponent;
import ru.project.worldmoneyinfo.dependency.IMainContainerComponent;
import ru.project.worldmoneyinfo.dependency.ISettingsComponent;
import ru.project.worldmoneyinfo.dependency.MainContainerModule;
import ru.project.worldmoneyinfo.dependency.SettingsModule;

public class MainApplication extends Application {
    private static IApplicationComponent sApplicationComponent;
    private static IMainContainerComponent sMainContainerComponent;
    private static ISettingsComponent sSettingsComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sApplicationComponent = DaggerIApplicationComponent.builder()
                .databaseModule(new DatabaseModule(this))
                .apiModule(new ApiModule())
                .build();

        sSettingsComponent = DaggerISettingsComponent.builder()
                .settingsModule(new SettingsModule(this))
                .build();
    }

    public static IApplicationComponent getApplicationComponent() {
        return sApplicationComponent;
    }

    public static IMainContainerComponent getMainContainerComponent(List<Fragment> fragmentList, List<String> titles) {
        if(sMainContainerComponent == null) {
            sMainContainerComponent = DaggerIMainContainerComponent.builder()
                    .mainContainerModule(new MainContainerModule(fragmentList, titles))
                    .build();
        }

        return sMainContainerComponent;
    }

    public static ISettingsComponent getSettingsComponent() {
        return sSettingsComponent;
    }
}
