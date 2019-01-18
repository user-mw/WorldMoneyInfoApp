package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.project.domainlayer.model.RemoteCurrencyData;
import ru.project.domainlayer.service.ICurrenciesService;
import ru.project.domainlayer.service.ISettingsService;
import ru.project.worldmoneyinfo.utils.CurrencyUtil;
import ru.project.worldmoneyinfo.MainApplication;

public class CurrenciesListViewModel extends ViewModel {
    @Inject
    String apiKey;
    @Inject
    CurrencyUtil currencyUtil;

    private static final String CURRENT_TAG = "CurrenciesListViewModel";

    private ICurrenciesService currenciesService;
    private ISettingsService settingsService;

    private MutableLiveData<List<RemoteCurrencyData>> currencies = new MutableLiveData<>();
    private MutableLiveData<Boolean> isErrorOccurred = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private SwipeRefreshLayout.OnRefreshListener refreshListener = () -> loadCurrenciesList();
    private Disposable intervalDisposable;

    public CurrenciesListViewModel(ICurrenciesService currenciesService, ISettingsService settingsService) {
        this.currenciesService = currenciesService;
        this.settingsService = settingsService;
        MainApplication.getUtilsComponent().inject(this);
        loadCurrenciesList();
    }

    public void startUpdate() {
        if(settingsService.isAutoUpdateEnabled()) {
            int intervalValue = settingsService.getAutoUpdatePeriodValue();

            intervalDisposable = Observable.interval(intervalValue, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> loadCurrenciesList());
        }

    }

    public void loadCurrenciesList() {
        String pairs = currencyUtil.getRatesValue(settingsService.getMainCurrency());

        currenciesService.getCurrencies(pairs, apiKey)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> isLoading.postValue(true))
                .doFinally(() -> isLoading.postValue(false))
                .subscribe(new SingleObserver<List<RemoteCurrencyData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(CURRENT_TAG, "Line 70 - onSubscribe: called");
                    }

                    @Override
                    public void onSuccess(List<RemoteCurrencyData> currencyPairs) {
                        isErrorOccurred.postValue(false);
                        currencies.postValue(currencyPairs);
                    }

                    @Override
                    public void onError(Throwable e) {
                        isErrorOccurred.postValue(true);
                    }
                });
    }

    public MutableLiveData<List<RemoteCurrencyData>> getCurrencies() {
        return currencies;
    }

    public MutableLiveData<Boolean> getIsErrorOccurred() {
        return isErrorOccurred;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public SwipeRefreshLayout.OnRefreshListener getRefreshListener() {
        return refreshListener;
    }

    public String getMainCurrency() {
        return settingsService.getMainCurrency();
    }

    public void onDetach() {
        if(intervalDisposable != null) {
            intervalDisposable.dispose();
            intervalDisposable = null;
        }
    }
}