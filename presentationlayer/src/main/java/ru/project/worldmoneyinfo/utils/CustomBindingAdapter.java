package ru.project.worldmoneyinfo.utils;

import android.databinding.BindingAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ru.project.domainlayer.model.RemoteCurrencyPair;
import ru.project.worldmoneyinfo.MainActivity;
import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrenciesAdapter;
import ru.project.worldmoneyinfo.ui.main_fragment_container.ContainerFragmentPagerAdapter;

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

    @BindingAdapter("bind:fragments")
    public static void setViewPager(ViewPager pager, List<Fragment> fragments) {
        FragmentManager fragmentManager = ((MainActivity)pager.getContext()).getSupportFragmentManager();

        ContainerFragmentPagerAdapter adapter = new ContainerFragmentPagerAdapter(fragmentManager);
        adapter.addAll(fragments);

        pager.setAdapter(adapter);
    }
}
