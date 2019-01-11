package ru.project.domainlayer.repository;

public interface ISettingsRepository {
    String MAIN_CURRENCY = "MainCurrency";
    String DEFAULT_CURRENCY = "RUB";
    String AUTO_UPDATE = "AutoUpdate";
    boolean DEFAULT_AUTO_UPDATE_VALUE = false;
    String AUTO_UPDATE_PERIOD = "AutoUpdatePeriod";
    int DEFAULT_AUTO_UPDATE_PERIOD_VALUE = 5;

    String getMainCurrency();
    boolean isAutoUpdateEnabled();
    int getAutoUpdatePeriodValue();
}
