package ru.project.domainlayer.service;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.project.domainlayer.model.RemoteCurrencyData;
import ru.project.domainlayer.model.StatisticCurrencyData;

public interface ICurrenciesService {
    Single<List<RemoteCurrencyData>> getCurrencies(String pairs, String key);
    Observable<List<StatisticCurrencyData>> getStatistic(String currencyPair);
}
