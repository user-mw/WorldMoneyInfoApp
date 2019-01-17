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
import ru.project.domainlayer.model.StatisticCurrencyData;
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
    private static final int MINIMUM_STATISTIC_VALUES_AMOUNT = 25;
    private ICurrenciesService currenciesService;

    private MutableLiveData<List<StatisticCurrencyData>> currencyStatistic = new MutableLiveData<>();
    private MutableLiveData<String[]> statisticPeriod = new MutableLiveData<>();
    private MutableLiveData<Boolean> isErrorOccurred = new MutableLiveData<>();
    private String mainCurrency;

    public StatisticViewModel(ICurrenciesService currenciesService, ISettingsService settingsService) {
        this.currenciesService = currenciesService;
        MainApplication.getUtilsComponent().inject(this);

        mainCurrency = settingsService.getMainCurrency();
    }

    public void loadStatistic(String currencyPair) {
        currenciesService.getStatistic(currencyPair)
                .flatMap(statisticCurrencyPairs -> {
                    List<StatisticCurrencyData> result = new ArrayList<>();
                    for(int step = statisticCurrencyPairs.size() - 1; step >= 0; step--) {
                        result.add(statisticCurrencyPairs.get(step));
                    }
                    return Observable.fromCallable(() -> result);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<StatisticCurrencyData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(CURRENT_TAG, "Line 60 - onSubscribe: called");
                    }

                    @Override
                    public void onNext(List<StatisticCurrencyData> statisticCurrencyPairs) {
                        if(statisticCurrencyPairs.size() >= MINIMUM_STATISTIC_VALUES_AMOUNT) {
                            currencyStatistic.postValue(statisticCurrencyPairs);

                            String statisticStart = dateUtil.getNormalDate(statisticCurrencyPairs.get(0).getTimestamp());
                            String statisticEnd = dateUtil.getNormalDate(statisticCurrencyPairs.get(statisticCurrencyPairs.size() - 1).getTimestamp());
                            statisticPeriod.postValue(new String[] {statisticStart, statisticEnd});
                            isErrorOccurred.postValue(false);
                        } else {
                            isErrorOccurred.postValue(true);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(CURRENT_TAG, "Line 79 - onError: called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(CURRENT_TAG, "Line 83 - onComplete: called");
                    }
                });
    }

    public MutableLiveData<List<StatisticCurrencyData>> getCurrencyStatistic() {
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

    public MutableLiveData<Boolean> getIsErrorOccurred() {
        return isErrorOccurred;
    }
}