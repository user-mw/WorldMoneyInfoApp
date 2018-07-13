package ru.project.worldmoneyinfo.ui.settings_screen;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.List;

import javax.inject.Inject;

public class SettingsViewModel {

    @Inject
    List<String> mCurrenciesList;

    private AdapterView.OnItemSelectedListener mSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Inject
    public SettingsViewModel() {

    }

    public List<String> getCurrenciesList() {
        return mCurrenciesList;
    }

    public AdapterView.OnItemSelectedListener getSpinnerListener() {
        return mSpinnerListener;
    }
}
