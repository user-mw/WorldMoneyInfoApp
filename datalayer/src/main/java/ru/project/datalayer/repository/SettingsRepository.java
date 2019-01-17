package ru.project.datalayer.repository;

import android.content.SharedPreferences;
import android.util.Log;

import javax.inject.Inject;

import ru.project.domainlayer.repository.ISettingsRepository;

public class SettingsRepository implements ISettingsRepository {
    @Inject
    SharedPreferences preferences;
    private static final String CURRENT_TAG = "SettingsRepository";

    @Inject
    public SettingsRepository() {
        Log.d(CURRENT_TAG, "Line 17 - SettingsRepository constructor: called");
    }

    @Override
    public String getMainCurrency() {
        return preferences.getString(ISettingsRepository.MAIN_CURRENCY, ISettingsRepository.DEFAULT_CURRENCY);
    }

    @Override
    public boolean isAutoUpdateEnabled() {
        return preferences.getBoolean(ISettingsRepository.AUTO_UPDATE, ISettingsRepository.DEFAULT_AUTO_UPDATE_VALUE);
    }

    @Override
    public int getAutoUpdatePeriodValue() {
        String periodData = preferences.getString(ISettingsRepository.AUTO_UPDATE_PERIOD, ISettingsRepository.DEFAULT_AUTO_UPDATE_PERIOD_VALUE);
        return Integer.parseInt(periodData);
    }
}