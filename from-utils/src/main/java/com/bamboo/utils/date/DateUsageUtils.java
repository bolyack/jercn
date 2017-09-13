package com.bamboo.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
//        System.out.println(DateUsageUtils.getDateFormat().toString());
        ArrayList<String> list = getDayForPreOrNext(10, false);
        for (String str : list) {
            System.out.println(str);
        }
    }


    /**
     * 获取过去或者未来 任意天内的日期数组
     * @param intervals intervals天内
     * @param pastFlag true-获取过去; false-获取将来
     * @return 日期数组
     */
    public static ArrayList<String> getDayForPreOrNext(int intervals, boolean pastFlag) {
        ArrayList<String> daysList = new ArrayList<String>();
        if (pastFlag) {
            for (int i = 0; i <intervals; i++) {
                daysList.add(getPastDate(i));
            }
        } else {
            for (int i = 0; i <intervals; i++) {
                daysList.add(getFetureDate(i));
            }
        }
        return daysList;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    /**
     * 获取未来 第 past 天的日期
     * @param past
     * @return
     */
    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

}
