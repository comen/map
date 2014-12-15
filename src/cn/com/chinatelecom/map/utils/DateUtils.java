package cn.com.chinatelecom.map.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import cn.com.chinatelecom.map.common.Config;
//import java.text.DateFormat;

/**
 * @author Shelwin
 *
 */
public class DateUtils {

	private static Logger logger = Logger.getLogger(DateUtils.class);

	public static Date getCurrentDate() {
		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(Config.getInstance()
				.getValue("dateFormat"));
		try {
			currentDate = sdf.parse(sdf.format(currentDate));
		} catch (Exception e) {
			logger.fatal("解析当前日期错误: " + e.getMessage());
		}
		return currentDate;
	}
	

	public static Date getSpecificDate(String dateStr, String dateFormat) {
		if (null == dateStr || null == dateFormat) {
			logger.error("输入日期字符串或格式字符串为空！");
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			logger.fatal("获取指定格式的日期错误: " + e.getMessage());
		}
		return date;
	}

	public static String getDateTimeString(Date date, String format) {
		if (null == date || null == format) {
			logger.error("输入日期或格式字符串为空！");
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static Date getFirstDayOfThisWeek(Date date) {
		if (null == date) {
			logger.error("获取本周第一天的输入为空！");
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		Date firstDayOfThisWeek = cal.getTime();
		return firstDayOfThisWeek;
	}

	public static Date getFirstDayOfLastWeek(Date date) {
		if (null == date) {
			logger.error("获取上周第一天的输入为空！");
			return null;
		}
		Date firstDayOfThisWeek = getFirstDayOfThisWeek(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(firstDayOfThisWeek);
		cal.add(Calendar.DATE, -1);

		Date firstDayOfLastWeek = getFirstDayOfThisWeek(cal.getTime());
		return firstDayOfLastWeek;
	}

	public static Date getLastDayOfThisWeek(Date date) {
		if (null == date) {
			logger.error("获取本周最后一天的输入为空！");
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date lastDayOfThisWeek = cal.getTime();
		return lastDayOfThisWeek;
	}

	public static Date getLastDayOfLastWeek(Date date) {
		if (null == date) {
			logger.error("获取上周最后一天的输入为空！");
			return null;
		}
		Date firstDayOfThisWeek = getFirstDayOfThisWeek(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(firstDayOfThisWeek);
		cal.add(Calendar.DATE, -1);

		Date lastDayOfLastWeek = cal.getTime();
		return lastDayOfLastWeek;
	}

	public static Date getFirstDayOfThisMonth(Date date) {
		if (null == date) {
			logger.error("获取本月第一天的输入为空！");
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1); // 设置为当月第一天
		Date firstDayOfThisMonth = cal.getTime();
		return firstDayOfThisMonth;
	}

	public static Date getFirstDayOfLastMonth(Date date) {
		if (null == date) {
			logger.error("获取上月第一天的输入为空！");
			return null;
		}
		Date firstDayOfThisMonth = getFirstDayOfThisMonth(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(firstDayOfThisMonth);
		cal.add(Calendar.DATE, -1);
		
		Date firstDayOfLastMonth = getFirstDayOfThisMonth(cal.getTime());
		return firstDayOfLastMonth;
	}

	public static Date getLastDayOfThisMonth(Date date) {
		if (null == date) {
			logger.error("获取本月最后一天的输入为空！");
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1); // 设置为当月第一天
		cal.add(Calendar.MONTH, 1); // 加一个月
		cal.set(Calendar.DATE, 1); // 设置为该月第一天
		cal.add(Calendar.DATE, -1); // 再减一天即得到当月最后一天
		Date lastDayOfThisMonth = cal.getTime();
		return lastDayOfThisMonth;
	}

	public static Date getLastDayOfLastMonth(Date date) {
		if (null == date) {
			logger.error("获取上月最后一天的输入为空！");
			return null;
		}
		Date firstDayOfThisMonth = getFirstDayOfThisMonth(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(firstDayOfThisMonth);
		cal.add(Calendar.DATE, -1);

		Date lastDayOfLastMonth = cal.getTime();
		return lastDayOfLastMonth;
	}

	public static Date getFirstDayOfThisMonthLastYear(Date date) {
		if (null == date) {
			logger.error("获取上年同月第一天的输入为空！");
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, -1);
		Date firstDayOfThisMonthLastYear = getFirstDayOfThisMonth(cal.getTime());
		return firstDayOfThisMonthLastYear;
	}

	public static Date getLastDayOfThisMonthLastYear(Date date) {
		if (null == date) {
			logger.error("获取上年同月最后一天的输入为空！");
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, -1);
		Date lastDayOfThisMonthLastYear = getLastDayOfThisMonth(cal.getTime());
		return lastDayOfThisMonthLastYear;
	}

}
