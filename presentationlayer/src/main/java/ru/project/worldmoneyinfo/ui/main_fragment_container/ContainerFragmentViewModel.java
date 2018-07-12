package ru.project.worldmoneyinfo.ui.main_fragment_container;

import android.support.v4.app.Fragment;

import java.util.List;

import javax.inject.Inject;

public class ContainerFragmentViewModel {

    @Inject
    List<Fragment> mFragments;

    @Inject
    List<String> mTitles;

    @Inject
    public ContainerFragmentViewModel() {

    }

    public List<Fragment> getFragments() {
        return mFragments;
    }

    public List<String> getTitles() {
        return mTitles;
    }
}
