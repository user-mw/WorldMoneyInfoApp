package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ru.project.worldmoneyinfo.MainApplication;
import ru.project.worldmoneyinfo.databinding.CurrenciesListBinding;
import ru.project.worldmoneyinfo.dependency.AppDataModule;
import ru.project.worldmoneyinfo.dependency.RepositoryModule;
import ru.project.worldmoneyinfo.dependency.ServiceModule;
import ru.project.worldmoneyinfo.ui.BaseFragment;

public class CurrenciesListFragment extends BaseFragment {

    public static final String UPDATE_DATA_COMMAND = "ru.project.worldmoneyinfo.UpdateDataCommand";

    @Inject
    CurrenciesListViewModel mViewModel;

    private DataUpdatingReceiver mReceiver = new DataUpdatingReceiver();

    public static CurrenciesListFragment newInstance() {
        Bundle arguments = new Bundle();

        CurrenciesListFragment fragment = new CurrenciesListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }
    
    @Override
    protected void prepareViewModel() {
        MainApplication.getApplicationComponent().plusScreenComponent(
                new RepositoryModule(),
                new ServiceModule(),
                // use your own key for result
                new AppDataModule("")
        ).inject(this);
    }

    @Override
    protected View retrieveView(@NonNull LayoutInflater layoutInflater, ViewGroup container) {
        CurrenciesListBinding binding = CurrenciesListBinding.inflate(layoutInflater, container, false);
        binding.setCurrentViewModel(mViewModel);
        return binding.getRoot();
    }

    @Override
    protected void loadData() {
        mViewModel.loadCurrenciesList();
    }

    @Override
    public void onResume() {
        super.onResume();

        if(getActivity() != null) {
            getActivity().registerReceiver(mReceiver, new IntentFilter(UPDATE_DATA_COMMAND));
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if(getActivity() != null) {
            getActivity().unregisterReceiver(mReceiver);
        }
    }

    private class DataUpdatingReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            loadData();
        }
    }
}
