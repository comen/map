package cn.com.chinatelecom.map.handle;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.entity.Grid;
import cn.com.chinatelecom.map.utils.FileUtils;

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
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				String string = item.getString();
				result.put(fieldName, string);
			} else {
				String filename = item.getName().trim();
				int index = filename.lastIndexOf("//");
				filename = filename.substring(index + 1, filename.length());
				if (filename.equals("") || !filename.endsWith(".xls")) {
					result.put("info", "文件格式错误！");
					return result;
				}
				File file = new File(Config.getInstance()
						.getValue("uploadPath") + "/" + filename);

				try {
					FileUtils.writeFile(item.getInputStream(), file);
				} catch (Exception e) {
					result.put("info", e.getClass() + ":" + e.getMessage());
					return result;
				}

				String content = FileUtils.readFile(file);
				result.put("content", content);
				String[] splits = content.split("<br/>");
				Grid grid;
				for (String split : splits) {
					if (!split.trim().equals("")) {
						grid = new Grid(split);
						grid.insert();
					}
				}
				result.put("info", "文件上传成功！");

				// --------------------Test MongoDB Start--------------------
				grid = new Grid("{'GRID_CODE':'BQ-PS-SD-5045'}");
				System.out.println(grid.exist());
				grid.delete();
				System.out.println(grid.exist());

				grid = new Grid("{'GRID_CODE':'BQ-PS-SD-5043'}");
				System.out.println(Grid.findOne(grid.toString()));
				grid.update("{'GRID_CODE':'BQ-PS-SD-5043','GRID_NAME':'上海市体育运动学校','GRID_MANAGER':'钱惠平','GRID_ADDRESS':'上海市虹口区广中路406号','GRID_COORDINATES':[{'LATITUDE':31.25214,'LONGTITUDE':121.46386},{'LATITUDE':31.25313,'LONGTITUDE':121.46892}]}");
				System.out.println(Grid.findOne(grid.toString()));
				// --------------------Test MongoDB End--------------------
			}
		}
		return result;
	}

}
