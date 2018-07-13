package ru.project.worldmoneyinfo.ui.currencies_list_screen;

import android.support.v7.widget.RecyclerView;

import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.worldmoneyinfo.databinding.CurrencyBinding;

public class CurrencyViewHolder extends RecyclerView.ViewHolder {
    private CurrencyBinding mBinding;

    public CurrencyViewHolder(CurrencyBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void bindData(RemoteCurrencyPair currencyPair, String mainCurrency) {
        mBinding.setCurrentViewModel(new CurrencyViewModel(currencyPair, mainCurrency));
    }
}
