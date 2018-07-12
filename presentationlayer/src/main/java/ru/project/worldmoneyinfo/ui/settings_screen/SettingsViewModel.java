package ru.project.worldmoneyinfo.ui.settings_screen;

import java.util.List;

import javax.inject.Inject;

public class SettingsViewModel {

    @Inject
    List<String> mCurrenciesList;

    @Inject
    public SettingsViewModel() {

    }

    public List<String> getCurrenciesList() {
        return mCurrenciesList;
    }
}
