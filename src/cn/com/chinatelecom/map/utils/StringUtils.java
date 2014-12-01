package cn.com.chinatelecom.map.utils;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author joseph
 *
 */
public class StringUtils {

	public static boolean isLegal(String name, String filter) {
		try {
			if (name == null || filter == null) {
				return false;
			}
			Pattern p = Pattern.compile(filter);
			Matcher m = p.matcher(name);
			if (!m.find()) {
				return false;
			}
			return true;
		} catch (Exception e) {
			String log = getLogPrefix(Level.SEVERE);
			System.out.println("\n" + log + "\n" + e.getClass() + "\t:\t"
					+ e.getMessage());
			return false;
		}
	}

	public static String getFileName(String path) {
		if (path == null)
			return null;
		int index = path.lastIndexOf("/");
		if (index > 0)
			return path.substring(index);
		index = path.lastIndexOf("\\");
		if (index > 0)
			return path.substring(index);
		return null;
	}

	public static String getLogPrefix(Level level) {
		StringBuffer sb = new StringBuffer();
		sb.append("[" + level + "]");
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.MEDIUM, Locale.CHINA);
		Date date = new Date();
		sb.append("[" + df.format(date) + "]");
		StackTraceElement ste = new Throwable().getStackTrace()[1];
		sb.append("[" + ste.toString() + "]");
		return sb.toString();
	}

}
