package ru.project.worldmoneyinfo.ui.main_fragment_container;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.project.worldmoneyinfo.MainActivity;
import ru.project.worldmoneyinfo.R;
import ru.project.worldmoneyinfo.ui.converter_screen.ConverterFragment;
import ru.project.worldmoneyinfo.ui.currencies_list_screen.CurrenciesListFragment;
import ru.project.worldmoneyinfo.ui.settings_screen.SettingsFragment;

public class ContainerFragment extends Fragment {
    private TabLayout tabs;
    private ViewPager mainContainer;

    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitles = new ArrayList<>();

    public static ContainerFragment newInstance() {
        Bundle args = new Bundle();
        
        ContainerFragment fragment = new ContainerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mFragmentList.add(CurrenciesListFragment.newInstance());
        mFragmentList.add(ConverterFragment.newInstance());

        if(getActivity() != null) {
            mTitles.add(getActivity().getString(R.string.rates_title));
            mTitles.add(getActivity().getString(R.string.converter_title));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.container_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tabs = view.findViewById(R.id.container_tabs);
        mainContainer = view.findViewById(R.id.main_container);

        if(configureViewPager()) {
            tabs.setupWithViewPager(mainContainer);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.settings_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: {
                if(getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).changeFragment(SettingsFragment.newInstance());
                }
                return true;
            }
            default: break;
        }

        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private boolean configureViewPager() {
        if(getActivity() != null) {
            ContainerPagerAdapter adapter = new ContainerPagerAdapter(getChildFragmentManager());
            adapter.addAll(mFragmentList);
            adapter.setTabsTitles(mTitles);

            mainContainer.setAdapter(adapter);
            return true;
        }

        return false;
    }
}