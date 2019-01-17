package ru.project.domainlayer.repository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.project.domainlayer.model.RemoteCurrencyData;
import ru.project.domainlayer.model.StatisticCurrencyData;

public interface ICurrenciesRepository {
    String REMOTE_REPOSITORY = "RemoteRepository";
    String LOCAL_REPOSITORY = "LocalRepository";

    Single<List<RemoteCurrencyData>> getCurrencies(String pairs, String key);
    Observable<List<StatisticCurrencyData>> getStatistic(String currencyPair);
    void insertCurrencies(List<RemoteCurrencyData> currencies);
}
