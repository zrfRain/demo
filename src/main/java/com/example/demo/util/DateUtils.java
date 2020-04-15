package com.example.demo.util;

import com.google.common.base.Strings;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyj on 2015/11/25.
 */
public class DateUtils {
    private DateUtils() {
        throw new AssertionError();
    }


    public static final DateTimeFormatter STANDARD_DATE_FORMAT
            = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static final DateTimeFormatter EIGHT_DIGITS_DATE_FORMAT
            = DateTimeFormat.forPattern("yyyyMMdd");

    public static final DateTimeFormatter STANDARD_DATE_TIME_FORMAT
            = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    public static final DateTimeFormatter SPECIAL_DATE_TIME_FORMAT
            = DateTimeFormat.forPattern("yyyyMMddHHmmss");

    /**
     * 年月日(无下划线) yyyyMMdd
     */
    public static final String dtShort = "yyyyMMdd";

    /**
     * 日期标准格式化
     *
     * @param date 日期 yyyy-MM-dd
     * @return Date
     */
    public static Date parseStandardDate(String date) {
        if (Strings.isNullOrEmpty(date)) {
            return null;
        }
        return DateTime.parse(date, STANDARD_DATE_FORMAT).toDate();
    }


    public static String formatEightDigitsDate(Date date) {
        DateTime dateTime = new DateTime(date);
        return EIGHT_DIGITS_DATE_FORMAT.print(dateTime);
    }


    public static Date parseEightDigitsDate(String date) {
        if (Strings.isNullOrEmpty(date)) {
            return null;
        }
        return DateTime.parse(date, EIGHT_DIGITS_DATE_FORMAT).toDate();
    }

    /**
     * 标准日期时间格式化
     *
     * @param dateTime 日期时间 yyyy-MM-dd HH:mm:ss
     * @return Date
     */
    public static Date parseStandardDateTime(String dateTime) {
        if (Strings.isNullOrEmpty(dateTime)) {
            return null;
        }
        return DateTime.parse(dateTime, STANDARD_DATE_TIME_FORMAT).toDate();
    }

    public static Date getDate(String dateStr) {
        Date time = null;
        SimpleDateFormat df = new SimpleDateFormat(dtShort);
        try {
            time = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String getDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(dtShort);
        return df.format(date);
    }

    /**
     * 当前标准日期时间格式化
     *
     * @return 日期时间 yyyy-MM-dd
     */
    public static String getNowDateFormatString() {
        return STANDARD_DATE_FORMAT.print(DateTime.now());
    }

    /**
     * cch把传入的Data标准时间格式化
     *
     * @return 日期时间 yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTimeFormatString(Date date) {
        DateTime dateTime = new DateTime(date);
        return STANDARD_DATE_TIME_FORMAT.print(dateTime);
    }

    /**
     * 当前标准日期时间按照格式进行格式化
     */
    public static String getNowDateFormatString(String formatString) {
        DateTimeFormatter dateFormatter
                = DateTimeFormat.forPattern(formatString);
        return dateFormatter.print(DateTime.now());
    }

    /**
     * 当前标准日期时间格式化
     *
     * @return 日期时间 yyyy-MM-dd
     */
    public static String getDateTimeFormatString(DateTime dateTime) {
        return STANDARD_DATE_FORMAT.print(dateTime);
    }

    /**
     * 2019放假安排
     * 　　二、春节：2月4日至2月5日放假调休，共7天。2月11日（星期一）上班。
     * 　　三、清明节：4月5日至7日放假调休，共3天。4月8日（星期一）上班。
     * 　　四、劳动节：5月1日放假，。
     * 　　五、端午节：6月7日至9日放假调休，共3天。6月10日（星期一）上班。
     * 　　六、中秋节：9月13日至15日放假调休，共3天。9月16日（星期一）上班。
     * 　　节假日期间，各地区、各部门要妥善安排好值班和安全、保卫等工作，遇有重大突发事件，要按规定及时报告并妥善处置，确保人民群众祥和平安度过节日假期。
     * 获取竞猜日期
     *
     * @return
     */

    public static Map<Integer, Integer> HOLIDAY_MAP = new HashMap<Integer, Integer>() {{


        put(20190204, 20190211);
        put(20190205, 20190211);
        put(20190206, 20190211);
        put(20190207, 20190211);
        put(20190208, 20190211);
        put(20190209, 20190211);
        put(20190210, 20190211);


        put(20190405, 20180409);
        put(20190406, 20180409);
        put(20190407, 20180409);


        // 五一假期需更新了
        put(20190501, 20190506);
        put(20190502, 20190506);
        put(20190503, 20190506);
        put(20190504, 20190506);
        put(20190505, 20190506);

        put(20190607, 20190610);
        put(20190608, 20190610);
        put(20190609, 20190610);


        put(20190913, 20190916);
        put(20190914, 20190916);
        put(20190915, 20190916);


        put(20191001, 20191008);
        put(20191002, 20191008);
        put(20191003, 20191008);
        put(20191004, 20191008);
        put(20191005, 20191008);
        put(20191006, 20191008);
        put(20191007, 20191008);

    }};

    /**
     * 判断是否在开始时间和结束时间之间
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        //设置当前时间
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        //设置开始时间
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        //设置结束时间
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        //处于开始时间之后，和结束时间之前的判断
        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取当前工作日
     *
     * @return
     */
    public static Integer getT(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, 15);
        c.set(Calendar.MINUTE, 0);

        Integer guessDate;

        /**
         * 星期日为1 -> 星期六为7
         */
        int day = c.get(Calendar.DAY_OF_WEEK);

        int dayDistance = 0;

        if (day == 1) {//星期日
            dayDistance = 1;
        } else if (day == 7) {//星期六
            dayDistance = 2;
        } else {
            if (d.compareTo(c.getTime()) > 0) {//下午一点后竞猜明天
                if (day == 6) {//星期五
                    dayDistance = 3;
                } else {
                    dayDistance = 1;
                }
            }
        }

        c.add(Calendar.DAY_OF_MONTH, dayDistance);
        guessDate = Integer.parseInt(DateUtils.formatEightDigitsDate(c.getTime()));

        if (HOLIDAY_MAP.containsKey(guessDate)) {
            guessDate = HOLIDAY_MAP.get(guessDate);
        }
        return guessDate;
    }

