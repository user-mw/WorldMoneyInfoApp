package ru.project.datalayer.repository;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.project.datalayer.api.IOneForgeApi;
import ru.project.domainlayer.model.RemoteCurrencyData;
import ru.project.domainlayer.model.StatisticCurrencyData;
import ru.project.domainlayer.repository.ICurrenciesRepository;

public class CurrenciesServerRepository implements ICurrenciesRepository {
    @Inject
    IOneForgeApi api;
    private static final String CURRENT_TAG = "ServerRepository";

    @Inject
    public CurrenciesServerRepository() {
        Log.d(CURRENT_TAG, "Line 23 - ServerRepository constructor: called");
    }

    @Override
    public Single<List<RemoteCurrencyData>> getCurrencies(String pairs, String key) {
        return api.getCurrenciesInfo(pairs, key);
    }

    @Override
    public Observable<List<StatisticCurrencyData>> getStatistic(String currencyPair) {
        throw new IllegalStateException("Implementation for database");
    }

    @Override
    public void insertCurrencies(List<RemoteCurrencyData> currencies) {
        // for database
    }
}