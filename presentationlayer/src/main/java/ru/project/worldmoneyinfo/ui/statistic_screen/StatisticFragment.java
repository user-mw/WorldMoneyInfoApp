package ru.project.worldmoneyinfo.ui.statistic_screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.project.domainlayer.model.StatisticCurrencyPair;
import ru.project.worldmoneyinfo.MainApplication;
import ru.project.worldmoneyinfo.R;
import ru.project.worldmoneyinfo.dependency.ViewModelModule;
import ru.project.worldmoneyinfo.ui.BaseFragment;

public class StatisticFragment extends BaseFragment {
    @Inject
    StatisticViewModel viewModel;

    private static final String CURRENCY_PAIR_KEY = "CurrentCurrencyPair";
    private static final float LINE_WIDTH = 4.0f;
    private String currencyPair;

    private TextView statisticName;
    private LineChart statisticChart;

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
        statisticChart = view.findViewById(R.id.statistic_chart);
        statisticName = view.findViewById(R.id.statistic_title);
        configureTitle();

        viewModel.getCurrencyStatistic().observe(this, currencies -> {
            if(currencies != null) {
                configureStatisticChart(currencies);
            }
        });
    }

    private void configureTitle() {
        String statisticNameText = String.format(getString(R.string.statistic_of_currency), "RUB", "USD");
        statisticName.setText(statisticNameText);
    }

    private void configureStatisticChart(List<StatisticCurrencyPair> statistic) {
        List<Entry> entries = new ArrayList<>();

        for(int step = 0; step < statistic.size(); step++) {
            Float value = Float.parseFloat(statistic.get(step).getPrice());
            entries.add(new Entry(step, value));
        }

        String title = getString(R.string.statistic_title);

        LineDataSet dataSet = new LineDataSet(entries, title);
        dataSet.setLineWidth(LINE_WIDTH);
        dataSet.setMode(LineDataSet.Mode.STEPPED);
        dataSet.setDrawCircles(false);
        dataSet.setDrawCircleHole(false);
        dataSet.setValueTextSize(0);

        LineData data = new LineData(dataSet);
        statisticChart.getDescription().setText("");
        statisticChart.setData(data);
        statisticChart.invalidate();
    }

    @Override
    protected void loadData() {
        viewModel.loadStatistic(currencyPair);
    }
}
