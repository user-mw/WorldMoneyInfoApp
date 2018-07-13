package ru.project.domainlayer.repository;

public interface ISettingsRepository {
    String MAIN_CURRENCY = "MainCurrency";
    String DEFAULT_CURRENCY = "RUB";

    void setMainCurrency(String currencyValue);
    String getMainCurrencyValue();
}
