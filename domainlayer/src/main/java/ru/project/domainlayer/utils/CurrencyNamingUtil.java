package ru.project.domainlayer.utils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class CurrencyNamingUtil {
    private Map<String, String> mCurrenciesNames = new HashMap<>();

    @Inject
    public CurrencyNamingUtil() {
        setCurrenciesNames();
    }

    public String getNormalName(String currencyPair, String mainCurrency) {
        String singleSymbol = currencyPair.replace(mainCurrency, "");

        return mCurrenciesNames.get(singleSymbol);
    }

    private void setCurrenciesNames() {
        mCurrenciesNames.put("RUB", "Russian Ruble");
        mCurrenciesNames.put("EUR", "Euro");
        mCurrenciesNames.put("USD", "United States Dollar");
        mCurrenciesNames.put("PLN", "Polish Zloty");
        mCurrenciesNames.put("GBP", "Pound sterling");
        mCurrenciesNames.put("CNH", "Chinese yuan");
        mCurrenciesNames.put("ZAR", "South African Rand");
        mCurrenciesNames.put("XAG", "Silver Ounce");
        mCurrenciesNames.put("XAU", "Gold Ounce");
    }
}
