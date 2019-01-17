package ru.project.domainlayer.service;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.domainlayer.model.StatisticCurrencyPair;

public interface ICurrenciesService {
    Single<List<RemoteCurrencyPair>> getCurrencies(String pairs, String key);
    Observable<List<StatisticCurrencyPair>> getStatistic(String currencyPair);
    void insertCurrencies(List<RemoteCurrencyPair> currencies);
}
