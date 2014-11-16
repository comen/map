package cn.com.chinatelecom.map.common;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

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
			System.out.println(e.getClass() + "\t:\t" + e.getMessage());
		}
	}

	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
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
			System.out.println(e.getClass() + "\t:\t" + e.getMessage());
		}
	}

	public String toString() {
		return "Config [properties=" + properties + "]";
	}

}
