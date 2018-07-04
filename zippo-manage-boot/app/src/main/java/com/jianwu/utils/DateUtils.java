package com.jianwu.utils;

import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tookbra on 2016/3/8.
 */
public class DateUtils {
    public static final SimpleDateFormat longDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");

    public static final SimpleDateFormat longDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static final SimpleDateFormat shortDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat shortDateFormat2 = new SimpleDateFormat("yyyy-MM");

    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public final static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public final static String YYYY_MM_DD = "yyyy-MM-dd";

    public final static String YYYYMMDD = "yyyyMMdd";

    public final static String YYYYMM = "yyyy-MM";

    public final static String YYYY = "yyyy";

    public final static String HH_MM_SS = "HH:mm:ss";

    public final static String UTC="yyyy-MM-dd'T'HH:mm:ss.SSS Z";

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss.sss";

    public static final List<String> houstList =  Lists.newArrayList("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","00");

    public static String format(Date date, String format) {
        return null == date ? "" : new SimpleDateFormat(format).format(date);
    }

    /**
     * 日期字符串
     * @param date 时间
     * @return
     */
    public static String format(Date date) {
        return format(date, DEFAULT_FORMAT);
    }

    public static Date getCurrentDate() throws Exception{
        return getCurrentDate(DateUtils.DEFAULT_FORMAT);
    }

    public static Date getNow() {
        return new Date();
    }

