package cn.com.chinatelecom.map.utils;

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
			System.out.println(e.getClass() + ":" + e.getMessage());
			return false;
		}
	}

}
