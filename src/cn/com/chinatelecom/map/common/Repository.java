package cn.com.chinatelecom.map.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

/**
 * @author joseph
 *
 */
public class Repository {

	private static Repository instance;
	private ServletFileUpload sfu;
	private Logger logger = Logger.getLogger(Repository.class);

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
		if (null == request) {
			logger.error("待解析HTTP请求为空！");
			return null;
		}
		try {
			return sfu.parseRequest(request);
		} catch (Exception e) {
			logger.fatal("解析HTTP请求失败: " + e.getMessage());
		}
		return null;
	}

	@Override
	public String toString() {
		return "Repository [sfu=" + sfu + "]";
	}

}
