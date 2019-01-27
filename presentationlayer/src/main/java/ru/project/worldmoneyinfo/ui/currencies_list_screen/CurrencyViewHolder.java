package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import ru.project.domainlayer.model.RemoteCurrencyData;
import ru.project.domainlayer.utils.ComputingUtil;
import ru.project.worldmoneyinfo.MainApplication;
import ru.project.worldmoneyinfo.R;
import ru.project.worldmoneyinfo.utils.CurrencyUtil;

public class CurrencyViewHolder extends RecyclerView.ViewHolder {
    @Inject
    ComputingUtil computingUtil;
    @Inject
    CurrencyUtil currencyUtil;

    private View mainView;
    private TextView currencyFullName;
    private ImageView currencyFlag;
    private TextView currencySymbol;
    private TextView currencyValue;
    private String priceFormat;

    public CurrencyViewHolder(View view) {
        super(view);
        mainView = view;
        MainApplication.getUtilsComponent().inject(this);

        priceFormat = view.getContext().getString(R.string.price_value);

        initViewElements(view);
    }

    private void initViewElements(View view) {
        currencyFullName = view.findViewById(R.id.currency_full_name);
        currencyFlag = view.findViewById(R.id.currency_flag);
        currencySymbol = view.findViewById(R.id.currency_symbol);
        currencyValue = view.findViewById(R.id.currency_value);
    }

    public void bindData(RemoteCurrencyData currencyData, String mainCurrency, CurrenciesAdapter.IOnElementClick onElementClick) {
        String symbol = currencyUtil.getSecondCurrencyFromPair(currencyData.getSymbol(), mainCurrency);

        String fullName = currencyUtil.getNormalName(currencyData.getSymbol(), mainCurrency);

        currencyFullName.setText(fullName);
        currencyFlag.setImageDrawable(currencyUtil.getFlag(symbol));
        currencySymbol.setText(symbol);

        String currencyValueText = String.format(priceFormat, currencyData.getPrice(), mainCurrency);
        currencyValue.setText(currencyValueText);
        mainView.setOnClickListener(view -> onElementClick.click(currencyData.getSymbol()));
    }
}