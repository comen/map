package cn.com.chinatelecom.map.handle;

import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

/**
 * @author joseph
 *
 */
public interface IHandler {
	
	Logger logger = Logger.getLogger(IHandler.class);

	public Map<String, Object> handle(List<FileItem> items);

}
