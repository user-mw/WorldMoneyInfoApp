package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import ru.project.worldmoneyinfo.MainApplication;
import ru.project.worldmoneyinfo.R;
import ru.project.worldmoneyinfo.dependency.ViewModelModule;
import ru.project.worldmoneyinfo.ui.BaseFragment;

public class CurrenciesListFragment extends BaseFragment {

    private SwipeRefreshLayout dataUpdater;
    private TextView errorText;

    private RecyclerView currenciesList;
    private CurrenciesAdapter currenciesAdapter;

    @Inject
    CurrenciesListViewModel mViewModel;

    public static CurrenciesListFragment newInstance() {
        Bundle arguments = new Bundle();

        CurrenciesListFragment fragment = new CurrenciesListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }
    
    @Override
    protected void prepareViewModel() {
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

        mViewModel.getIsLoading().observe(this, isLoading -> {
            if(isLoading != null) {
                dataUpdater.post(() -> dataUpdater.setRefreshing(isLoading));
            }
        });

        mViewModel.getIsErrorOccurred().observe(this, isError -> {
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

        mViewModel.getCurrencies().observe(this, currencies -> {
            if(currencies != null) {
                currenciesAdapter = new CurrenciesAdapter(currencies, mViewModel.getMainCurrency());
                currenciesList.setAdapter(currenciesAdapter);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        dataUpdater.setOnRefreshListener(mViewModel.getRefreshListener());
    }

    @Override
    protected void loadData() {
        mViewModel.loadCurrenciesList();
    }
}