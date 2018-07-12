package ru.project.worldmoneyinfo.ui.main_fragment_container;

import android.support.v4.app.Fragment;

import java.util.List;

import javax.inject.Inject;

public class ContainerFragmentViewModel {

    @Inject
    List<Fragment> mFragments;

    @Inject
    public ContainerFragmentViewModel() {

    }

    public List<Fragment> getFragments() {
        return mFragments;
    }
}
