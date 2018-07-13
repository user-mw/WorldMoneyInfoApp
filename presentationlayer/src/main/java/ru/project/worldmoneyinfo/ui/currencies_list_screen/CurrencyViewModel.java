package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import javax.inject.Inject;

import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.domainlayer.utils.ComputingUtil;
import ru.project.domainlayer.utils.CurrencyNamingUtil;
import ru.project.worldmoneyinfo.dependency.DaggerIUtilsComponent;

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

    public CurrencyViewModel(RemoteCurrencyPair pair, String mainCurrency) {
        DaggerIUtilsComponent.builder().build().inject(this);
        setCurrencyData(pair, mainCurrency);
    }

    private void setCurrencyData(RemoteCurrencyPair pair, String mainCurrency) {
        mCurrencySign = pair.getSymbol();
        mCurrencyValue = pair.getPrice();
        mCurrencyBid = pair.getBid() + " / ";
        mCurrencyAsk = pair.getAsk();

        mIsBidMoreThanAsk = mComputingUtil.isBidMoreThanAsk(pair.getBid(), pair.getAsk());
        mCurrencyFullName = mNamingUtil.getNormalName(mCurrencySign, mainCurrency);
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
