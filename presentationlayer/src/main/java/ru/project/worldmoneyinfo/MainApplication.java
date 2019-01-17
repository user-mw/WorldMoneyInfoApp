package ru.project.worldmoneyinfo;

import android.app.Application;

import ru.project.worldmoneyinfo.dependency.ApiModule;
import ru.project.worldmoneyinfo.dependency.DaggerIApplicationComponent;
import ru.project.worldmoneyinfo.dependency.DaggerIUtilsComponent;
import ru.project.worldmoneyinfo.dependency.DatabaseModule;
import ru.project.worldmoneyinfo.dependency.IApplicationComponent;
import ru.project.worldmoneyinfo.dependency.IUtilsComponent;
import ru.project.worldmoneyinfo.dependency.SettingsModule;
import ru.project.worldmoneyinfo.dependency.UtilsModule;

public class MainApplication extends Application {
    private static IApplicationComponent applicationComponent;
    private static IUtilsComponent utilsComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerIApplicationComponent.builder()
                .databaseModule(new DatabaseModule(this))
                .apiModule(new ApiModule())
                .settingsModule(new SettingsModule(this))
                .build();

        utilsComponent = DaggerIUtilsComponent.builder().utilsModule(new UtilsModule(this)).build();
    }

    public static IApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static IUtilsComponent getUtilsComponent() {
        return utilsComponent;
    }
}