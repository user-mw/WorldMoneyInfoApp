package ru.project.domainlayer.repository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.domainlayer.model.StatisticCurrencyPair;

public interface ICurrenciesRepository {
    String REMOTE_REPOSITORY = "RemoteRepository";
    String LOCAL_REPOSITORY = "LocalRepository";

    Single<List<RemoteCurrencyPair>> getCurrencies(String pairs, String key);
    Observable<List<StatisticCurrencyPair>> getStatistic(String currencyPair);
    void insertCurrencies(List<RemoteCurrencyPair> currencies);
}
