package ru.project.worldmoneyinfo.dependency;

import android.support.v4.app.Fragment;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainContainerModule {
    private List<Fragment> mFragments;

    public MainContainerModule(List<Fragment> fragments) {
        mFragments = fragments;
    }

    @Provides
    @Singleton
    public List<Fragment> provideFragments() {
        return mFragments;
    }
}
