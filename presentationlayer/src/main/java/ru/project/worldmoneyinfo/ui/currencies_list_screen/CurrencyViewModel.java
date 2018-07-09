package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.domainlayer.utils.ComputingUtil;
import ru.project.domainlayer.utils.CurrencyNamingUtil;

public class CurrencyViewModel {

    private String mCurrencySign;
    private String mCurrencyValue;
    private String mCurrencyBid;
    private String mCurrencyAsk;
    private boolean mIsBidMoreThanAsk;
    private String mCurrencyFullName;

    public CurrencyViewModel(RemoteCurrencyPair pair) {
        mCurrencySign = pair.getSymbol();
        mCurrencyValue = pair.getPrice();
        mCurrencyBid = pair.getBid() + " / ";
        mCurrencyAsk = pair.getAsk();

        mIsBidMoreThanAsk = new ComputingUtil().isBidMoreThanAsk(pair.getBid(), pair.getAsk());
        mCurrencyFullName = new CurrencyNamingUtil().getNormalName(mCurrencySign, "RUB");
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
