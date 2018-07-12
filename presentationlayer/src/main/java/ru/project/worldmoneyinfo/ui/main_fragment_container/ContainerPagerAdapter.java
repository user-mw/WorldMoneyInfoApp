package ru.project.worldmoneyinfo.ui.main_fragment_container;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContainerPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTabsTitles = new ArrayList<>();

    public ContainerPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabsTitles.get(position);
    }

    public void addAll(List<Fragment> fragments) {
        mFragmentList.addAll(fragments);
    }

    public void setTabsTitles(List<String> tabsTitles) {
        mTabsTitles = tabsTitles;
    }
}
