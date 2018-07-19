package ru.project.worldmoneyinfo.ui.converter_screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ru.project.worldmoneyinfo.MainApplication;
import ru.project.worldmoneyinfo.databinding.ConverterBinding;
import ru.project.worldmoneyinfo.dependency.AppDataModule;
import ru.project.worldmoneyinfo.dependency.RepositoryModule;
import ru.project.worldmoneyinfo.dependency.ServiceModule;
import ru.project.worldmoneyinfo.ui.BaseFragment;

public class ConverterFragment extends BaseFragment {

    @Inject
    ConverterViewModel mViewModel;

    public static ConverterFragment newInstance() {
        Bundle args = new Bundle();
        
        ConverterFragment fragment = new ConverterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void prepareViewModel() {
        MainApplication.getApplicationComponent()
                .plusScreenComponent(
                        new RepositoryModule(),
                        new ServiceModule(),
                        new AppDataModule("")
                ).inject(this);
    }

    @Override
    protected View retrieveView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        ConverterBinding binding = ConverterBinding.inflate(inflater, container, false);
        binding.setCurrentViewModel(mViewModel);
        return binding.getRoot();
    }

    @Override
    protected void loadData() {

    }
}
