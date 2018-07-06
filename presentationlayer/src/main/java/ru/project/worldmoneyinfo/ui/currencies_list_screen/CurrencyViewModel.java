package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import ru.project.domainlayer.model.RemoteCurrencyPair;

public class CurrencyViewModel {

    private String mCurrencySign;
    private String mCurrencyValue;
    private String mCurrencyBid;
    private String mCurrencyAsk;

    public CurrencyViewModel(RemoteCurrencyPair pair) {
        mCurrencySign = pair.getSymbol();
        mCurrencyValue = String.valueOf(pair.getPrice());
        mCurrencyBid = pair.getBid() + " / ";
        mCurrencyAsk = String.valueOf(pair.getAsk());
    }

    public String getCurrencySign() {
        return mCurrencySign;
    }

    public String getCurrencyValue() {
        return mCurrencyValue;
    }

    public String getCurrencyBid() {
        return mCurrencyBid;
    }

    public String getCurrencyAsk() {
        return mCurrencyAsk;
    }
}
