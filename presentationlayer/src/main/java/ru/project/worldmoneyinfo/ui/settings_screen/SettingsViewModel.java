package ru.project.worldmoneyinfo.ui.settings_screen;

import java.util.List;

public class SettingsViewModel {

    private List<String> mCurrenciesList;

    public SettingsViewModel(List<String> currenciesList) {
        mCurrenciesList = currenciesList;
    }

    public List<String> getCurrenciesList() {
        return mCurrenciesList;
    }
}
