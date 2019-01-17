package ru.project.worldmoneyinfo.ui.statistic_screen;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    private LinearLayout statisticLayout;
    private TextView statisticName;
    private TextView statisticPeriod;
    private TextView statisticErrorMessage;
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
        statisticLayout = view.findViewById(R.id.statistic_layout);
        statisticChart = view.findViewById(R.id.statistic_chart);
        statisticName = view.findViewById(R.id.statistic_title);
        statisticPeriod = view.findViewById(R.id.statistic_period);
        statisticErrorMessage = view.findViewById(R.id.statistic_error_message);
        configureTitle();

        viewModel.getCurrencyStatistic().observe(this, currencies -> {
            if(currencies != null) {
                configureStatisticChart(currencies);
            }
        });

        viewModel.getStatisticPeriod().observe(this, periodData -> {
            if(periodData != null) {
                statisticLayout.setVisibility(View.VISIBLE);
                String statisticPeriodText = String.format(getString(R.string.statistic_period), periodData[0], periodData[1]);
                statisticPeriod.setText(statisticPeriodText);
                statisticErrorMessage.setVisibility(View.GONE);
            }
        });

        viewModel.getIsErrorOccurred().observe(this, isError -> {
            if(isError != null && isError) {
                statisticLayout.setVisibility(View.GONE);
                statisticErrorMessage.setVisibility(View.VISIBLE);
            }
        });
    }

    private void configureTitle() {
        String statisticNameText = String.format(getString(R.string.statistic_of_currency), viewModel.getMainCurrency(), viewModel.getSecondCurrency(currencyPair));
        statisticName.setText(statisticNameText);
    }

    private void configureStatisticChart(List<StatisticCurrencyPair> statistic) {
        List<Entry> entries = new ArrayList<>();

        for(int step = 0; step < statistic.size(); step++) {
            Float value = Float.parseFloat(statistic.get(step).getPrice());
            entries.add(new Entry(step, value));
        }

        String title = getString(R.string.statistic_title);

        int lineColor;

        if(getActivity() != null) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                lineColor = ContextCompat.getColor(getActivity(), R.color.textColorRed);
            } else {
                lineColor = getActivity().getResources().getColor(R.color.textColorRed);
            }

            LineDataSet dataSet = new LineDataSet(entries, title);
            dataSet.setLineWidth(LINE_WIDTH);
            dataSet.setMode(LineDataSet.Mode.STEPPED);
            dataSet.setDrawCircles(false);
            dataSet.setDrawCircleHole(false);
            dataSet.setColor(lineColor);
            dataSet.setValueTextSize(0);

            LineData data = new LineData(dataSet);
            statisticChart.getDescription().setText("");
            statisticChart.setData(data);
            statisticChart.invalidate();
        }
    }

    @Override
    protected void loadData() {
        viewModel.loadStatistic(currencyPair);
    }
}