package ru.project.domainlayer.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.domainlayer.repository.ICurrenciesRepository;

public class CurrenciesServiceImpl implements ICurrenciesService {

    @Inject
    @Named(ICurrenciesRepository.REMOTE_REPOSITORY)
    ICurrenciesRepository mRemoteRepository;

    @Inject
    @Named(ICurrenciesRepository.LOCAL_REPOSITORY)
    ICurrenciesRepository mLocalRepository;

    @Inject
    public CurrenciesServiceImpl() {

    }

    @Override
    public Single<List<RemoteCurrencyPair>> getCurrencies(String pairs, String key) {
        return mRemoteRepository.getCurrencies(pairs, key)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response -> mLocalRepository.insertCurrencies(response))
                .onErrorReturn(throwable -> mLocalRepository.getCurrencies(pairs, key).blockingGet());
    }

    @Override
    public void insertCurrencies(List<RemoteCurrencyPair> currencies) {
        mLocalRepository.insertCurrencies(currencies);
    }
}
