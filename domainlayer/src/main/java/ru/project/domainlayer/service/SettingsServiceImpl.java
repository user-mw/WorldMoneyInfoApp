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
    public String getMainCurrency() {
        return mSettingsRepository.getMainCurrency();
    }

    @Override
    public boolean isAutoUpdateEnabled() {
        return mSettingsRepository.isAutoUpdateEnabled();
    }

    @Override
    public int getAutoUpdatePeriodValue() {
        return mSettingsRepository.getAutoUpdatePeriodValue();
    }
}
