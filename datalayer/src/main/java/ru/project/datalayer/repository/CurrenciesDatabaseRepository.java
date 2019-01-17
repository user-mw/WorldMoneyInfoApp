package ru.project.datalayer.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.project.datalayer.database.ICurrenciesDao;
import ru.project.domainlayer.model.LocalCurrencyPair;
import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.domainlayer.model.StatisticCurrencyPair;
import ru.project.domainlayer.repository.ICurrenciesRepository;

public class CurrenciesDatabaseRepository implements ICurrenciesRepository {

    @Inject
    ICurrenciesDao mDao;

    @Inject
    public CurrenciesDatabaseRepository() {

    }

    @Override
    public Single<List<RemoteCurrencyPair>> getCurrencies(String pairs, String key) {
        return Single.fromCallable(() -> {

            String[] pairsArray = pairs.split(",");

            List<LocalCurrencyPair> localCurrencyPairList = mDao.getCurrencies(pairsArray);
            List<RemoteCurrencyPair> remoteCurrencyPairList = new ArrayList<>();

            for(int step = 0; step < localCurrencyPairList.size(); step++) {
                remoteCurrencyPairList.add(localCurrencyPairList.get(step).toRemoteCurrency());
            }

            return remoteCurrencyPairList;
        });
    }

    @Override
    public Observable<List<StatisticCurrencyPair>> getStatistic(String currencyPair) {
        return Observable.fromCallable(() -> mDao.getStatistic(currencyPair));
    }

    @Override
    public void insertCurrencies(List<RemoteCurrencyPair> currencies) {
        List<LocalCurrencyPair> localCurrencies = new ArrayList<>();
        List<StatisticCurrencyPair> statisticCurrencies = new ArrayList<>();

        for(int step = 0; step < currencies.size(); step++) {
            localCurrencies.add(currencies.get(step).toLocalCurrency());
            statisticCurrencies.add(currencies.get(step).toStatisticCurrency());
        }

        mDao.insertCurrencies(localCurrencies);
        mDao.insertStatistic(statisticCurrencies);
    }
}
