package cn.com.chinatelecom.map.servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.common.GeoCoder;
import cn.com.chinatelecom.map.common.MongoDB;
import cn.com.chinatelecom.map.common.Repository;

/**
 * Application Lifecycle Listener implementation class Log4jConfigListener
 *
 */
@WebListener
public final class ConfigListener implements ServletContextListener {

	public final String properties = "/conf.properties";
	public final Logger logger = Logger.getLogger(ConfigListener.class);

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		LogManager.shutdown();
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		TimeZone utc =TimeZone.getTimeZone("UTC");
		TimeZone.setDefault(utc);
		try {
			PropertyConfigurator.configure(getClass().getResourceAsStream(
					properties));
			Config.getInstance();
			GeoCoder.getInstance();
			Repository.getInstance();
			MongoDB.getInstance();
			/* Set index for Record */
			List<String> recordIndexes = new ArrayList<String>();
			recordIndexes.add("GRID_CODE");
			recordIndexes.add("GRID_DATETIME");
			MongoDB.getInstance().indexOn("record", recordIndexes);
			/* Set index for Data */
			List<String> dataIndexes = new ArrayList<String>();
			dataIndexes.add("calculatedDate");
			dataIndexes.add("gridCode");
			MongoDB.getInstance().indexOn("data", dataIndexes);
			/* Set index for Grid */
			MongoDB.getInstance().indexOn("grid", "GRID_CODE");
			/* Set index for User */
			MongoDB.getInstance().indexOn("user", "username");
		} catch (Exception e) {
			logger.fatal("初始化环境配置错误: " + e.getMessage());
		}
	}

}
