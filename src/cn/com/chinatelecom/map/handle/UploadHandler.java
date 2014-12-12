package cn.com.chinatelecom.map.handle;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.entity.Record;
import cn.com.chinatelecom.map.utils.FileUtils;
import cn.com.chinatelecom.map.utils.MathUtils;
import cn.com.chinatelecom.map.utils.StringUtils;

/**
 * @author joseph
 *
 */
public class UploadHandler implements IHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (items == null) {
			logger.warn("没有请求数据!");
			return null;
		}
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				String string = item.getString();
				result.put(fieldName, string);
			} else {
				String filename = item.getName().trim();
				filename = StringUtils.getFileName(filename);
				if (!StringUtils.isLegal(filename, ".xls$")) {
					logger.error("文件格式有误: " + filename);
					return result;
				}
				File file = new File(Config.getInstance()
						.getValue("uploadPath") + filename);

				try {
					FileUtils.writeFile(item.getInputStream(), file);
				} catch (Exception e) {
					logger.fatal("上传文件发生致命错误: " + e.getMessage());
					return result;
				}

				List<String> content = FileUtils.readFile(file);
				if (null == content) {
					logger.error("读取文件失败: " + filename);
					return result;
				}

				StringBuffer sb = new StringBuffer();
				int size = MathUtils.getThreadSize(content.size());
				ExecutorService threadPool = Executors.newFixedThreadPool(size);
				for (String line : content) {
					if (!line.trim().equals("")) {
						Record record = new Record(line);
						threadPool.execute(record);
						sb.append(record + "<br/>");
					}
				}
				result.put("content", sb.toString());
				file.delete();
				logger.info("文件上传成功: " + filename);
			}
		}
		return result;
	}

}
