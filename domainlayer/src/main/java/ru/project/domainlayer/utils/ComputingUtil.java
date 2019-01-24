package ru.project.domainlayer.utils;

public class ComputingUtil {
    private static final int LAST_SYMBOL_OFFSET = 3;
    private static final String POINT_SYMBOL = ".";
    public static final String DEFAULT_RESULT_REPLACEMENT = "1";
    public static final char ZERO_CHARACTER = '0';

    public String getTotalAmount(String amount, String price) {
        double digitPrise = Double.valueOf(price);
        double convertedAmount = Double.valueOf(amount);

        StringBuilder result = new StringBuilder();
        result.append(convertedAmount / digitPrise);
        int lastIndex = result.indexOf(POINT_SYMBOL) + LAST_SYMBOL_OFFSET;

        char last = result.charAt(lastIndex - 1);
        int lastDigit = result.charAt(lastIndex);
        if(last == ZERO_CHARACTER) {
            result.replace(lastIndex - 1, lastIndex, DEFAULT_RESULT_REPLACEMENT);
        } else {
            if(lastDigit >= 5) {
                int rightDigit = Character.getNumericValue(last) + 1;
                result.replace(lastIndex - 1, lastIndex, String.valueOf(rightDigit));
            }
        }

        return result.substring(0, lastIndex);
    }
}