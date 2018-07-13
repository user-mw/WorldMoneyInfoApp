package ru.project.domainlayer.repository;

public interface ISettingsRepository {
    String MAIN_CURRENCY = "MainCurrency";
    String DEFAULT_CURRENCY = "RUB";
    String MAIN_CURRENCY_POSITION = "MainCurrencyPosition";
    int DEFAULT_POSITION = 0;

    void setMainCurrencyPosition(int currencyPosition);
    int getMainCurrencyPosition();
    void setMainCurrency(String mainCurrencyValue);
    String getMainCurrency();
}
