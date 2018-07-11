package ru.project.worldmoneyinfo.utils;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrenciesAdapter;

public class CustomBindingAdapter {
    @BindingAdapter({"bind:items"})
    public static void setRecyclerViewItems(RecyclerView recyclerView, List<RemoteCurrencyPair> items) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        CurrenciesAdapter adapter = new CurrenciesAdapter(items);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"bind:loadingState", "bind:refreshListener"})
    public static void setRefreshing(SwipeRefreshLayout refreshLayout, boolean isLoading, SwipeRefreshLayout.OnRefreshListener listener) {
        refreshLayout.setOnRefreshListener(listener);
        refreshLayout.post(() -> refreshLayout.setRefreshing(isLoading));
    }
}
