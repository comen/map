package cn.com.chinatelecom.map.handle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.entity.Grid;

/**
 * @author joseph
 *
 */
public class FetchHandler implements IHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (items == null) {
			return null;
		}
		for (FileItem item : items) {
			if (item.isFormField() && item.getFieldName().equals("zoom")) {
				int zoom = Integer.parseInt(item.getString());
				if (zoom > 10 && zoom < 14) {
					Grid grid = new Grid("{'GRID_CODE':'0'}");
					grid = Grid.findOne(grid.toString());
					if (grid != null) {
						result.put("grids", "[" + grid.toString() + "]");
					}
				}
			}
		}
		return result;
	}

}
