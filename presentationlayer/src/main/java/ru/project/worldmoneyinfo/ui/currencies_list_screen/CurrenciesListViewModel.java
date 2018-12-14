package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
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

public class CurrenciesListViewModel extends ViewModel {

    @Inject
    ICurrenciesService mService;

    @Inject
    String mApiKey;

    @Inject
    ISettingsService mSettingsService;

    @Inject
    CurrencyUtil mCurrencyUtil;

    private MutableLiveData<List<RemoteCurrencyPair>> mCurrencies = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsErrorOccurred = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = () -> loadCurrenciesList();

    @Inject
    public CurrenciesListViewModel() {

    }

    public void loadCurrenciesList() {
        String pairs = mCurrencyUtil.getRatesValue(mSettingsService.getMainCurrency());

        mService.getCurrencies(pairs, mApiKey)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                .doFinally(() -> mIsLoading.postValue(false))
                .subscribe(new SingleObserver<List<RemoteCurrencyPair>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<RemoteCurrencyPair> currencyPairs) {
                        mIsErrorOccurred.postValue(false);
                        mCurrencies.postValue(currencyPairs);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mIsErrorOccurred.postValue(true);
                    }
                });
    }

    public MutableLiveData<List<RemoteCurrencyPair>> getCurrencies() {
        return mCurrencies;
    }

    public MutableLiveData<Boolean> getIsErrorOccurred() {
        return mIsErrorOccurred;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public SwipeRefreshLayout.OnRefreshListener getRefreshListener() {
        return mRefreshListener;
    }

    public String getMainCurrency() {
        return mSettingsService.getMainCurrency();
    }
}
