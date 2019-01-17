package ru.project.domainlayer.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CurrencyUtil {
    public static final String COMMA_SYMBOL = ",";
    private Map<String, String> currenciesNames = new LinkedHashMap<>();
    private List<String> currenciesList = new ArrayList<>();

//    @Inject
    public CurrencyUtil() {
        setCurrenciesNames();
        setSettingsCurrencies();
    }

    public String getNormalName(String currencyPair, String mainCurrency) {
        String singleSymbol = currencyPair.replace(mainCurrency, "");

        return currenciesNames.get(singleSymbol);
    }

    public String getSecondCurrencyFromPair(String currencyPair, String mainCurrency) {
        return currencyPair.replace(mainCurrency, "");
    }

    private void setCurrenciesNames() {
        currenciesNames.put("RUB", "Russian Ruble");
        currenciesNames.put("EUR", "Euro");
        currenciesNames.put("USD", "United States Dollar");
        currenciesNames.put("PLN", "Polish Zloty");
        currenciesNames.put("GBP", "Pound sterling");
        currenciesNames.put("CNH", "Chinese yuan");
        currenciesNames.put("JPY", "Japanese yen");
        currenciesNames.put("NOK", "Norwegian krone");
        currenciesNames.put("TRY", "Turkish lira");
        currenciesNames.put("ZAR", "South African Rand");
        currenciesNames.put("XAG", "Silver Ounce");
        currenciesNames.put("XAU", "Gold Ounce");
    }

    private void setSettingsCurrencies() {
        currenciesList.add("RUB");
        currenciesList.add("EUR");
        currenciesList.add("USD");
        currenciesList.add("PLN");
        currenciesList.add("GBP");
        currenciesList.add("CNH");
        currenciesList.add("JPY");
        currenciesList.add("NOK");
        currenciesList.add("TRY");
        currenciesList.add("ZAR");
    }

    public String getRatesValue(String mainCurrency) {
        StringBuilder result = new StringBuilder();

        for(int step = 0; step < currenciesNames.keySet().size(); step++) {
            String key = currenciesNames.keySet().toArray()[step].toString();

            if(!key.equals(mainCurrency)) {
                result.append(key).append(mainCurrency).append(COMMA_SYMBOL);
            }

        }

        return result.toString();
    }

    public List<String> getCurrenciesList() {
        return currenciesList;
    }
}