package ru.base.util.converter;

import org.springframework.format.Formatter;
import ru.base.util.DateTimeUtil;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalTimeFormatter implements Formatter<LocalTime> {
    @Override
    public LocalTime parse(String text, Locale locale) throws ParseException{
        return DateTimeUtil.parseLocalTime(text,null);
    }

    @Override
    public String print(LocalTime lt, Locale locale) {
        return lt.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
