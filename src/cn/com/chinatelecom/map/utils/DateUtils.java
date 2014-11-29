/**
 * 
 */
package cn.com.chinatelecom.map.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Shelwin
 *
 */
public class DateUtils {
	
	public static String getCurrentDate() {
		
		TimeZone shZone = TimeZone.getTimeZone("Asia/Shanghai");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(shZone);
		
		Date currentDate = new Date();
		String dateStr = format.format(currentDate);
		
		return dateStr;
		
	}
	
}
