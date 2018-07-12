package ru.project.worldmoneyinfo.ui.settings_screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.project.worldmoneyinfo.databinding.SettingsBinding;
import ru.project.worldmoneyinfo.ui.BaseFragment;

public class SettingsFragment extends BaseFragment {

    private SettingsViewModel mViewModel;
    private List<String> mCurrenciesList = new ArrayList<>();

    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();
        
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void prepareViewModel() {

        if(mCurrenciesList.size() == 0) {
            mCurrenciesList.add("RUB");
            mCurrenciesList.add("EUR");
            mCurrenciesList.add("USD");
            mCurrenciesList.add("GBP");
            mCurrenciesList.add("PLN");
        }

        mViewModel = new SettingsViewModel(mCurrenciesList);
    }

    @Override
    protected View retrieveView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        SettingsBinding binding = SettingsBinding.inflate(inflater, container, false);
        binding.setCurrentViewModel(mViewModel);

        return binding.getRoot();
    }

    @Override
    protected void loadData() {

    }
}
