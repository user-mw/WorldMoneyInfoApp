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
    public void setMainCurrencyPosition(int currencyPosition) {
        mPreferences.edit().putInt(ISettingsRepository.MAIN_CURRENCY_POSITION, currencyPosition).apply();
    }

    @Override
    public int getMainCurrencyPosition() {
        return mPreferences.getInt(ISettingsRepository.MAIN_CURRENCY_POSITION, ISettingsRepository.DEFAULT_POSITION);
    }

    @Override
    public void setMainCurrency(String mainCurrencyValue) {
        mPreferences.edit().putString(ISettingsRepository.MAIN_CURRENCY, mainCurrencyValue).apply();
    }

    @Override
    public String getMainCurrency() {
        return mPreferences.getString(ISettingsRepository.MAIN_CURRENCY, ISettingsRepository.DEFAULT_CURRENCY);
    }
}
