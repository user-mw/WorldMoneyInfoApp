package ru.project.worldmoneyinfo.ui.statistic_screen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.project.domainlayer.model.StatisticCurrencyPair;
import ru.project.domainlayer.service.ICurrenciesService;
import ru.project.domainlayer.service.ISettingsService;
import ru.project.domainlayer.utils.CurrencyUtil;
import ru.project.domainlayer.utils.DateUtil;
import ru.project.worldmoneyinfo.MainApplication;

public class StatisticViewModel extends ViewModel {
    @Inject
    CurrencyUtil currencyUtil;
    @Inject
    DateUtil dateUtil;

    private static final String CURRENT_TAG = "StatisticViewModel";
    private ICurrenciesService currenciesService;

    private MutableLiveData<List<StatisticCurrencyPair>> currencyStatistic = new MutableLiveData<>();
    private MutableLiveData<String[]> statisticPeriod = new MutableLiveData<>();
    private String mainCurrency;

    public StatisticViewModel(ICurrenciesService currenciesService, ISettingsService settingsService) {
        this.currenciesService = currenciesService;
        MainApplication.getUtilsComponent().inject(this);

        mainCurrency = settingsService.getMainCurrency();
    }

    public void loadStatistic(String currencyPair) {
        currenciesService.getStatistic(currencyPair)
                .flatMap(statisticCurrencyPairs -> {
                    List<StatisticCurrencyPair> result = new ArrayList<>();
                    for(int step = statisticCurrencyPairs.size() - 1; step >= 0; step--) {
                        result.add(statisticCurrencyPairs.get(step));
                    }
                    return Observable.fromCallable(() -> result);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<StatisticCurrencyPair>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(CURRENT_TAG, "Line 42 - onSubscribe: called");
                    }

                    @Override
                    public void onNext(List<StatisticCurrencyPair> statisticCurrencyPairs) {
                        currencyStatistic.postValue(statisticCurrencyPairs);

                        String statisticStart = dateUtil.getNormalDate(statisticCurrencyPairs.get(0).getTimestamp());
                        String statisticEnd = dateUtil.getNormalDate(statisticCurrencyPairs.get(statisticCurrencyPairs.size() - 1).getTimestamp());
                        statisticPeriod.postValue(new String[] {statisticStart, statisticEnd});
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(CURRENT_TAG, "Line 52 - onError: called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(CURRENT_TAG, "Line 56 - onComplete: called");
                    }
                });
    }

    public MutableLiveData<List<StatisticCurrencyPair>> getCurrencyStatistic() {
        return currencyStatistic;
    }

    public MutableLiveData<String[]> getStatisticPeriod() {
        return statisticPeriod;
    }

    public String getMainCurrency() {
        return mainCurrency;
    }

    public String getSecondCurrency(String currencyPair) {
        return currencyUtil.getSecondCurrencyFromPair(currencyPair, mainCurrency);
    }
}
