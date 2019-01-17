package ru.project.domainlayer.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class CurrencyUtil {
    private Map<String, String> mCurrenciesNames = new LinkedHashMap<>();
    private List<String> mCurrenciesList = new ArrayList<>();

    @Inject
    public CurrencyUtil() {
        setCurrenciesNames();
        setSettingsCurrencies();
    }

    public String getNormalName(String currencyPair, String mainCurrency) {
        String singleSymbol = currencyPair.replace(mainCurrency, "");

        return mCurrenciesNames.get(singleSymbol);
    }

    public String getSecondCurrencyFromPair(String currencyPair, String mainCurrency) {
        return currencyPair.replace(mainCurrency, "");
    }

    private void setCurrenciesNames() {
        mCurrenciesNames.put("RUB", "Russian Ruble");
        mCurrenciesNames.put("EUR", "Euro");
        mCurrenciesNames.put("USD", "United States Dollar");
        mCurrenciesNames.put("PLN", "Polish Zloty");
        mCurrenciesNames.put("GBP", "Pound sterling");
        mCurrenciesNames.put("CNH", "Chinese yuan");
        mCurrenciesNames.put("JPY", "Japanese yen");
        mCurrenciesNames.put("NOK", "Norwegian krone");
        mCurrenciesNames.put("TRY", "Turkish lira");
        mCurrenciesNames.put("ZAR", "South African Rand");
        mCurrenciesNames.put("XAG", "Silver Ounce");
        mCurrenciesNames.put("XAU", "Gold Ounce");
    }

    private void setSettingsCurrencies() {
        mCurrenciesList.add("RUB");
        mCurrenciesList.add("EUR");
        mCurrenciesList.add("USD");
        mCurrenciesList.add("PLN");
        mCurrenciesList.add("GBP");
        mCurrenciesList.add("CNH");
        mCurrenciesList.add("JPY");
        mCurrenciesList.add("NOK");
        mCurrenciesList.add("TRY");
        mCurrenciesList.add("ZAR");

    }

    public String getRatesValue(String mainCurrency) {
        String pair = "";

        for(int step = 0; step < mCurrenciesNames.keySet().size(); step++) {
            String key = mCurrenciesNames.keySet().toArray()[step].toString();

            if(!key.equals(mainCurrency)) {
                pair += key + mainCurrency + ",";
            }

        }

        return pair;
    }

    public List<String> getCurrenciesList() {
        return mCurrenciesList;
    }
}
