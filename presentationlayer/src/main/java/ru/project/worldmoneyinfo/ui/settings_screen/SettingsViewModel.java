package ru.project.worldmoneyinfo.ui.settings_screen;

import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import javax.inject.Inject;

import ru.project.domainlayer.service.ISettingsService;

public class SettingsViewModel {

    @Inject
    List<String> mCurrenciesList;

    @Inject
    ISettingsService mSettingsService;

    private AdapterView.OnItemSelectedListener mSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mSettingsService.setMainCurrency(mCurrenciesList.get(position));
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
