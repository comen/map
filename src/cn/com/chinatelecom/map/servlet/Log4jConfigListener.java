package cn.com.chinatelecom.map.servlet;

import java.util.TimeZone;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.LogManager;
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
public final class Log4jConfigListener implements ServletContextListener {

	public static final String properties = "/conf.properties";

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
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		PropertyConfigurator.configure(getClass().getResourceAsStream(
				properties));
		Config.getInstance();
		GeoCoder.getInstance();
		MongoDB.getInstance();
		Repository.getInstance();
	}

}
