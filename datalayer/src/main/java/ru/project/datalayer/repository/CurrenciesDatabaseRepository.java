package ru.project.datalayer.repository;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.project.datalayer.database.ICurrenciesDao;
import ru.project.domainlayer.model.LocalCurrencyData;
import ru.project.domainlayer.model.RemoteCurrencyData;
import ru.project.domainlayer.model.StatisticCurrencyData;
import ru.project.domainlayer.repository.ICurrenciesRepository;

public class CurrenciesDatabaseRepository implements ICurrenciesRepository {
    @Inject
    ICurrenciesDao dao;
    private static final String CURRENT_TAG = "CurrenciesDBRepository";
    private static final String ARRAY_SPLIT_SYMBOL = ",";

    @Inject
    public CurrenciesDatabaseRepository() {
        Log.d(CURRENT_TAG, "Line 27 - CurrenciesDatabaseRepository constructor: called");
    }

    @Override
    public Single<List<RemoteCurrencyData>> getCurrencies(String pairs, String key) {
        return Single.fromCallable(() -> {

            String[] pairsArray = pairs.split(ARRAY_SPLIT_SYMBOL);

            List<LocalCurrencyData> localCurrencyPairList = dao.getCurrencies(pairsArray);
            List<RemoteCurrencyData> remoteCurrencyDataUnitList = new ArrayList<>();

            for(int step = 0; step < localCurrencyPairList.size(); step++) {
                remoteCurrencyDataUnitList.add(localCurrencyPairList.get(step).toRemoteCurrency());
            }

            return remoteCurrencyDataUnitList;
        });
    }

    @Override
    public Observable<List<StatisticCurrencyData>> getStatistic(String currencyPair) {
        return Observable.fromCallable(() -> dao.getStatistic(currencyPair));
    }

    @Override
    public void insertCurrencies(List<RemoteCurrencyData> currencies) {
        List<LocalCurrencyData> localCurrencies = new ArrayList<>();
        List<StatisticCurrencyData> statisticCurrencies = new ArrayList<>();

        for(int step = 0; step < currencies.size(); step++) {
            localCurrencies.add(currencies.get(step).toLocalCurrency());
            statisticCurrencies.add(currencies.get(step).toStatisticCurrency());
        }

        dao.insertCurrencies(localCurrencies);
        dao.insertStatistic(statisticCurrencies);
    }
}