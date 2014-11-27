package cn.com.chinatelecom.map.handle;

import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

/**
 * @author joseph
 *
 */
public interface IHandler {

	public Map<String, Object> handle(List<FileItem> items);
//	public Object handle(Object object);

}
