package ru.project.worldmoneyinfo;

import android.app.Application;

import ru.project.worldmoneyinfo.dependency.ApiModule;
import ru.project.worldmoneyinfo.dependency.DaggerIApplicationComponent;
import ru.project.worldmoneyinfo.dependency.DatabaseModule;
import ru.project.worldmoneyinfo.dependency.IApplicationComponent;

public class MainApplication extends Application {
    private static IApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerIApplicationComponent.builder()
                .databaseModule(new DatabaseModule(this))
                .apiModule(new ApiModule())
                .build();
    }

    public static IApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
