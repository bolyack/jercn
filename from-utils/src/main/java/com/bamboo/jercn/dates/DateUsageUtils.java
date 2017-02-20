package com.bamboo.jercn.dates;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bamboo on 2017/2/20.
 */
public class DateUsageUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static ThreadLocal threadlocal = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat(DATE_FORMAT);
        }
    };

    public static DateFormat getDateFormat() {
        return (DateFormat) threadlocal.get();
    }

    public static Date parse(String textdate) throws ParseException {
        return getDateFormat().parse(textdate);
    }


    public static void main(String[] args) {
        System.out.println(DateUsageUtils.getDateFormat().toString());
    }

}
