package ru.project.worldmoneyinfo.ui.main_fragment_container;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.project.worldmoneyinfo.MainApplication;
import ru.project.worldmoneyinfo.R;
import ru.project.worldmoneyinfo.databinding.ContainerFragmentBinding;
import ru.project.worldmoneyinfo.ui.BaseFragment;
import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrenciesListFragment;

public class ContainerFragment extends BaseFragment {

    @Inject
    ContainerFragmentViewModel mViewModel;

    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitles = new ArrayList<>();

    public static ContainerFragment newInstance() {
        Bundle args = new Bundle();
        
        ContainerFragment fragment = new ContainerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void prepareViewModel() {
        mFragmentList.add(CurrenciesListFragment.newInstance());

        if(getActivity() != null) {
            mTitles.add(getActivity().getString(R.string.rates_title));
        }

        MainApplication.getMainContainerComponent(mFragmentList, mTitles).inject(this);
    }

    @Override
    protected View retrieveView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        ContainerFragmentBinding binding = ContainerFragmentBinding.inflate(inflater, container, false);
        binding.setCurrentViewModel(mViewModel);

        return binding.getRoot();
    }

    @Override
    protected void loadData() {

    }
}
