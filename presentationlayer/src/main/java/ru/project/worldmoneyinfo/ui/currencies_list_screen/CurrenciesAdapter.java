package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncListDiffer;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.worldmoneyinfo.R;

public class CurrenciesAdapter extends RecyclerView.Adapter<CurrencyViewHolder> {
    private IOnElementClick onElementClick;
    private String mainCurrency;
    private final AsyncListDiffer<RemoteCurrencyPair> differ = new AsyncListDiffer<>(this, DIFF_CALLBACK);

    private final static DiffUtil.ItemCallback<RemoteCurrencyPair> DIFF_CALLBACK = new DiffUtil.ItemCallback<RemoteCurrencyPair>() {
        @Override
        public boolean areItemsTheSame(@NonNull RemoteCurrencyPair oldCurrencyItem, @NonNull RemoteCurrencyPair newCurrencyItem) {
            return oldCurrencyItem.getSymbol().equals(newCurrencyItem.getSymbol());
        }

        @Override
        public boolean areContentsTheSame(@NonNull RemoteCurrencyPair oldCurrencyItem, @NonNull RemoteCurrencyPair newCurrencyItem) {
            return oldCurrencyItem.getPrice().equals(newCurrencyItem.getPrice());
        }
    };

    public CurrenciesAdapter(String mainCurrency, IOnElementClick onElementClick) {
        this.mainCurrency = mainCurrency;
        this.onElementClick = onElementClick;
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
        holder.bindData(differ.getCurrentList().get(position), mainCurrency, onElementClick);
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    public void addNewData(List<RemoteCurrencyPair> newCurrencyPairs) {
        differ.submitList(newCurrencyPairs);
    }

    public interface IOnElementClick {
        void click(String currencyPair);
    }
}
