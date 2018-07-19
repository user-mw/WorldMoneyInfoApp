package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.domainlayer.service.ICurrenciesService;
import ru.project.domainlayer.service.ISettingsService;
import ru.project.domainlayer.utils.CurrencyUtil;
import ru.project.worldmoneyinfo.dependency.AppDataModule;

public class CurrenciesListViewModel {

    @Inject
    ICurrenciesService mService;

    @Inject
    String mApiKey;

    @Inject
    ISettingsService mSettingsService;

    @Inject
    CurrencyUtil mCurrencyUtil;

    private ObservableArrayList<RemoteCurrencyPair> mCurrencies = new ObservableArrayList<>();
    private ObservableBoolean mIsErrorOccurred = new ObservableBoolean(false);
    private ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = () -> loadCurrenciesList();

    @Inject
    public CurrenciesListViewModel() {

    }

    public void loadCurrenciesList() {
        String pairs = mCurrencyUtil.getRatesValue(mSettingsService.getMainCurrency());

        mService.getCurrencies(pairs, mApiKey)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mIsLoading.set(true))
                .doFinally(() -> mIsLoading.set(false))
                .subscribe(new SingleObserver<List<RemoteCurrencyPair>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<RemoteCurrencyPair> currencyPairs) {
                        mIsErrorOccurred.set(false);

                        if(mCurrencies.size() > 0) {
                            mCurrencies.clear();
                        }

                        mCurrencies.addAll(currencyPairs);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mIsErrorOccurred.set(true);
                    }
                });
    }

    public ObservableArrayList<RemoteCurrencyPair> getCurrencies() {
        return mCurrencies;
    }

    public ObservableBoolean getIsErrorOccurred() {
        return mIsErrorOccurred;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public SwipeRefreshLayout.OnRefreshListener getRefreshListener() {
        return mRefreshListener;
    }

    public String getMainCurrency() {
        return mSettingsService.getMainCurrency();
    }
}
