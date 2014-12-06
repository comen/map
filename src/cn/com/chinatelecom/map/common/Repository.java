package cn.com.chinatelecom.map.common;

import java.util.List;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.com.chinatelecom.map.utils.StringUtils;

/**
 * @author joseph
 *
 */
public class Repository {

	private static Repository instance;
	private ServletFileUpload sfu;

	private Repository() {
		Config config = Config.getInstance();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(Integer.parseInt(config.getValue("cacheSize")));
		sfu = new ServletFileUpload(factory);
		sfu.setSizeMax((int) Math.pow(
				Integer.parseInt(config.getValue("cacheSize")), 2));
		sfu.setHeaderEncoding(config.getValue("charset"));
	}

	public static Repository getInstance() {
		if (null == instance)
			instance = new Repository();
		return instance;
	}

	public List<FileItem> parse(HttpServletRequest request) {
		try {
			return sfu.parseRequest(request);
		} catch (Exception e) {
			String log = StringUtils.getLogPrefix(Level.SEVERE);
			System.out.println("\n" + log + "\n" + e.getClass() + "\t:\t"
					+ e.getMessage());
		}
		return null;
	}

	@Override
	public String toString() {
		return "Repository [sfu=" + sfu + "]";
	}

}
