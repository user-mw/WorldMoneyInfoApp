package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import ru.project.domainlayer.model.RemoteCurrencyPair;

public class CurrencyViewModel {

    private String mCurrencySign;
    private String mCurrencyValue;

    public CurrencyViewModel(RemoteCurrencyPair pair) {
        mCurrencySign = pair.getSymbol();
        mCurrencyValue = String.valueOf(pair.getPrice());
    }

    public String getCurrencySign() {
        return mCurrencySign;
    }

    public String getCurrencyValue() {
        return mCurrencyValue;
    }
}
