package cn.com.chinatelecom.map.utils;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * @author joseph
 *
 */
public class StringUtils {

	public static boolean isLegal(String name, String filter) {
		try {
			if (null == name || null == filter)
				return false;
			Pattern p = Pattern.compile(filter);
			Matcher m = p.matcher(name);
			if (!m.find())
				return false;
			return true;
		} catch (Exception e) {
			String log = getLogPrefix(Level.SEVERE);
			System.out.println("\n" + log + "\n" + e.getClass() + "\t:\t"
					+ e.getMessage());
			return false;
		}
	}

	public static String getFileName(String path) {
		if (null == path)
			return null;
		int index = path.lastIndexOf("/");
		if (0 < index)
			return path.substring(index);
		index = path.lastIndexOf("\\");
		if (0 < index)
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

	public static String getColor(String rgb) {
		if (rgb == null)
			return "#FFFFFF";
		String color = "#FFFFFF";
		DBObject dbo = null;
		try {
			dbo = (DBObject) JSON.parse(rgb);
			double R = Integer.parseInt(dbo.get("R").toString());
			double G = Integer.parseInt(dbo.get("G").toString());
			int r = (int) (R * 15 / (R + G));
			int g = (int) (G * 15 / (R + G));
			color = "#" + getHtmlColor(r) + getHtmlColor(g) + "00";
		} catch (Exception e) {
			String log = getLogPrefix(Level.SEVERE);
			System.out.println("\n" + log + "\n" + e.getClass() + "\t:\t"
					+ e.getMessage());
		}
		return color;
	}

	public static String getHtmlColor(int level) {
		String color = "";
		if (-1 < level && level < 10)
			color += level + level;
		else {
			switch (level) {
			case 10:
				color = "AA";
				break;
			case 11:
				color = "BB";
				break;
			case 12:
				color = "CC";
				break;
			case 13:
				color = "DD";
				break;
			case 14:
				color = "EE";
				break;
			default:
				color = "FF";
				break;
			}
		}
		return color;
	}

}
