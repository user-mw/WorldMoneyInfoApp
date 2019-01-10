package ru.project.worldmoneyinfo.ui.converter_screen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.domainlayer.service.ICurrenciesService;
import ru.project.domainlayer.utils.ComputingUtil;
import ru.project.domainlayer.utils.CurrencyUtil;
import ru.project.worldmoneyinfo.MainApplication;

public class ConverterViewModel extends ViewModel {
    @Inject
    CurrencyUtil mCurrencyUtil;
    @Inject
    ComputingUtil mComputingUtil;
    @Inject
    String mApiKey;

    private static final String CURRENT_TAG = "ConverterViewModel";
    private static final String DEFAULT_AMOUNT = "0";

    private MutableLiveData<String> mFirstCurrencyAmount = new MutableLiveData<>();
    private MutableLiveData<String> mResultAmount = new MutableLiveData<>();
    private ICurrencyConverter mCurrentConverter = (amount, basicCurrency, targetCurrency) ->
        convertData(amount, basicCurrency, targetCurrency);

    private ICurrenciesService currenciesService;

    public ConverterViewModel(ICurrenciesService currenciesService) {
        this.currenciesService = currenciesService;
        MainApplication.getUtilsComponent().inject(this);
    }

    private void convertData(String amount, String basicCurrency, String targetCurrency) {
        if(!basicCurrency.equals(targetCurrency) && amount.length() > 0 && !amount.equals(DEFAULT_AMOUNT)) {
            String pair = basicCurrency + targetCurrency;

            currenciesService.getCurrencies(pair, mApiKey)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<RemoteCurrencyPair>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d(CURRENT_TAG, "Line 52 - onSubscribe: called");
                        }

                        @Override
                        public void onSuccess(List<RemoteCurrencyPair> currencyPairs) {
                            String prise = currencyPairs.get(0).getPrice();
                            String result = mComputingUtil.getTotalAmount(amount, prise);

                            mResultAmount.postValue(result);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.w(CURRENT_TAG, "Line 65 - onError: called");
                        }
                    });
        } else {
            mResultAmount.postValue(amount);
        }
    }

    public MutableLiveData<String> getFirstCurrencyAmount() {
        return mFirstCurrencyAmount;
    }

    public MutableLiveData<String> getResultAmount() {
        return mResultAmount;
    }

    public ICurrencyConverter getCurrentConverter() {
        return mCurrentConverter;
    }

    public List<String> getCurrenciesList() {
        return mCurrencyUtil.getCurrenciesList();
    }

    public interface ICurrencyConverter {
        void convert(String amount, String basicCurrency, String targetCurrency);
    }
}
