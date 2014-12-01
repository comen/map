/**
 * 
 */
package cn.com.chinatelecom.map.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Shelwin
 *
 */
public class DateUtils {
	
	public static Date getCurrentDate() {
		
		TimeZone shZone = TimeZone.getTimeZone("Asia/Shanghai");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(shZone);
		
		Date currentDate = new Date();
		return currentDate;
		
	}
	
	public static Date getFirstDayOfThisWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date firstDayOfThisWeek = cal.getTime();
        
        return firstDayOfThisWeek;
	}
	
	public static Date getFirstDayOfLastWeek(Date date) {
		Date firstDayOfThisWeek = getFirstDayOfThisWeek(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(firstDayOfThisWeek);
		cal.add(Calendar.DATE, -1);
        Date firstDayOfLastWeek = getFirstDayOfThisWeek(cal.getTime());
        
        return firstDayOfLastWeek;
	}
	
	public static Date getLastDayOfThisWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date lastDayOfThisWeek = cal.getTime();
        
        return lastDayOfThisWeek;
	}
	
	public static Date getLastDayOfLastWeek(Date date) {
		Date firstDayOfThisWeek = getFirstDayOfThisWeek(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(firstDayOfThisWeek);
		cal.add(Calendar.DATE, -1);
        Date lastDayOfLastWeek = cal.getTime();
        
        return lastDayOfLastWeek;
	}
	
	public static Date getFirstDayOfThisMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);	//设置为当月第一天
        Date firstDayOfThisMonth = cal.getTime();
        
        return firstDayOfThisMonth;
	}
	
	public static Date getFirstDayOfLastMonth(Date date) {
		Date firstDayOfThisMonth = getFirstDayOfThisMonth(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(firstDayOfThisMonth);
		cal.add(Calendar.DATE, -1);
        Date firstDayOfLastMonth = getFirstDayOfThisMonth(cal.getTime());
        
        return firstDayOfLastMonth;
	}
	
	public static Date getLastDayOfThisMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);	//设置为当月第一天
		cal.add(Calendar.MONTH, 1);    		//加一个月
		cal.set(Calendar.DATE, 1);        	//设置为该月第一天
		cal.add(Calendar.DATE, -1);    		//再减一天即得到当月最后一天
        Date lastDayOfThisMonth = cal.getTime();
        
        return lastDayOfThisMonth;
	}
	
	public static Date getLastDayOfLastMonth(Date date) {
		Date firstDayOfThisMonth = getFirstDayOfThisMonth(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(firstDayOfThisMonth);
		cal.add(Calendar.DATE, -1);
        Date lastDayOfLastMonth = cal.getTime();
        
        return lastDayOfLastMonth;
	}
	
	public static Date getFirstDayOfThisMonthLastYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, -1);
        Date firstDayOfThisMonthLastYear = getFirstDayOfThisMonth(cal.getTime());
        
        return firstDayOfThisMonthLastYear;
	}
	
	public static Date getLastDayOfThisMonthLastYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, -1);
        Date lastDayOfThisMonthLastYear = getLastDayOfThisMonth(cal.getTime());
        
        return lastDayOfThisMonthLastYear;
	}
	
}
