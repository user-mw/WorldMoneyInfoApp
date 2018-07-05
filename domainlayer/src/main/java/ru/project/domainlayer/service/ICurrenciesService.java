package ru.project.domainlayer.service;

import java.util.List;

import io.reactivex.Single;
import ru.project.domainlayer.model.RemoteCurrencyPair;

public interface ICurrenciesService {
    Single<List<RemoteCurrencyPair>> getCurrencies(String pairs, String key);
    void insertCurrencies(List<RemoteCurrencyPair> currencies);
}
