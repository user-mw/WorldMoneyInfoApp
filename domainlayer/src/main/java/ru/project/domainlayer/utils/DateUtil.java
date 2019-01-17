package ru.project.domainlayer.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public String getNormalDate(int timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss", Locale.ROOT);
        Date currentDate = new Date((long) timestamp * 1000);
        return format.format(currentDate);
    }
}