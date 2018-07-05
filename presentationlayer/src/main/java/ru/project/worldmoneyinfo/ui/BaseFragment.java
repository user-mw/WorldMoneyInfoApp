package ru.project.worldmoneyinfo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.project.worldmoneyinfo.MainActivity;

public abstract class BaseFragment extends Fragment {
    protected abstract void prepareViewModel();
    protected abstract View retrieveView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);
    protected abstract void loadData();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        prepareViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return retrieveView(inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    protected final void changeFragment(Fragment newFragment) {
        if(getActivity() != null) {
            ((MainActivity)getActivity()).changeFragment(newFragment);
        }
    }
}
