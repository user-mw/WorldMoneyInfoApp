package ru.project.domainlayer.service;

import javax.inject.Inject;

import ru.project.domainlayer.repository.ISettingsRepository;

public class SettingsServiceImpl implements ISettingsService {

    @Inject
    ISettingsRepository settingsRepository;

    @Inject
    public SettingsServiceImpl() {

    }

    @Override
    public String getMainCurrency() {
        return settingsRepository.getMainCurrency();
    }

    @Override
    public boolean isAutoUpdateEnabled() {
        return settingsRepository.isAutoUpdateEnabled();
    }

    @Override
    public int getAutoUpdatePeriodValue() {
        return settingsRepository.getAutoUpdatePeriodValue();
    }
}