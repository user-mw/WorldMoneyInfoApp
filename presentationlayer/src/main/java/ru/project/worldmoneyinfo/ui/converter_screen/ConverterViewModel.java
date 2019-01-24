package ru.project.worldmoneyinfo.ui.converter_screen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.project.domainlayer.model.RemoteCurrencyData;
import ru.project.domainlayer.service.ICurrenciesService;
import ru.project.domainlayer.utils.ComputingUtil;
import ru.project.worldmoneyinfo.MainApplication;

public class ConverterViewModel extends ViewModel {
    @Inject
    ComputingUtil computingUtil;
    @Inject
    String apiKey;

    private static final String CURRENT_TAG = "ConverterViewModel";
    private static final String DEFAULT_AMOUNT = "0";

    private MutableLiveData<String> resultAmount = new MutableLiveData<>();
    private ICurrencyConverter currencyConverter = (amount, basicCurrency, targetCurrency) ->
        convertData(amount, basicCurrency, targetCurrency);

    private ICurrenciesService currenciesService;

    public ConverterViewModel(ICurrenciesService currenciesService) {
        this.currenciesService = currenciesService;
        MainApplication.getUtilsComponent().inject(this);
    }

    private void convertData(String amount, String basicCurrency, String targetCurrency) {
        if(!basicCurrency.equals(targetCurrency) && amount.length() > 0 && !amount.equals(DEFAULT_AMOUNT)) {
            String pair = targetCurrency + basicCurrency;

            currenciesService.getCurrencies(pair, apiKey)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<RemoteCurrencyData>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d(CURRENT_TAG, "Line 48 - onSubscribe: called");
                        }

                        @Override
                        public void onSuccess(List<RemoteCurrencyData> currencyPairs) {
                            String price = currencyPairs.get(0).getPrice();
                            String result = computingUtil.getTotalAmount(amount, price);

                            resultAmount.postValue(result);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.w(CURRENT_TAG, "Line 61 - onError: called");
                        }
                    });
        } else {
            resultAmount.postValue(amount);
        }
    }

    public MutableLiveData<String> getResultAmount() {
        return resultAmount;
    }

    public ICurrencyConverter getCurrencyConverter() {
        return currencyConverter;
    }

    public interface ICurrencyConverter {
        void convert(String amount, String basicCurrency, String targetCurrency);
    }
}