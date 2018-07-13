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
    public void setMainCurrency(String currencyValue) {
        mPreferences.edit().putString(ISettingsRepository.MAIN_CURRENCY, currencyValue).apply();
    }

    @Override
    public String getMainCurrencyValue() {
        return mPreferences.getString(ISettingsRepository.MAIN_CURRENCY, ISettingsRepository.DEFAULT_CURRENCY);
    }
}
