package com.bestvike.mvphttpdaodemo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 两个时间差
     *
     * @param dateEnd
     * @param dateStart
     * @return
     */
    public static long daysBetweenTwoDays(String dateEnd, String dateStart,
                                          String formatStr) {
        SimpleDateFormat df = new SimpleDateFormat(formatStr);
        Date endDate = null;
        Date startDate = null;
        long days = -1L;
        try {
            endDate = df.parse(dateEnd);
            // System.out.println(endDate);
            startDate = df.parse(dateStart);
            // System.out.println(startDate);
            long l = endDate.getTime() - startDate.getTime();
            days = l / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 是不是超过5分钟
     *
     * @return
     */
    public static boolean isOutOfDate(String nowtime, String lastime_str) {
        SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Date endDate = null;
        Date startDate = null;
        long mins = -1L;
        try {
            endDate = df.parse(nowtime);
            // System.out.println(endDate);
            startDate = df.parse(lastime_str);

            // System.out.println(startDate);
            long l = endDate.getTime() - startDate.getTime();
            mins = l / (60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mins >= 5;
    }

    /*
     * 把时间转为long
     */
    public static long getTime(String dateStr) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date picktime = df.parse(dateStr);
            return picktime.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0L;
    }


    /**
     * @param nowtime
     * @param lastime_str
     * @return
     */
    public static boolean isOutOfDate_Token(String nowtime, String lastime_str, long expirestime) {
        SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Date endDate = null;
        Date startDate = null;
        long mins = -1L;
        try {
            endDate = df.parse(nowtime);
            // System.out.println(endDate);
            startDate = df.parse(lastime_str);

            // System.out.println(startDate);
            long l = endDate.getTime() - startDate.getTime();
            mins = l / (1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mins >= expirestime;
    }

    public static Date getDateFromString(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = sdf.parse(dateStr);
            return date;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Date getSimpleDateFromString(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse(dateStr);
            return date;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String getNowDate(String dateStr) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(dateStr);// "yyyyMMdd"
        return formatter.format(currentTime);
    }

    public static String dateToString(Date date, String formatStr) {
        String str = null;
        SimpleDateFormat formatDate = new SimpleDateFormat(formatStr); // "yyyy-MM-dd"

        try {
            str = formatDate.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 格式转换
     *
     * @param dateStr
     * @param fromatinit
     * @param formatStr
     * @return
     */
    public static String getStringformString(String dateStr, String fromatinit,
                                             String formatStr) {

        SimpleDateFormat sdf = new SimpleDateFormat(fromatinit);
        SimpleDateFormat formatDate = new SimpleDateFormat(formatStr);
        Date date;
        try {
            date = sdf.parse(dateStr);
            dateStr = formatDate.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateStr;
    }

    public static Date getDateFromLong(long datelong) {
        Date dateValue;
        try {
            dateValue = new Date(datelong);
            return dateValue;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // 根据出生日期获取年龄
    public static int getAge(Date dateOfBirth) {
        int age = 0;
        Calendar born = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        if (dateOfBirth != null) {
            now.setTime(new Date());
            born.setTime(dateOfBirth);
            if (born.after(now)) {
                throw new IllegalArgumentException(
                        "Can't be born in the future");
            }
            age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
            if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
                age -= 1;
            }
        }
        return age;
    }


    /**
     * 计算date之前n天的日期
     */
    public static Date getDateBefore(Date date, int n) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - n);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getCurrentMontn() {
        Calendar c = Calendar.getInstance();//
        int mYear = c.get(Calendar.YEAR); // 获取当前年份
        int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        int mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期
        int mWay = c.get(Calendar.DAY_OF_WEEK);// 获取当前日期的星期
        int mHour = c.get(Calendar.HOUR_OF_DAY);//时
        int mMinute = c.get(Calendar.MINUTE);//分
        return mMonth;
    }
}
