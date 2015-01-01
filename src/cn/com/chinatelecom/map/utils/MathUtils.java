package cn.com.chinatelecom.map.utils;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author joseph
 *
 */
public class MathUtils {

	public static double getTitude(double number, int decimal) {
		String format = "0.";
		for (int i = 0; i != decimal; i++)
			format += '0';
		DecimalFormat df = new DecimalFormat(format);
		return Double.parseDouble(df.format(number));
	}

	public static boolean randomTrue(int chance) {
		if (2 > chance) {
			return true;
		}
		Random random = new Random();
		int number = random.nextInt();
		if (number % chance == 0)
			return true;
		else
			return false;
	}

	public static int getThreadSize(int tasks) {
		int cpu = Runtime.getRuntime().availableProcessors();
		int size = Math.floorDiv(cpu * tasks, 10) + 1;
		return size;
	}

	public static double calcuGrowthRate(int near, int far) {
		if (far == 0) {
			return 0.00;
		}
		double nearInDouble = (double) near;
		double farInDouble = (double) far;
		double rate = (nearInDouble - farInDouble) / farInDouble;
		return rate;
	}
	
	public static int calcuGrowth(int near, int far) {
		int growth = near - far;
		return growth;
	}

}
