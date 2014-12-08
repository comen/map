package cn.com.chinatelecom.map.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

/**
 * @author joseph
 *
 */
public class GeoCoder {

	private static GeoCoder instance;
	private String url;
	private Map<String, String> parameters;
	private Logger logger = Logger.getLogger(GeoCoder.class);

	private GeoCoder() {
		url = Config.getInstance().getValue("geocoder");
		parameters = new HashMap<String, String>();
		parameters.put("output", "json");
		parameters.put("ak", Config.getInstance().getValue("ak"));
	}

	public static GeoCoder getInstance() {
		if (null == instance)
			instance = new GeoCoder();
		return instance;
	}

	public String getParameter(String key) {
		if (null == key || null == parameters || parameters.isEmpty()) {
			logger.error("要获取的geocoder参数为空！");
			return null;
		}
		return parameters.get(key);
	}

	public void setParameter(String key, String value) {
		if (null != key)
			parameters.replace(key, value);
		else
			logger.error("geocoder参数的键不能为空！");
	}

	public void putParameter(String key, String value) {
		if (null != key)
			parameters.put(key, value);
		else
			logger.error("geocoder参数的键不能为空！");
	}

	public void removeParameter(String key) {
		if (null != key)
			parameters.remove(key);
		else
			logger.error("geocoder参数的键不能为空！");
	}

	public String geoCode(String address) {
		if (null == address) {
			logger.warn("请求geocoder的地址为空！");
			return null;
		}
		try {
			address = URLEncoder.encode(address,
					Config.getInstance().getValue("charset"));
			parameters.put("address", address);
			URL url = new URL(toString());
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream(), Config.getInstance().getValue("charset")));
			StringBuffer sb = new StringBuffer();
			String line;
			while (null != (line = br.readLine())) {
				sb.append(line.trim());
			}
			return sb.toString();
		} catch (Exception e) {
			logger.fatal("请求geocoder发生错误: " + e.getMessage());
			return null;
		}
	}

	public String toString() {
		if (null == parameters) {
			logger.warn("geocoder请求参数为空！");
			return url;
		}
		StringBuffer sb = new StringBuffer(url);
		int index = 0;
		for (Entry<String, String> es : parameters.entrySet()) {
			if (0 == index++)
				sb.append("?");
			else
				sb.append("&");
			sb.append(es.getKey() + "=" + es.getValue());
		}
		return sb.toString();
	}
}
