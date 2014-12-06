package cn.com.chinatelecom.map.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import cn.com.chinatelecom.map.utils.StringUtils;

/**
 * @author joseph
 *
 */
public class GeoCoder {

	private static GeoCoder instance;
	private String url;
	private Map<String, String> parameters;

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
		if (null == key || null == parameters || parameters.isEmpty())
			return null;
		return parameters.get(key);
	}

	public void setParameter(String key, String value) {
		if (null != key)
			parameters.replace(key, value);
	}

	public void putParameter(String key, String value) {
		if (null != key)
			parameters.put(key, value);
	}

	public void removeParameter(String key) {
		if (null != key)
			parameters.remove(key);
	}

	public String geoCode(String address) {
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
			String log = StringUtils.getLogPrefix(Level.SEVERE);
			System.out.println("\n" + log + "\n" + e.getClass() + "\t:\t"
					+ e.getMessage());
			return null;
		}
	}

	public String toString() {
		if (null == parameters)
			return url;

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
