package ru.project.worldmoneyinfo.utils;

import android.databinding.BindingAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.worldmoneyinfo.MainActivity;
import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrenciesAdapter;
import ru.project.worldmoneyinfo.ui.main_fragment_container.ContainerPagerAdapter;

public class CustomBindingAdapter {
    @BindingAdapter({"bind:items", "bind:mainCurrency"})
    public static void setRecyclerViewItems(RecyclerView recyclerView, List<RemoteCurrencyPair> items, String mainCurrency) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        CurrenciesAdapter adapter = new CurrenciesAdapter(items, mainCurrency);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"bind:loadingState", "bind:refreshListener"})
    public static void setRefreshing(SwipeRefreshLayout refreshLayout, boolean isLoading, SwipeRefreshLayout.OnRefreshListener listener) {
        refreshLayout.setOnRefreshListener(listener);
        refreshLayout.post(() -> refreshLayout.setRefreshing(isLoading));
    }

    @BindingAdapter({"bind:fragments", "bind:titles"})
    public static void setViewPager(ViewPager pager, List<Fragment> fragments, List<String> titles) {
        FragmentManager fragmentManager = ((MainActivity)pager.getContext()).getSupportFragmentManager();

        ContainerPagerAdapter adapter = new ContainerPagerAdapter(fragmentManager);
        adapter.addAll(fragments);
        adapter.setTabsTitles(titles);

        pager.setAdapter(adapter);
    }

    @BindingAdapter("bind:currentPager")
    public static void setTabLayout(TabLayout layout, ViewPager pager) {
        layout.setupWithViewPager(pager);
    }

    @BindingAdapter({"bind:currenciesItems", "bind:spinnerListener", "bind:selectedItem"})
    public static void setSettingsSpinner(Spinner spinner, List<String> items, AdapterView.OnItemSelectedListener listener, int selectedItem) {
        spinner.setAdapter(new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_spinner_item, items));
        spinner.setOnItemSelectedListener(listener);

        spinner.setSelection(selectedItem);
    }

    @BindingAdapter({"bind:converterCurrenciesItems"})
    public static void setConverterSpinner(Spinner spinner, List<String> items) {
        spinner.setAdapter(new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_spinner_item, items));
    }
}
