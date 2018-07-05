package ru.project.domainlayer.repository;

import java.util.List;

import io.reactivex.Single;
import ru.project.domainlayer.model.RemoteCurrencyPair;

public interface ICurrenciesRepository {
    String REMOTE_REPOSITORY = "RemoteRepository";
    String LOCAL_REPOSITORY = "LocalRepository";

    Single<List<RemoteCurrencyPair>> getCurrencies(String pairs, String key);
    void insertCurrencies(List<RemoteCurrencyPair> currencies);
}
