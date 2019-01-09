package ru.project.worldmoneyinfo.utils;

import android.databinding.BindingAdapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class CustomBindingAdapter {
    @BindingAdapter({"bind:currenciesItems", "bind:spinnerListener", "bind:selectedItem"})
    public static void setSettingsSpinner(Spinner spinner, List<String> items, AdapterView.OnItemSelectedListener listener, int selectedItem) {
        spinner.setAdapter(new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_spinner_item, items));
        spinner.setOnItemSelectedListener(listener);

        spinner.setSelection(selectedItem);
    }
}
