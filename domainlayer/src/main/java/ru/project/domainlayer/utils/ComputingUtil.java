package ru.project.domainlayer.utils;

import java.math.BigDecimal;

import javax.inject.Inject;

public class ComputingUtil {

    @Inject
    public ComputingUtil() {
        
    }

    public boolean isBidMoreThanAsk(String bidValue, String askValue) {
        BigDecimal bid = BigDecimal.valueOf(Double.valueOf(bidValue));
        BigDecimal ask = BigDecimal.valueOf(Double.valueOf(askValue));

        return bid.compareTo(ask) > 0;
    }

    public String getTotalAmount(String amount, String price) {
        BigDecimal digitPrise = BigDecimal.valueOf(Double.valueOf(price));
        BigDecimal convertedAmount = BigDecimal.valueOf(Double.valueOf(amount));

        return String.valueOf((digitPrise.multiply(convertedAmount)));
    }
}
