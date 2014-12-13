package cn.com.chinatelecom.map.handle;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.entity.Grid;
import cn.com.chinatelecom.map.entity.Record;

/**
 * @author joseph
 *
 */
public class ReshapeHandler implements IHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	@Override
	public Map<String, Object> handle(List<FileItem> items) {

		Map<String, Object> result = new HashMap<String, Object>();
		if (null == items) {
			logger.warn("没有请求数据!");
			return null;
		}

		String code = null;
		String coordinates = null;
		for (FileItem item : items) {
			if (item.isFormField()) {
				String name = item.getFieldName();
				String string = item.getString();
				switch (name) {
				case "GRID_CODE":
					code = string;
					break;
				case "GRID_COORDINATES":
					coordinates = string;
					break;
				default:
					break;
				}
			}
		}

		logger.info("编辑网格 " + code + ". 边界坐标: " + coordinates);

		Grid query = new Grid("{GRID_CODE:'" + code + "'}");
		Grid origin = Grid.findOne(query.toString());
		StringBuffer sb = new StringBuffer("{GRID_CODE:'" + code + "'");
		if (null != origin.getName())
			sb.append(",GRID_NAME:'" + origin.getName() + "'");
		if (null != origin.getManager())
			sb.append(",GRID_MANAGER:'" + origin.getManager() + "'");
		if (null != origin.getAddress())
			sb.append(",GRID_ADDRESS:'" + origin.getAddress() + "'");
		Record record = new Record(sb.toString() + "}");
		sb.append(",GRID_COORDINATES:" + coordinates + "}");
		Grid target = new Grid(sb.toString());
		if (origin.comparePolygon(target.getPolygon())) {
			result.put("result", "网格未编辑!");
		} else {
			record.setDate(new Date());
			record.insert();
			query.update(target.toString());
			result.put("result", "编辑网格成功!");
		}

		return result;
	}

}
