package ru.project.worldmoneyinfo.utils;

import java.math.BigDecimal;

public class ComputingUtil {
    public boolean isBidMoreThanAsk(String bidValue, String askValue) {
        BigDecimal bid = BigDecimal.valueOf(Double.valueOf(bidValue));
        BigDecimal ask = BigDecimal.valueOf(Double.valueOf(askValue));

        return bid.compareTo(ask) > 0;
    }
}
