package ru.project.domainlayer.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import ru.project.domainlayer.model.RemoteCurrencyData;
import ru.project.domainlayer.model.StatisticCurrencyData;
import ru.project.domainlayer.repository.ICurrenciesRepository;

public class CurrenciesServiceImpl implements ICurrenciesService {
    @Inject
    @Named(ICurrenciesRepository.REMOTE_REPOSITORY)
    ICurrenciesRepository remoteRepository;

    @Inject
    @Named(ICurrenciesRepository.LOCAL_REPOSITORY)
    ICurrenciesRepository localRepository;

    @Inject
    public CurrenciesServiceImpl() {

    }

    @Override
    public Single<List<RemoteCurrencyData>> getCurrencies(String currencyPairs, String key) {
        return remoteRepository.getCurrencies(currencyPairs, key)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response -> localRepository.insertCurrencies(response))
                .onErrorReturn(throwable -> {
                    List<RemoteCurrencyData> currencyPairList = localRepository.getCurrencies(currencyPairs, key).blockingGet();
                    if(currencyPairList.size() > 0) {
                        return currencyPairList;
                    }
                    return null;
                });
    }

    @Override
    public Observable<List<StatisticCurrencyData>> getStatistic(String currencyPair) {
        return localRepository.getStatistic(currencyPair);
    }
}
