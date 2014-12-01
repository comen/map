package cn.com.chinatelecom.map.handle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.entity.Coordinate;
import cn.com.chinatelecom.map.entity.Grid;
import cn.com.chinatelecom.map.utils.StringUtils;

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
			String log = StringUtils.getLogPrefix(Level.WARNING);
			System.out.println("\n" + log + "\nThere is no request item!");
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

		String log = StringUtils.getLogPrefix(Level.INFO);
		System.out.println("\n" + log + "\nGetting info on zoom level of "
				+ zoom + "...");
		System.out.println("Longtitude: " + longtitude);
		System.out.println("Latitude: " + latitude);

		Coordinate coordinate = new Coordinate(latitude, longtitude);
		Grid grid = null;
		switch (zoom) {
		case 12:
		case 13:
			grid = new Grid("{GRID_CODE:'0'}");
			grid = Grid.findOne(grid.toString());
			if (grid.contains(coordinate)) {
				System.out.println("Grid: " + grid.getCode() + ","
						+ grid.getName());
				result.put("grid", grid.toInfo());
			}
			break;
		case 14:
		case 15:
			for (int i = 1; i <= 4; i++) {
				grid = new Grid("{GRID_CODE:'" + i + "'}");
				grid = Grid.findOne(grid.toString());
				if (null != grid && grid.contains(coordinate)) {
					System.out.println("Grid: " + grid.getCode() + ","
							+ grid.getName());
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
					System.out.println("Grid: " + g.getCode() + ","
							+ g.getName());
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
