package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import ru.project.worldmoneyinfo.MainActivity;
import ru.project.worldmoneyinfo.MainApplication;
import ru.project.worldmoneyinfo.R;
import ru.project.worldmoneyinfo.dependency.ViewModelModule;
import ru.project.worldmoneyinfo.ui.BaseFragment;
import ru.project.worldmoneyinfo.ui.statistic_screen.StatisticFragment;

public class CurrenciesListFragment extends BaseFragment {
    @Inject
    CurrenciesListViewModel viewModel;

    public static final String UPDATE_ACTION = "ru.project.worldmoneyinfo.UPDATE_ACTION";

    private SwipeRefreshLayout dataUpdater;
    private TextView errorText;

    private RecyclerView currenciesList;
    private CurrenciesAdapter currenciesAdapter;

    private CurrenciesAdapter.IOnElementClick onElementClick = currencyPair -> {
        if(getActivity() != null && getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).changeFragment(StatisticFragment.newInstance(currencyPair));
        }
    };

    private LocalBroadcastManager localBroadcastManager;
    private BroadcastReceiver receiverForUpdate = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            viewModel.loadCurrenciesList();
        }
    };

    public static CurrenciesListFragment newInstance() {
        Bundle arguments = new Bundle();

        CurrenciesListFragment fragment = new CurrenciesListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }
    
    @Override
    protected void prepareViewModel() {
        if(getActivity() != null) {
            IntentFilter filterForReceiver = new IntentFilter(UPDATE_ACTION);
            localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
            localBroadcastManager.registerReceiver(receiverForUpdate, filterForReceiver);
        }

        MainApplication.getApplicationComponent()
                .plusViewModelComponent(new ViewModelModule(this))
                .inject(this);
    }

    @Override
    protected View retrieveView(@NonNull LayoutInflater layoutInflater, ViewGroup container) {
        return layoutInflater.inflate(R.layout.currencies_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        dataUpdater = view.findViewById(R.id.data_updater);
        currenciesList = view.findViewById(R.id.currencies_list);
        errorText = view.findViewById(R.id.error_text);

        currenciesList.setLayoutManager(new LinearLayoutManager(getActivity()));

        viewModel.getIsLoading().observe(this, isLoading -> {
            if(isLoading != null) {
                dataUpdater.post(() -> dataUpdater.setRefreshing(isLoading));
            }
        });

        viewModel.getIsErrorOccurred().observe(this, isError -> {
            if(isError != null) {
                if(isError) {
                    currenciesList.setVisibility(View.GONE);
                    errorText.setVisibility(View.VISIBLE);
                } else {
                    currenciesList.setVisibility(View.VISIBLE);
                    errorText.setVisibility(View.GONE);
                }
            }
        });

        currenciesAdapter = new CurrenciesAdapter(viewModel.getMainCurrency(), onElementClick);
        currenciesList.setAdapter(currenciesAdapter);

        viewModel.getCurrencies().observe(this, currencies -> {
            if(currencies != null && currencies.size() > 0) {
                currenciesAdapter.addNewData(currencies);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        dataUpdater.setOnRefreshListener(viewModel.getRefreshListener());
    }

    @Override
    protected void loadData() {
        viewModel.startUpdate();
    }

    @Override
    public void onStop() {
        viewModel.onDetach();
        super.onStop();
    }

    @Override
    public void onDetach() {
        localBroadcastManager.unregisterReceiver(receiverForUpdate);
        super.onDetach();
    }
}