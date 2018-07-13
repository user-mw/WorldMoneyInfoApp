package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.worldmoneyinfo.databinding.CurrencyBinding;

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
        CurrencyBinding binding = CurrencyBinding.inflate(inflater, parent, false);
        return new CurrencyViewHolder(binding);
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
