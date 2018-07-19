package ru.project.worldmoneyinfo.ui.converter_screen;

import android.databinding.ObservableField;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.domainlayer.service.ICurrenciesService;
import ru.project.domainlayer.utils.ComputingUtil;
import ru.project.domainlayer.utils.CurrencyUtil;

public class ConverterViewModel {

    private ObservableField<String> mFirstCurrencyAmount = new ObservableField<>();
    private ObservableField<String> mResultAmount = new ObservableField<>();
    private ICurrencyConverter mCurrentConverter = (amount, basicCurrency, targetCurrency) ->
        convertData(amount, basicCurrency, targetCurrency);

    @Inject
    CurrencyUtil mCurrencyUtil;

    @Inject
    ComputingUtil mComputingUtil;

    @Inject
    ICurrenciesService mCurrenciesService;

    @Inject
    String mApiKey;

    @Inject
    public ConverterViewModel() {

    }

    private void convertData(String amount, String basicCurrency, String targetCurrency) {

        if(!basicCurrency.equals(targetCurrency)) {
            String pair = basicCurrency + targetCurrency;

            mCurrenciesService.getCurrencies(pair, mApiKey)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<RemoteCurrencyPair>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(List<RemoteCurrencyPair> currencyPairs) {
                            String prise = currencyPairs.get(0).getPrice();
                            String result = mComputingUtil.getTotalAmount(amount, prise);

                            mResultAmount.set(result);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        } else {
            mResultAmount.set(amount);
        }
    }

    public ObservableField<String> getFirstCurrencyAmount() {
        return mFirstCurrencyAmount;
    }

    public ObservableField<String> getResultAmount() {
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
