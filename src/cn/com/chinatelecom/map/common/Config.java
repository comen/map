package cn.com.chinatelecom.map.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author joseph
 *
 */
public class Config {

	private static Config instance;
	private Properties properties;
	private Logger logger = Logger.getLogger(Config.class);

	private Config() {
		try {
			properties = new Properties();
			File file = new File("C:/eclipse-workspace/Map/src/conf.properties");
			FileInputStream fis = new FileInputStream(file);
			properties.load(fis);
		} catch (Exception e) {
			logger.fatal("读取配置文件错误: " + e.getMessage());
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
		if (null == key || null == value) {
			logger.error("修改配置文件输入参数不能为空！");
			return;
		}
		try {
			properties.setProperty(key, value);
			String uri = "C:/eclipse-workspace/Map/src/conf.properties";
			FileOutputStream fos = new FileOutputStream(new File(uri));
			properties.store(fos, null);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			logger.fatal("修改配置文件(" + key + "-->" + value + ")错误: "
					+ e.getMessage());
		}
	}

	public String toString() {
		return "Config [properties=" + properties + "]";
	}

}
