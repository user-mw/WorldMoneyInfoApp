package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.domainlayer.service.ICurrenciesService;
import ru.project.domainlayer.service.ISettingsService;
import ru.project.domainlayer.utils.CurrencyUtil;
import ru.project.worldmoneyinfo.MainApplication;

public class CurrenciesListViewModel extends ViewModel {

    private static final String CURRENT_TAG = "CurrenciesListViewModel";

    @Inject
    String apiKey;
    @Inject
    CurrencyUtil currencyUtil;

    private ICurrenciesService currenciesService;
    private ISettingsService settingsService;

    private MutableLiveData<List<RemoteCurrencyPair>> currencies = new MutableLiveData<>();
    private MutableLiveData<Boolean> isErrorOccurred = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private SwipeRefreshLayout.OnRefreshListener refreshListener = () -> loadCurrenciesList();

    public CurrenciesListViewModel(ICurrenciesService currenciesService, ISettingsService settingsService) {
        this.currenciesService = currenciesService;
        this.settingsService = settingsService;
        MainApplication.getUtilsComponent().inject(this);
    }

    public void loadCurrenciesList() {
        String pairs = currencyUtil.getRatesValue(settingsService.getMainCurrency());

        currenciesService.getCurrencies(pairs, apiKey)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> isLoading.postValue(true))
                .doFinally(() -> isLoading.postValue(false))
                .subscribe(new SingleObserver<List<RemoteCurrencyPair>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(CURRENT_TAG, "Line 54 - onSubscribe: called");
                    }

                    @Override
                    public void onSuccess(List<RemoteCurrencyPair> currencyPairs) {
                        isErrorOccurred.postValue(false);
                        currencies.postValue(currencyPairs);
                    }

                    @Override
                    public void onError(Throwable e) {
                        isErrorOccurred.postValue(true);
                    }
                });
    }

    public MutableLiveData<List<RemoteCurrencyPair>> getCurrencies() {
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
}
