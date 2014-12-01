package cn.com.chinatelecom.map.utils;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author joseph
 *
 */
public class MathUtils {

	public static double getTitude(double number, double decimal) {
		String format = "0.";
		for (int i = 0; i < decimal; i++) {
			format += '0';
		}
		DecimalFormat df = new DecimalFormat(format);
		return Double.parseDouble(df.format(number));
	}

	public static boolean randomTrue(int chance) {
		if (chance <= 1)
			return true;

		Random random = new Random();
		int number = random.nextInt();
		if (number % chance == 0)
			return true;
		else
			return false;
	}

}
