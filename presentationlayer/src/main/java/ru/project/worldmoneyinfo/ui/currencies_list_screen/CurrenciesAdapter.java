package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.worldmoneyinfo.R;

public class CurrenciesAdapter extends RecyclerView.Adapter<CurrencyViewHolder> {

    private List<RemoteCurrencyPair> mCurrencyPairs;
    private String mMainCurrency;

    public CurrenciesAdapter(List<RemoteCurrencyPair> currencyPairs, String mainCurrency) {
        mCurrencyPairs = currencyPairs;
        mMainCurrency = mainCurrency;
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View holderView = inflater.inflate(R.layout.currency_list_item, parent, false);
        return new CurrencyViewHolder(holderView);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        holder.bindData(mCurrencyPairs.get(position), mMainCurrency);
    }

    @Override
    public int getItemCount() {
        if(mCurrencyPairs == null) {
            return 0;
        }

        return mCurrencyPairs.size();
    }
}
