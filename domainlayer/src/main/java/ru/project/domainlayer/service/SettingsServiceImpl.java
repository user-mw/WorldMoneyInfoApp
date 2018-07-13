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
    public void setMainCurrency(String currencyValue) {
        mSettingsRepository.setMainCurrency(currencyValue);
    }

    @Override
    public String getMainCurrencyValue() {
        return mSettingsRepository.getMainCurrencyValue();
    }
}
