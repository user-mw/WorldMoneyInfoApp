package ru.project.worldmoneyinfo.ui.settings_screen;

import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import javax.inject.Inject;

import ru.project.domainlayer.service.ISettingsService;
import ru.project.domainlayer.utils.CurrencyUtil;

public class SettingsViewModel {
    @Inject
    CurrencyUtil mCurrencyUtil;

    @Inject
    ISettingsService mSettingsService;

    private AdapterView.OnItemSelectedListener mSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mSettingsService.setMainCurrencyPosition(position);
            mSettingsService.setMainCurrency(mCurrencyUtil.getSettingsCurrenciesList().get(position));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Inject
    public SettingsViewModel() {

    }

    public List<String> getCurrenciesList() {
        return mCurrencyUtil.getSettingsCurrenciesList();
    }

    public AdapterView.OnItemSelectedListener getSpinnerListener() {
        return mSpinnerListener;
    }

    public int getSelectedItem() {
        return mSettingsService.getMainCurrencyPosition();
    }
}
