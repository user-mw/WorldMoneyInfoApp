package ru.project.worldmoneyinfo.utils;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import ru.project.worldmoneyinfo.MainApplication;
import ru.project.worldmoneyinfo.R;

public class CurrencyUtil {
    private static final String COMMA_SYMBOL = ",";
    private Map<String, String> currenciesNames = new LinkedHashMap<>();
    private String[] symbols;
    private String[] fullNames;
    private LinkedHashMap<String, Drawable> currenciesFlags = new LinkedHashMap<>();

    public CurrencyUtil(MainApplication applicationInstance) {
        symbols = applicationInstance.getResources().getStringArray(R.array.currenciesList);
        fullNames = applicationInstance.getResources().getStringArray(R.array.currenciesFullNames);
        setCurrenciesNames();
        fillFlags(applicationInstance);
    }

    public String getNormalName(String currencyPair, String mainCurrency) {
        String singleSymbol = currencyPair.replace(mainCurrency, "");

        return currenciesNames.get(singleSymbol);
    }

    public String getSecondCurrencyFromPair(String currencyPair, String mainCurrency) {
        return currencyPair.replace(mainCurrency, "");
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

    public Drawable getFlag(String currencySymbol) {
        return currenciesFlags.get(currencySymbol);
    }

    private void setCurrenciesNames() {
        for(int step = 0; step < symbols.length; step++) {
            currenciesNames.put(symbols[step], fullNames[step]);
        }
    }

    private void fillFlags(MainApplication applicationInstance) {
        TypedArray flags = applicationInstance.getResources().obtainTypedArray(R.array.flags);

        for(int step = 0; step < symbols.length; step++) {
            currenciesFlags.put(symbols[step], flags.getDrawable(step));
        }

        flags.recycle();
    }
}