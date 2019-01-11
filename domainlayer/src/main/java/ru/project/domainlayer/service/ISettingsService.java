package ru.project.domainlayer.service;

public interface ISettingsService {
    String getMainCurrency();
    boolean isAutoUpdateEnabled();
    int getAutoUpdatePeriodValue();
}
