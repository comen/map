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
public class InfoHandler implements IHandler {

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

		int zoom = 0;
		double longtitude = 0.0;
		double latitude = 0.0;
		for (FileItem item : items) {
			if (item.isFormField()) {
				String name = item.getFieldName();
				String string = item.getString();
				switch (name) {
				case "zoom":
					zoom = Integer.parseInt(string);
					break;
				case "longtitude":
					longtitude = Double.parseDouble(string);
					break;
				case "latitude":
					latitude = Double.parseDouble(string);
					break;
				default:
					break;
				}
			}
		}

		Coordinate coordinate = new Coordinate(latitude, longtitude);
		Grid grid = null;
		switch (zoom) {
		case 12:
		case 13:
			grid = new Grid("{GRID_CODE:'0'}");
			grid = Grid.findOne(grid.toString());
			if (grid.contains(coordinate)) {
				result.put("grid", grid.toInfo());
			}
			break;
		case 14:
		case 15:
			for (int i = 1; i <= 4; i++) {
				grid = new Grid("{GRID_CODE:'" + i + "'}");
				grid = Grid.findOne(grid.toString());
				if (null != grid && grid.contains(coordinate)) {
					result.put("grid", grid.toInfo());
					break;
				}
			}
			break;
		case 16:
		case 17:
		case 18:
		case 19:
			List<Grid> grids = Grid.findList(null);
			for (Grid g : grids) {
				if (g.contains(coordinate) && 1 != g.getCode().length()) {
					result.put("grid", g.toInfo());
					break;
				}
			}
			break;
		default:
			break;
		}

		return result;
	}

}
