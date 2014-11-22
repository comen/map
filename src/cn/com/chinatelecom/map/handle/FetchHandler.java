package cn.com.chinatelecom.map.handle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.entity.Coordinate;
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
			if (item.isFormField() && item.getFieldName().equals("grid_code")) {
				String code = item.getString();
				Grid grid = new Grid("{'GRID_CODE':'" + code + "'}");
				grid = Grid.findOne(grid.toString());
				List<Coordinate> coordinates = grid.getCoordinates();
				int size = coordinates.size();
				double[] latitudes = new double[size];
				double[] longtitudes = new double[size];
				for (int i = 0; i < size; i++) {
					latitudes[i] = coordinates.get(i).getLatitude();
					longtitudes[i] = coordinates.get(i).getLongtitude();
				}
				result.put("latitudes", latitudes);
				result.put("longtitudes", longtitudes);
			}
		}
		return result;
	}

}
