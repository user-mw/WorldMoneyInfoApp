package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.domainlayer.utils.ComputingUtil;
import ru.project.domainlayer.utils.CurrencyUtil;
import ru.project.worldmoneyinfo.MainApplication;

import ru.project.worldmoneyinfo.R;

public class CurrencyViewHolder extends RecyclerView.ViewHolder {
    @Inject
    ComputingUtil computingUtil;
    @Inject
    CurrencyUtil currencyUtil;

    private View mainView;
    private TextView currencyFullName;
    private TextView currencySymbol;
    private TextView currencyValue;
    private TextView currencyBid;
    private TextView currencyAsk;

    public CurrencyViewHolder(View view) {
        super(view);
        mainView = view;
        MainApplication.getUtilsComponent().inject(this);

        initViewElements(view);
    }

    private void initViewElements(View view) {
        currencyFullName = view.findViewById(R.id.currency_full_name);
        currencySymbol = view.findViewById(R.id.currency_symbol);
        currencyValue = view.findViewById(R.id.currency_value);
        currencyBid = view.findViewById(R.id.currency_bid);
        currencyAsk = view.findViewById(R.id.currency_ask);
    }

    public void bindData(RemoteCurrencyPair currencyPair, String mainCurrency, CurrenciesAdapter.IOnElementClick onElementClick) {
        currencyFullName.setText(currencyUtil.getNormalName(currencyPair.getSymbol(), mainCurrency));
        currencySymbol.setText(currencyPair.getSymbol());
        currencyValue.setText(currencyPair.getPrice());

        StringBuilder currencyBidText = new StringBuilder(currencyPair.getBid()).append(" / ");

        currencyBid.setText(currencyBidText);
        currencyAsk.setText(currencyPair.getAsk());

        boolean isBidMoreThanAsk = computingUtil.isBidMoreThanAsk(currencyPair.getBid(), currencyPair.getAsk());

        setElementsTextColor(isBidMoreThanAsk);
        mainView.setOnClickListener(view -> onElementClick.click(currencyPair.getSymbol()));
    }

    private void setElementsTextColor(boolean isBidMoreThanAsk) {
        Context context = mainView.getContext();

        int bidColor;
        int askColor;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(isBidMoreThanAsk) {
                bidColor = ContextCompat.getColor(context, R.color.colorAccent);
                askColor = ContextCompat.getColor(context, R.color.textColorPrimary);
            } else {
                bidColor = ContextCompat.getColor(context, R.color.textColorPrimary);
                askColor = ContextCompat.getColor(context, R.color.textColorRed);
            }
        } else {
            if(isBidMoreThanAsk) {
                bidColor = context.getResources().getColor(R.color.colorAccent);
                askColor = context.getResources().getColor(R.color.textColorPrimary);
            } else {
                bidColor = context.getResources().getColor(R.color.textColorPrimary);
                askColor = context.getResources().getColor(R.color.textColorRed);
            }
        }

        currencyBid.setTextColor(bidColor);
        currencyAsk.setTextColor(askColor);
    }
}
