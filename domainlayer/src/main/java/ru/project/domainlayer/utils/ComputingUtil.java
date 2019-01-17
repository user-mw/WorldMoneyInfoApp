package ru.project.domainlayer.utils;

import java.math.BigDecimal;

public class ComputingUtil {
    private static final int LAST_SYMBOL_OFFSET = 3;
    private static final String POINT_SYMBOL = ".";

    public boolean isBidMoreThanAsk(String bidValue, String askValue) {
        BigDecimal bid = BigDecimal.valueOf(Double.valueOf(bidValue));
        BigDecimal ask = BigDecimal.valueOf(Double.valueOf(askValue));

        return bid.compareTo(ask) > 0;
    }

    public String getTotalAmount(String amount, String price) {
        BigDecimal digitPrise = BigDecimal.valueOf(Double.valueOf(price));
        BigDecimal convertedAmount = BigDecimal.valueOf(Double.valueOf(amount));

        StringBuilder result = new StringBuilder(digitPrise.multiply(convertedAmount).toString());
        int lastIndex = result.indexOf(POINT_SYMBOL) + LAST_SYMBOL_OFFSET;
        return result.substring(0, lastIndex);
    }
}