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
				StringBuffer sb = new StringBuffer();
				switch (zoom) {
				// 北区局级别12-13
				case 12:
				case 13:
					Grid grid = new Grid("{'GRID_CODE':'0'}");
					grid = Grid.findOne(grid.toString());
					if (grid != null) {
						sb.append("[" + grid.toString() + "]");
					}
					break;
				// 分局级别14-15
				case 14:
				case 15:
					sb = new StringBuffer("[");
					for (int i = 1; i <= 4; i++, sb.append(",")) {
						grid = new Grid("{'GRID_CODE':'" + i + "'}");
						grid = Grid.findOne(grid.toString());
						if (null != grid) {
							sb.append(grid.toString());
						}
					}
					sb.append("]");
					break;
				// 网格级别16-19
				case 16:
				case 17:
				case 18:
				case 19:
					List<Grid> grids = Grid.findList(null);
					sb = new StringBuffer("[");
					for (int i = 0; i < grids.size(); i++, sb.append(",")) {
						if (1 != grids.get(i).getCode().length()) {
							sb.append(grids.get(i).toString());
						}
					}
					sb.append("]");
					break;
				default:
					break;
				}
				result.put("grids", sb.toString());
			}
		}
		return result;
	}

}
