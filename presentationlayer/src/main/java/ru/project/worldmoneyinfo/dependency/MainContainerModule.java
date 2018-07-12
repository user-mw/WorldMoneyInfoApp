package ru.project.worldmoneyinfo.dependency;

import android.support.v4.app.Fragment;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainContainerModule {
    private List<Fragment> mFragments;
    private List<String> mTitles;

    public MainContainerModule(List<Fragment> fragments, List<String> titles) {
        mFragments = fragments;
        mTitles = titles;
    }

    @Provides
    @Singleton
    public List<Fragment> provideFragments() {
        return mFragments;
    }

    @Provides
    @Singleton
    public List<String> provideTitles() {
        return mTitles;
    }
}
