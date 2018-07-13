package ru.project.domainlayer.service;

public interface ISettingsService {
    void setMainCurrencyPosition(int currencyPosition);
    int getMainCurrencyPosition();
    void setMainCurrency(String currencyValue);
    String getMainCurrency();
}
