package ru.project.domainlayer.service;

public interface ISettingsService {
    void setMainCurrency(String currencyValue);
    String getMainCurrencyValue();
}