    public static Integer getTPlusN(int n) {
        int t = getT(new Date());

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        int result = t;
        while (n > 0) {
            c.add(Calendar.DAY_OF_YEAR, 1);
            result = getT(c.getTime());
            if (result != t) {
                n--;
                t=result;
            }
        }
        return result;

    }

    /**
     * 判断今天是周几 weekday=1 周日 2 周一 3 周二 4,3   5,4   6,5   7,6
     */
    public static int getWeekDay(Date DateNow) {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekday = c.get(Calendar.DAY_OF_WEEK);

        return weekday;
    }

    //获得当天0点时间
    public static int getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) (cal.getTimeInMillis() / 1000);
    }


    //获得当天15点时间
    public static int getTimeThirteen() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 15);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) (cal.getTimeInMillis() / 1000);
    }

    //获得当天24点时间
    public static int getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) (cal.getTimeInMillis() / 1000);
    }

    //获得本周一0点时间
    public static int getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        //今天是周末
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            return (int) (cal.getTimeInMillis() / 1000) - 604800;
        } else {
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            return (int) (cal.getTimeInMillis() / 1000);
        }
    }

    //获得上周一0点时间
    public static int getTimesLastWeekmorning() {
        Calendar cal = Calendar.getInstance();
        //今天是周末
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            return (int) (cal.getTimeInMillis() / 1000) - 604800 * 2;
        } else {
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            return (int) (cal.getTimeInMillis() / 1000) - 604800;
        }
    }


    //获得下周一0点时间
    public static int getTimesNextWeekmorning() {
        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            return (int) (cal.getTimeInMillis() / 1000);
        } else {
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            return (int) (cal.getTimeInMillis() / 1000) + 604800;
        }
    }

    //获得本周日0点时间
    public static int getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        //今天是周末
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            return (int) ((cal.getTime().getTime() + ((7 * 24 - 1) * 60 * 60 * 1000)) / 1000) + 604800;
        } else {
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            return (int) ((cal.getTime().getTime() + ((7 * 24 - 1) * 60 * 60 * 1000)) / 1000);
        }

    }

    //获得下月1号0点
    public static Long getTimesLastMonth() {
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至0
        ca.set(Calendar.HOUR_OF_DAY, 24);
        //将分钟至0
        ca.set(Calendar.MINUTE, 0);
        //将秒至0
        ca.set(Calendar.SECOND, 0);
        //将毫秒至0
        ca.set(Calendar.MILLISECOND, 0);
        // 获取本月最后一天的时间戳
        return ca.getTimeInMillis();
    }

    // 获得本月第一天0点时间
    public static Long getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        //将小时至0
        cal.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        cal.set(Calendar.MINUTE, 0);
        //将秒至0
        cal.set(Calendar.SECOND, 0);
        //将毫秒至0
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }


    /**
     * 当前季度的开始时间，即2012-04-01 00:00:00
     *
     * @return
     */
    public static Long getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 3);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 6);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 9);
            }
            c.set(Calendar.DATE, 1);
            //将小时至0
            c.set(Calendar.HOUR_OF_DAY, 0);
            //将分钟至0
            c.set(Calendar.MINUTE, 0);
            //将秒至0
            c.set(Calendar.SECOND, 0);
            //将毫秒至0
            c.set(Calendar.MILLISECOND, 0);
            return c.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTimeInMillis();
    }


    /**
     * 当前季度的结束时间，即2012-04-01 00:00:00
     *
     * @return
     */
    public static Long getCurrentQuarterEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            //将小时至0
            c.set(Calendar.HOUR_OF_DAY, 24);
            //将分钟至0
            c.set(Calendar.MINUTE, 0);
            //将秒至0
            c.set(Calendar.SECOND, 0);
            //将毫秒至0
            c.set(Calendar.MILLISECOND, 0);
            return c.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTimeInMillis();
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 返回日期对应周几，周日返回 7
     *
     * @param date
     * @return
     */
    public static Integer getDateOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day == 0) {
            return 7;
        }
        return day;
    }

    /**
     * 获取几天前日期
     *
     * @param day
     * @return
     */
    public static String minusDay(int day) {
        LocalDate now = LocalDate.now();
        LocalDate before = now.minusDays(day);
        String beforeDay = before.format(java.time.format.DateTimeFormatter.BASIC_ISO_DATE);
        return beforeDay;
    }

    public static String addDay(int day) {
        LocalDate now = LocalDate.now();
        LocalDate after = now.plusDays(day);
        String afterDay = after.format(java.time.format.DateTimeFormatter.BASIC_ISO_DATE);
        return afterDay;
    }

    public static Date addDay(Date date, int day) {
        long plusTime = day * 24 * 60 * 60 * 1000L;
        long afterDateLong = date.getTime() + plusTime;
        return new Date(afterDateLong);
    }

    /**
     * 获得日期的下一个星期一的日期
     *
     * @param date
     * @return
     */
    public static Calendar getNextMonday(Calendar date) {
        Calendar result = null;
        result = date;
        do {
            result = (Calendar) result.clone();
            result.add(Calendar.DATE, 1);
        } while (result.get(Calendar.DAY_OF_WEEK) != 2);
        return result;
    }


    /**
     * 获取日期的下一个星期一零点的日期
     *
     * @param date
     * @return
     */
    public static Calendar getNextMondayZero(Calendar date) {
        Calendar monday = getNextMonday(date);
        date.set(monday.get(Calendar.YEAR), monday.get(Calendar.MONDAY), monday.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return date;
    }

    /**
     * 判断是否在改时间段内
     *
     * @param startTime 15:00
     * @param endTime   18:00
     * @param now
     * @return
     * @throws ParseException
     */
    public static boolean inTime(String startTime, String endTime, Date now) {
        try {
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            //初始化
            Date nowTime = df.parse(df.format(now));
            Date beginDate = df.parse(startTime);
            Date endDate = df.parse(endTime);
            //调用判断方法
            boolean flag = belongCalendar(nowTime, beginDate, endDate);
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 根据星期和时分获取构造对应的日期
     *
     * @param weekDay
     * @param hmTime
     * @return
     */
    public static Date getDate(Integer weekDay, String hmTime) {
        Calendar calendar = Calendar.getInstance();
        String[] hm = hmTime.split(":");
        if (hm.length < 2) {
            throw new IllegalArgumentException("hm参数没有按照规定的的  19:00");
        }
        if (weekDay < 1 || weekDay > 7) {
            throw new IllegalArgumentException("weekDay参与范围错误 1-7之间");
        }
        String hour = hm[0];
        String mins = hm[1];
        int dayOfWeek = weekDay;
        if (weekDay == 7) {
            dayOfWeek = 1;
        } else {
            dayOfWeek = weekDay + 1;
        }
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);

        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hour));
        calendar.set(Calendar.MINUTE, Integer.valueOf(mins));
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        System.out.println(getT(new Date()));
        System.out.println(getTPlusN(1));
        System.out.println(getTPlusN(2));
        System.out.println(getTPlusN(3));
    }

}
