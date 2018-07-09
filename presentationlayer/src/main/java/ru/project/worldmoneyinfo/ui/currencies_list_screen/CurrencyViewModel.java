package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import javax.inject.Inject;

import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.domainlayer.utils.ComputingUtil;
import ru.project.domainlayer.utils.CurrencyNamingUtil;
import ru.project.worldmoneyinfo.MainApplication;

public class CurrencyViewModel {

    private String mCurrencySign;
    private String mCurrencyValue;
    private String mCurrencyBid;
    private String mCurrencyAsk;
    private boolean mIsBidMoreThanAsk;
    private String mCurrencyFullName;

    @Inject
    ComputingUtil mComputingUtil;
    @Inject
    CurrencyNamingUtil mNamingUtil;

    public CurrencyViewModel(RemoteCurrencyPair pair) {
        MainApplication.getApplicationComponent().injectUtils(this);
        setCurrencyData(pair);
    }

    private void setCurrencyData(RemoteCurrencyPair pair) {
        mCurrencySign = pair.getSymbol();
        mCurrencyValue = pair.getPrice();
        mCurrencyBid = pair.getBid() + " / ";
        mCurrencyAsk = pair.getAsk();

        mIsBidMoreThanAsk = mComputingUtil.isBidMoreThanAsk(pair.getBid(), pair.getAsk());
        mCurrencyFullName = mNamingUtil.getNormalName(mCurrencySign, "RUB");
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

    public String getCurrencyFullName() {
        return mCurrencyFullName;
    }
}
