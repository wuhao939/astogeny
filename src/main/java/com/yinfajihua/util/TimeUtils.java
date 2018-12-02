package com.yinfajihua.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String getCurrentTime(){
        return simpleDateFormat.format(new Date());
    }
    public static String cal_time_diff(String target_time) throws ParseException {
        String current_time = simpleDateFormat.format(new Date());
        long from = simpleDateFormat.parse(target_time).getTime();
        long to = simpleDateFormat.parse(current_time).getTime();
        int days = (int) ((to - from)/(1000 * 60 * 60 * 24));
        int hours = (int) ((to - from)/(1000 * 60 * 60));
        int minutes = (int) ((to - from)/(1000 * 60));
        int weeks = days / 7;
        int months = days / 30;
        String time_diff;
        if (months>=1)
            time_diff = months+"月前";
        else if (weeks>=1)
            time_diff = weeks+"周前";
        else if (days>=1)
            time_diff = days+"日前";
        else if (hours>=1)
            time_diff = hours+"小时前";
        else if (minutes>=10)
            time_diff = minutes+"分钟前";
        else
            time_diff = "刚刚";
        return time_diff;
    }
}
