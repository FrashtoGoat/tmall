package com.xiaoluban.tmallcommon.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class MyDateUtils {

//    SimpleDateFormat的方法是线程不安全，不用做静态变量
//    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
//    private static SimpleDateFormat sdf2=new SimpleDateFormat("HHmmss");

//    private static SimpleDateFormat sdf3=new SimpleDateFormat("yyyyMMddHHmmss");

//    private static SimpleDateFormat sdf_ = new SimpleDateFormat("yyyy-MM-dd");

//    public static String formatDateAndTime(Date date){
//        return sdf3.format(date);
//    }

    public static String formatDate(Date date){
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    public static String formatTime(Date date){
        return new SimpleDateFormat("HHmmss").format(date);
    }
    
    /**
	 * yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate_(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTime_(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

    public static Date strTranDate(String dateStr){
        try {
            String format="yyyy-MM-dd HH:mm:ss";
            return new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            log.error("字符转日期异常",e);
           return null;
        }
    }

//    public static Date strTranDate(String dateStr){
//        try {
//            return new SimpleDateFormat("yyyyMMdd").parse(dateStr);
//        } catch (ParseException e) {
//            return null;
//        }
//    }
//
//    public static Date strToDate(String dateStr){
//        try {
//            return sdf3.parse(dateStr);
//        } catch (ParseException e) {
//            return null;
//        }
//    }

    /**
     * date1>date2返回true
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareTwoDate(Date date1,Date date2){

	    if(date1==null || date2==null){
	        return false;
        }

        int interval=date1.compareTo(date2);
        if(interval>0){
            return true;
        }
        return false;
    }


    /**
     * 通过毫秒值，手动计算日期间的相关的值
     *
     * 跨年不会出现问题
     * 使用此种方法的话需要注意
     * 如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 0
     */
    public static Long twoDaysInternal(Date date1,Date date2) {
        return (date1.getTime()-date2.getTime())/(1000*3600*24);
    }

    //日期差值
    public static int daysOfTwo(Date fDate, Date oDate) {

        if(fDate==null || oDate==null){
            return -1;
        }

        Calendar fCal = Calendar.getInstance();
        Calendar oCal = Calendar.getInstance();

        fCal.setTime(fDate);
        oCal.setTime(oDate);

        //获取日期在一年中的第多少天
        int day1 = fCal.get(Calendar.DAY_OF_YEAR);
        int day2 = oCal.get(Calendar.DAY_OF_YEAR);

        //获取当前日期所在的年份
        int year1 = fCal.get(Calendar.YEAR);
        int year2 = oCal.get(Calendar.YEAR);

        if(year1>year2){
            return -1;
        }

        if(year1 < year2)
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0) //闰年
                {
                    timeDistance += 366;
                }
                else //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2-day1);
        }

        return (day2-day1);
    }

    /**
	 * 获取指定时间的几天之后或几天之前
	 * 
	 * @param date
	 * @param days 正数：几天之后，负数：几天之前
	 * @return
	 */
	public static Date getDateAfterDays(Date date, int days) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.DATE, days);
		return now.getTime();
	}

	/**
	 * 获取指定时间的几小时之后或几小时之前
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateAfterHours(Date date, int hour) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.HOUR, hour);
		return now.getTime();
	}


	//获取当前时间
    public static String getNow(){
	    return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }

    //获取当前日期
    public static String getDateNow(){
        //return DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now());
        return getDate("yyyy-MM-dd",LocalDate.now());
    }

    public static String getDate(String pattern, LocalDate date){
        return DateTimeFormatter.ofPattern(pattern).format(date);
    }

    public static Date getTodayZero(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
       return calendar.getTime();
    }


}
