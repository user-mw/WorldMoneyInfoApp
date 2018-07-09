package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.worldmoneyinfo.utils.ComputingUtil;

public class CurrencyViewModel {

    private String mCurrencySign;
    private String mCurrencyValue;
    private String mCurrencyBid;
    private String mCurrencyAsk;
    private boolean mIsBidMoreThanAsk;

    public CurrencyViewModel(RemoteCurrencyPair pair) {
        mCurrencySign = pair.getSymbol();
        mCurrencyValue = pair.getPrice();
        mCurrencyBid = pair.getBid() + " / ";
        mCurrencyAsk = pair.getAsk();

        mIsBidMoreThanAsk = new ComputingUtil().isBidMoreThanAsk(pair.getBid(), pair.getAsk());
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

    public boolean isBidMoreThanAsk() {
        return mIsBidMoreThanAsk;
    }
}
