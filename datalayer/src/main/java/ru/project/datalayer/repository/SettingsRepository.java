package ru.project.datalayer.repository;

import android.content.SharedPreferences;

import javax.inject.Inject;

import ru.project.domainlayer.repository.ISettingsRepository;

public class SettingsRepository implements ISettingsRepository {

    @Inject
    SharedPreferences mPreferences;

    @Inject
    public SettingsRepository() {

    }

    @Override
    public String getMainCurrency() {
        return mPreferences.getString(ISettingsRepository.MAIN_CURRENCY, ISettingsRepository.DEFAULT_CURRENCY);
    }

    @Override
    public boolean isAutoUpdateEnabled() {
        return mPreferences.getBoolean(ISettingsRepository.AUTO_UPDATE, ISettingsRepository.DEFAULT_AUTO_UPDATE_VALUE);
    }

    @Override
    public int getAutoUpdatePeriodValue() {
        return mPreferences.getInt(ISettingsRepository.AUTO_UPDATE_PERIOD, ISettingsRepository.DEFAULT_AUTO_UPDATE_PERIOD_VALUE);
    }
}
