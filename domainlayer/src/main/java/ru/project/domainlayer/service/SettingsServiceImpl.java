package ru.project.domainlayer.service;

import javax.inject.Inject;

import ru.project.domainlayer.repository.ISettingsRepository;

public class SettingsServiceImpl implements ISettingsService {

    @Inject
    ISettingsRepository mSettingsRepository;

    @Inject
    public SettingsServiceImpl() {

    }

    @Override
    public void setMainCurrencyPosition(int currencyPosition) {
        mSettingsRepository.setMainCurrencyPosition(currencyPosition);
    }

    @Override
    public int getMainCurrencyPosition() {
        return mSettingsRepository.getMainCurrencyPosition();
    }

    @Override
    public void setMainCurrency(String currencyValue) {
        mSettingsRepository.setMainCurrency(currencyValue);
    }

    @Override
    public String getMainCurrency() {
        return mSettingsRepository.getMainCurrency();
    }
}
