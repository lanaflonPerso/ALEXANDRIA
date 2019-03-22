package com.alexandria.windows.util;

import com.sun.istack.Nullable;
import org.jdesktop.beansbinding.Converter;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * {@code Date} to {@code String} converter
 * that returns null when the argument cannot be parsed.
 *
 */
public class DateConverter extends Converter<Date, String> {

    @Override
    public String convertForward(Date value) {
        DateFormat df = new SimpleDateFormat("yyyy/MMM/dd");
        return df.format(value);
    }

    @Override
	@Nullable
    public Date convertReverse(String arg) {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date = null;
        try {
            date = sdf1.parse(arg);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        Date sqlDate = new Date(date.getTime());

        return sqlDate;
    }
}