    /**
     * 根据指定格式获取当前时间
     * @param format
     * @return
     */
    public static String getCurrentTime(String format){
        SimpleDateFormat sdf = getFormat(format);
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 获取指定格式的当前时间：为空时格式为yyyy-mm-dd HH:mm:ss
     * @param format
     * @return
     * @throws Exception
     */
    public static Date getCurrentDate(String format) throws Exception{
        SimpleDateFormat sdf = getFormat(format);
        String dateS = getCurrentTime(format);
        Date date = null;
        try {
            date = sdf.parse(dateS);
        } catch (ParseException e) {
            throw new Exception("时间转换出错..");
        }
        return date;
    }


    public static Date getCurrentDateTime(){

        return getDateTime(null);
    }

    public static Date getDateTime(Date date){
        Calendar calendar=Calendar.getInstance();
        if (date!=null) {
            calendar.setTime(date);
        }
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }

    public static Date format(String value) throws ParseException {
        return longDateFormat1.parse(value);
    }

    /**
     * @Author chenDong
     * @Date 2017/8/24 11:14
     * yyyy-MM-dd
     */
    public static Date formatStringToDate(String value) throws ParseException {
        return shortDateFormat.parse(value);
    }

    /**
     * @Author chenDong
     * @Date 2017/8/24 11:14
     * yyyy-MM-dd
     */
    public static Date formatStringToDate2(String value) throws ParseException {
        return shortDateFormat2.parse(value);
    }


    /**
     * 验证结束时间是否大于开始时间
     * @param beginTime
     *              开始时间
     * @param endTime
     *              结束时间
     * @return boolean 是true，否false
     */
    public static boolean compareDate(String beginTime, String endTime) throws ParseException {
        Date beginDate = longDateFormat1.parse(beginTime);
        Date endDate = longDateFormat1.parse(endTime);
        if(beginDate.getTime() > endDate.getTime()) {
            return  false;
        } else {
            return true;
        }
    }

    /**
     * 验证结束时间是否大于开始时间
     * @param beginTime
     *              开始时间
     * @param endTime
     *              结束时间
     * @return boolean 是true，否false
     */
    public static boolean compareDate(Date beginTime, Date endTime) {
        if(beginTime.getTime() > endTime.getTime()) {
            return  false;
        } else {
            return true;
        }
    }

    /**
     * 获取日期显示格式，为空默认为yyyy-mm-dd HH:mm:ss
     * @param format
     * @return
     */
    private static SimpleDateFormat getFormat(String format){
        if(format == null || "".equals(format)){
            format = DateUtils.DEFAULT_FORMAT;
        }
        return new SimpleDateFormat(format);
    }

    /**
     * 时间分钟加减
     * @param date 时间
     * @param minutes 分钟
     * @return
     */
    public static Date getDateAfterMinute(Date date, int minutes) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + minutes);
        return now.getTime();
    }

    public static Date getDateAfterMinute() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, 5);
        return now.getTime();
    }

    public static Date getCurrentMonth(){
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.DATE,1);
        return calendar.getTime();
    }
    public static Date getMonth(Date date){
        Calendar calendar=Calendar.getInstance();
        if (date!=null) calendar.setTime(date);
        calendar.set(Calendar.DATE,1);
       return calendar.getTime();
    }

    public static Date getMonthLastDay(Date date){
        Calendar calendar=Calendar.getInstance();
        if (date!=null) calendar.setTime(date);
        calendar.set(Calendar.DATE,1);
        calendar.add(Calendar.MONTH,1);
        calendar.add(Calendar.DATE,-1);
        return calendar.getTime();
    }



    public static Date getMonthLastDay(String dateTime){
        Date date=null;
        if (!StringUtils.isEmpty(dateTime))date = DateUtils.stringToDate(dateTime);
        return getMonthLastDay(date);
    }

    public static Date getMonth(String dateTime){
        Date date=null;
        if (!StringUtils.isEmpty(dateTime))date = DateUtils.stringToDate(dateTime);
        return getMonth(date);
    }
    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date
     *            日期
     * @param dateType
     *            类型
     * @param amount
     *            数值
     * @return 计算后日期
     */
    private static Date addDate(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }


    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date
     *            日期
     * @param yearAmount
     *            增加数量。可为负数
     * @return 增加年份后的日期
     */
    public static Date addYear(Date date, int yearAmount) {
        return addDate(date, Calendar.YEAR, yearAmount);
    }


    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date
     *            日期
     * @param monthAmount
     *            增加数量。可为负数
     * @return 增加月份后的日期
     */
    public static Date addMonth(Date date, int monthAmount) {
        return addDate(date, Calendar.MONTH, monthAmount);
    }


    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date
     *            日期
     * @param dayAmount
     *            增加数量。可为负数
     * @return 增加天数后的日期
     */
    public static Date addDay(Date date, int dayAmount) {
        return addDate(date, Calendar.DATE, dayAmount);
    }


    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date
     *            日期
     * @param hourAmount
     *            增加数量。可为负数
     * @return 增加小时后的日期
     */
    public static Date addHour(Date date, int hourAmount) {
        return addDate(date, Calendar.HOUR_OF_DAY, hourAmount);
    }


    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date
     *            日期
     * @param minuteAmount
     *            增加数量。可为负数
     * @return 增加分钟后的日期
     */
    public static Date addMinute(Date date, int minuteAmount) {
        return addDate(date, Calendar.MINUTE, minuteAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date
     *            日期
     * @param secondAmount
     *            增加数量。可为负数
     * @return 增加秒钟后的日期
     */
    public static Date addSecond(Date date, int secondAmount) {
        return addDate(date, Calendar.SECOND, secondAmount);
    }

    /**
     * 时间差（分）
     * @param begin 开始时间
     * @param end 结束时间
     * @return Long
     */
    public static Long diffTime(Date begin, Date end) {
        return (end.getTime() - begin.getTime())/(1000 * 60) % 60;
    }

    /**
     * 验证时间格式是否正确
     * @param time
     * @return
     */
    public static boolean validateDate(String time) {
        String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) "
                + "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

    /**
     * 将指定的字符串解析成日期类型
     *
     * @param dateStr
     *            字符串格式的日期
     * @return
     */
    public static Date stringToDate(String dateStr, String pattern) {
        SimpleDateFormat format = getFormat(pattern);
        Date date = null;
        try {
            date =  format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }

    public static Date stringToDate(String dateStr) {
        if (dateStr.length() == 7) {
            return stringToDate(dateStr, YYYYMM);
        } else if (dateStr.length() == 10) {
            return stringToDate(dateStr, YYYY_MM_DD);
        } else if(dateStr.length() == 16) {
            return stringToDate(dateStr, YYYY_MM_DD_HH_MM);
        } else if (dateStr.length() == 19) {
            return stringToDate(dateStr, YYYY_MM_DD_HH_MM_SS);
        } else if (dateStr.length() == 24){
            dateStr = dateStr.replace("Z", " UTC");//注意是空格+UTC
            return stringToDate(dateStr,UTC);
        }else {
            try {
                return stringToDate(dateStr, YYYY_MM_DD_HH_MM_SS);
            } catch (Exception e) {
                throw new RuntimeException("不支持的日期格式.");
            }
        }
    }



    /**
     * 返回unix时间戳 (1970年至今的秒数)
     * @return
     */
    public static long getUnixStamp(){
        return System.currentTimeMillis()/1000;
    }

    public static long getTodayTimeStamp() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    public static List<String> getAllHour() {
        return houstList;
    }

    public static List<String> getSubDate(String startDate, String endDate) {
        List<String> list = Lists.newArrayList();
        if (startDate.equals(endDate)) {
            return list;
        }
        Date start = stringToDate(startDate);
        Date end = stringToDate(endDate);
        Calendar calendar = Calendar.getInstance();
        while(start.before(end) || start.equals(end)) {
            calendar.setTime(start);
            list.add(format(calendar.getTime(),YYYY_MM_DD));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            start = calendar.getTime();
        }
        return list;
    }

    public static List<String> getSubWeek(String startDate, String endDate) {
        List<String> list = Lists.newArrayList();
        Calendar calendar = Calendar.getInstance();
        Date start = stringToDate(startDate);
        Date end = stringToDate(endDate);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        while(start.before(end)) {
            calendar.setTime(start);
            list.add(format(calendar.getTime(),YYYY_MM_DD));
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - day);
            calendar.add(Calendar.DATE, 7);
//            calendar.add(Calendar.WEEK_OF_MONTH, 1);
            start = calendar.getTime();
        }
        return list;
    }

    public static List<String> getSubHour() {
        return null;
    }

    public static boolean sclInWeek(String startDate, String endDate) {
        Date start = stringToDate(startDate);
        Date end = stringToDate(endDate);
        long day = (end.getTime()-start.getTime())/(24*60*60*1000);
        if(day >7) {
            return false;
        }
        return true;
    }

    // 获取当前时间所在周的开始日期
    public static String getWeekFirstDay(String startDate) {
        Date start = stringToDate(startDate);
        return format(getFirstDayOfWeek(start),YYYY_MM_DD);
    }

    // 获取当前时间所在周的开始日期
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        return c.getTime();
    }

    public static List<String> getSubMonth(String startDate, String endDate) {
        List<String> list = Lists.newArrayList();
        if (startDate.equals(endDate)) {
            return list;
        }
        Date start = stringToDate(startDate);
        Date end = stringToDate(endDate);
        Calendar calendar = Calendar.getInstance();
        while(start.before(end) || start.equals(end)) {
            calendar.setTime(start);
            list.add(format(calendar.getTime(),YYYYMM));
            calendar.add(Calendar.MONTH, 1);
            start = calendar.getTime();
        }
        return list;

    }

    public static List<String> getSubYear(String startDate, String endDate) {
        List<String> list = Lists.newArrayList();
        if (startDate.equals(endDate)) {
            return list;
        }
        Date start = stringToDate(startDate);
        Date end = stringToDate(endDate);
        Calendar calendar = Calendar.getInstance();
        while(start.before(end) || start.equals(end)) {
            calendar.setTime(start);
            list.add(format(calendar.getTime(),YYYY));
            calendar.add(Calendar.YEAR, 1);
            start = calendar.getTime();
        }
        return list;

    }


    public static int daysBetween(Long smdate,Long bdate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(new Date(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

       return Integer.parseInt(String.valueOf(between_days));
    }

    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static String getWeekByDate(String time) {
        Date date = DateUtils.stringToDate(time, "yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd"); // 设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        //System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        String imptimeBegin = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());
        return imptimeBegin + "-" + imptimeEnd;
    }

    public static String getMonthByDate(String time) {
        Date date = DateUtils.stringToDate(time, "yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayYear = cal.get(Calendar.YEAR);
        int dayMonth = cal.get(Calendar.MONTH) + 1;
        return dayYear + "年" + dayMonth + "月";
    }


    public static void main(String [] args) {



    }
}
