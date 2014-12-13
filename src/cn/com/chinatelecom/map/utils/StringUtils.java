package cn.com.chinatelecom.map.utils;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * @author joseph
 *
 */
public class StringUtils {

	private static Logger logger = Logger.getLogger(StringUtils.class);

	public static boolean isLegal(String name, String filter) {
		if (null == name || null == filter) {
			logger.error("文件名或正则表达式为空！");
			return false;
		}
		try {
			Pattern p = Pattern.compile(filter);
			Matcher m = p.matcher(name);
			if (!m.find())
				return false;
			return true;
		} catch (Exception e) {
			logger.error("匹配正则表达式错误: " + e.getMessage());
			return false;
		}
	}

	public static String getFileName(String path) {
		if (null == path) {
			logger.error("文件路径为空！");
			return null;
		}
		int index = path.lastIndexOf("/");
		if (0 < index)
			return path.substring(index);
		index = path.lastIndexOf("\\");
		if (0 < index)
			return path.substring(index);
		return path;
	}

	@Deprecated
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
		String color = "#FFFFFF";
		if (null == rgb || "".equals(rgb.trim()))
			return color;
		DBObject dbo = null;
		try {
			dbo = (DBObject) JSON.parse(rgb);
			double R = Integer.parseInt(dbo.get("R").toString());
			double G = Integer.parseInt(dbo.get("G").toString());
			if (0 == R + G)
				return color;
			int r = (int) (R * 15 / (R + G));
			int g = (int) (G * 15 / (R + G));
			while (10 > r && 10 > g) {
				r++;
				g++;
			}
			color = "#" + getHtmlColor(r) + getHtmlColor(g) + "00";
		} catch (Exception e) {
			logger.fatal("解析RGB组合错误: " + e.getMessage());
		}
		return color;
	}

	public static String getHtmlColor(int level) {
		if (0 > level) {
			logger.warn("小于0的颜色值自动重置为0: " + level);
			level = 0;
		}
		if (15 < level) {
			logger.warn("大于15的颜色值自动重置为15: " + level);
			level = 15;
		}
		String color = Integer.toHexString(level).toUpperCase();
		return color + color;
	}

}
