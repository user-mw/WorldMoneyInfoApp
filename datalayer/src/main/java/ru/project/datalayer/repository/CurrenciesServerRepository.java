package ru.project.datalayer.repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.project.datalayer.api.IOneForgeApi;
import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.domainlayer.model.StatisticCurrencyPair;
import ru.project.domainlayer.repository.ICurrenciesRepository;

public class CurrenciesServerRepository implements ICurrenciesRepository {

    @Inject
    IOneForgeApi mApi;

    @Inject
    public CurrenciesServerRepository() {

    }

    @Override
    public Single<List<RemoteCurrencyPair>> getCurrencies(String pairs, String key) {
        return mApi.getCurrenciesInfo(pairs, key);
    }

    @Override
    public Observable<List<StatisticCurrencyPair>> getStatistic(String currencyPair) {
        throw new IllegalStateException("Implementation for database");
    }

    @Override
    public void insertCurrencies(List<RemoteCurrencyPair> currencies) {
        // for database
    }
}
