package com.share.api.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类
 *
 * @author Administrator
 */
public class DateUtils {

    public static final SimpleDateFormat TIME_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 判断一个时间是否在另一个时间之前
     *
     * @param time1 第一个时间
     * @param time2 第二个时间
     * @return 判断结果
     */
    public static boolean before(String time1, String time2) {
        try {
            Date dateTime1 = TIME_FORMAT.parse(time1);
            Date dateTime2 = TIME_FORMAT.parse(time2);

            if (dateTime1.before(dateTime2)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断一个时间是否在另一个时间之后
     *
     * @param time1 第一个时间
     * @param time2 第二个时间
     * @return 判断结果
     */
    public static boolean after(String time1, String time2) {
        try {
            Date dateTime1 = TIME_FORMAT.parse(time1);
            Date dateTime2 = TIME_FORMAT.parse(time2);

            if (dateTime1.after(dateTime2)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @Author：weiqiming
     * @Description： 计算时间差值 日期格式字符串  yyyy-MM-dd HH:mm:ss
     * @Date： 2020/5/8 10:09
     * @Param： [time1, time2, returnType（ss：秒；mm：分钟；HH：小时。）]
     * @return：int
     **/
    public static int minus(String time1, String time2, String returnType) {
        try {
            Date datetime1 = TIME_FORMAT.parse(time1);
            Date datetime2 = TIME_FORMAT.parse(time2);
            long millisecond = datetime1.getTime() - datetime2.getTime();
            int returnInt = 0;
            switch (returnType) {
                case "ss":
                    returnInt = Integer.parseInt(String.valueOf(millisecond / 1000));
                    break;
                case "mm":
                    returnInt = Integer.parseInt(String.valueOf(millisecond / (1000 * 60)));
                    break;
                case "HH":
                    returnInt = Integer.parseInt(String.valueOf(millisecond / (1000 * 3600)));
                    break;
            }
            return returnInt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @Author：weiqiming
     * @Description： 计算时间差值 long 值
     * @Date： 2020/5/8 10:23
     * @Param： [time1, time2, returnType（ss：秒；mm：分钟；HH：小时。）]
     * @return：int
     **/
    public static int minus(long time1, long time2, String returnType) {
        try {
            long millisecond = time1 - time2;
            int returnInt = 0;
            switch (returnType) {
                case "ss":
                    returnInt = Integer.parseInt(String.valueOf(millisecond / 1000));
                    break;
                case "mm":
                    returnInt = Integer.parseInt(String.valueOf(millisecond / (1000 * 60)));
                    break;
                case "HH":
                    returnInt = Integer.parseInt(String.valueOf(millisecond / (1000 * 3600)));
                    break;
            }
            return returnInt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取年月日和小时
     *
     * @param datetime 时间（yyyy-MM-dd HH:mm:ss）
     * @return 结果
     */
    public static String getDateHour(String datetime) {
        String date = datetime.split(" ")[0];
        String hourMinuteSecond = datetime.split(" ")[1];
        String hour = hourMinuteSecond.split(":")[0];
        return date + "_" + hour;
    }

    /**
     * @Author：weiqiming
     * @Description：
     * @Date： 2020/5/8 10:17
     * @Param： [isTime（true：返回带时间格式日期；false：仅返回日期格式；）]
     * @return： java.lang.String
     **/
    public static String getTodayDate(boolean isTime) {
        if (isTime) {
            return TIME_FORMAT.format(new Date());
        } else {
            return DATE_FORMAT.format(new Date());
        }
    }

    /**
     * 获取昨天的日期（yyyy-MM-dd）
     *
     * @return 昨天的日期
     */
    public static String getYesterdayDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -1);

        Date date = cal.getTime();

        return DATE_FORMAT.format(date);
    }

    /**
     * 格式化日期（yyyy-MM-dd）
     *
     * @param date Date对象
     * @return 格式化后的日期
     */
    public static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    /**
     * 格式化时间（yyyy-MM-dd HH:mm:ss）
     *
     * @param date Date对象
     * @return 格式化后的时间
     */
    public static String formatTime(Date date) {
        return TIME_FORMAT.format(date);
    }

}
