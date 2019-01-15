package ru.project.worldmoneyinfo.ui.statistic_screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import ru.project.worldmoneyinfo.MainApplication;
import ru.project.worldmoneyinfo.R;
import ru.project.worldmoneyinfo.dependency.ViewModelModule;
import ru.project.worldmoneyinfo.ui.BaseFragment;

public class StatisticFragment extends BaseFragment {
    @Inject
    StatisticViewModel viewModel;

    private static final String CURRENCY_PAIR_KEY = "CurrentCurrencyPair";
    private String currencyPair;

    public static StatisticFragment newInstance(String currencyPair) {
        Bundle args = new Bundle();
        args.putString(CURRENCY_PAIR_KEY, currencyPair);

        StatisticFragment fragment = new StatisticFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void prepareViewModel() {
        MainApplication.getApplicationComponent()
                .plusViewModelComponent(new ViewModelModule(this))
                .inject(this);

        if(getArguments() != null) {
            currencyPair = getArguments().getString(CURRENCY_PAIR_KEY);
        }
    }

    @Override
    protected View retrieveView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.statistic_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel.getCurrencyStatistic().observe(this, currencies -> {
            if(currencies != null) {
                Toast.makeText(getActivity(), String.valueOf(currencies.size()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void loadData() {
        viewModel.loadStatistic(currencyPair);
    }
}
