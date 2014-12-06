package cn.com.chinatelecom.map.common;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;

import cn.com.chinatelecom.map.utils.StringUtils;

/**
 * @author joseph
 *
 */
public class Config {

	private static Config instance;
	private Properties properties;

	private Config() {
		try {
			properties = new Properties();
			properties.load(getClass().getResourceAsStream("/conf.properties"));
		} catch (Exception e) {
			String log = StringUtils.getLogPrefix(Level.SEVERE);
			System.out.println("\n" + log + "\n" + e.getClass() + "\t:\t"
					+ e.getMessage());
		}
	}

	public static Config getInstance() {
		if (null == instance)
			instance = new Config();
		return instance;
	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}

	public void setValue(String key, String value) {
		try {
			properties.setProperty(key, value);
			String uri = "C:/eclipse-workspace/Map/src/conf.properties";
			properties.store(new FileOutputStream(new File(uri)), null);
		} catch (Exception e) {
			String log = StringUtils.getLogPrefix(Level.SEVERE);
			System.out.println("\n" + log + "\n" + e.getClass() + "\t:\t"
					+ e.getMessage());
		}
	}

	public String toString() {
		return "Config [properties=" + properties + "]";
	}

}
