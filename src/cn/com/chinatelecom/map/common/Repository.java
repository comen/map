package cn.com.chinatelecom.map.common;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
		factory.setRepository(new File(config.getValue("tempPath")));

		sfu = new ServletFileUpload(factory);
		sfu.setSizeMax((int) Math.pow(
				Integer.parseInt(config.getValue("cacheSize")), 2));
		sfu.setHeaderEncoding(config.getValue("charset"));
	}

	public static Repository getInstance() {
		if (instance == null) {
			instance = new Repository();
		}
		return instance;
	}

	public List<FileItem> parse(HttpServletRequest request) {
		try {
			return sfu.parseRequest(request);
		} catch (Exception e) {
			System.out.println(e.getClass() + "\t:\t" + e.getMessage());
		}
		return null;
	}

	@Override
	public String toString() {
		return "Repository [sfu=" + sfu + "]";
	}

}
