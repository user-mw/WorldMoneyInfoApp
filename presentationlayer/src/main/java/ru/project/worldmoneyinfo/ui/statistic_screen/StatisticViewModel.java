package ru.project.worldmoneyinfo.ui.statistic_screen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.project.domainlayer.model.StatisticCurrencyPair;
import ru.project.domainlayer.service.ICurrenciesService;

public class StatisticViewModel extends ViewModel {
    private static final String CURRENT_TAG = "StatisticViewModel";
    private ICurrenciesService currenciesService;

    private MutableLiveData<List<StatisticCurrencyPair>> currencyStatistic = new MutableLiveData<>();

    public StatisticViewModel(ICurrenciesService currenciesService) {
        this.currenciesService = currenciesService;
    }

    public void loadStatistic(String currencyPair) {
        String timestampStart = String
                .valueOf(
                        TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - 500000
                );

        String timestampEnd = String
                .valueOf(
                        TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
                );

        currenciesService.getStatistic(currencyPair)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<StatisticCurrencyPair>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(CURRENT_TAG, "Line 33 - onSubscribe: called");
                    }

                    @Override
                    public void onNext(List<StatisticCurrencyPair> statisticCurrencyPairs) {
                        currencyStatistic.postValue(statisticCurrencyPairs);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(CURRENT_TAG, "Line 43 - onError: called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(CURRENT_TAG, "Line 48 - onComplete: called");
                    }
                });
    }

    public MutableLiveData<List<StatisticCurrencyPair>> getCurrencyStatistic() {
        return currencyStatistic;
    }
}
