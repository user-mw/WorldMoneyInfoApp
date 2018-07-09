package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.domainlayer.service.ICurrenciesService;

public class CurrenciesListViewModel {

    @Inject
    ICurrenciesService mService;

    private ObservableArrayList<RemoteCurrencyPair> mCurrencies = new ObservableArrayList<>();
    private ObservableBoolean mIsErrorOccurred = new ObservableBoolean(false);

    @Inject
    public CurrenciesListViewModel() {

    }

    public void loadCurrenciesList(String pairs, String key) {
        mService.getCurrencies(pairs, key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<RemoteCurrencyPair>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<RemoteCurrencyPair> currencyPairs) {
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
}
