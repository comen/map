package cn.com.chinatelecom.map.handle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.entity.Grid;
import cn.com.chinatelecom.map.utils.MathUtils;
import cn.com.chinatelecom.map.utils.StringUtils;

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
			String log = StringUtils.getLogPrefix(Level.WARNING);
			System.out.println("\n" + log + "\nThere is no request item!");
			return null;
		}

		int zoom = 0;
		double swlng = 0.0;
		double swlat = 0.0;
		double nelng = 0.0;
		double nelat = 0.0;
		for (FileItem item : items) {
			if (item.isFormField()) {
				String name = item.getFieldName();
				String string = item.getString();
				switch (name) {
				case "zoom":
					zoom = Integer.parseInt(string);
					break;
				case "swlng":
					swlng = Double.parseDouble(string);
					break;
				case "swlat":
					swlat = Double.parseDouble(string);
					break;
				case "nelng":
					nelng = Double.parseDouble(string);
					break;
				case "nelat":
					nelat = Double.parseDouble(string);
					break;
				default:
					break;
				}
			}
		}

		String json = "{GRID_CODE: '-1', GRID_COORDINATES:[{LONGTITUDE:"
				+ swlng + ",LATITUDE:" + nelat + "},{LONGTITUDE:" + swlng
				+ ",LATITUDE:" + swlat + "},{LONGTITUDE:" + nelng
				+ ",LATITUDE:" + swlat + "},{LONGTITUDE:" + nelng
				+ ",LATITUDE:" + nelat + "}]}";
		Grid bounds = new Grid(json);

		String log = StringUtils.getLogPrefix(Level.INFO);
		System.out.println("\n" + log + "\nFetching grids on zoom level of "
				+ zoom + "...");

		StringBuffer sb = new StringBuffer();

		int amount = 0;
		switch (zoom) {
		// 北区局级别12-13
		case 12:
		case 13:
			Grid grid = new Grid("{'GRID_CODE':'0'}");
			grid = Grid.findOne(grid.toString());
			if (grid != null) {
				amount++;
				sb.append("[" + grid.toFetch() + "]");
			}
			break;
		// 分局级别14-15
		case 14:
		case 15:
			sb = new StringBuffer("[");
			for (int i = 1; i <= 4; i++) {
				grid = new Grid("{'GRID_CODE':'" + i + "'}");
				grid = Grid.findOne(grid.toString());
				if (null != grid) {
					amount++;
					sb.append(grid.toFetch() + ",");
				}
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			break;
		// 网格级别16-19
		case 16:
		case 17:
		case 18:
		case 19:
			List<Grid> grids = Grid.findList(null);
			sb = new StringBuffer("[");
			for (int i = 0; i < grids.size(); i++) {
				grid = grids.get(i);
				if (1 != grid.getCode().length()
						&& MathUtils.randomTrue(19 - zoom)
						&& (grid.getCoordinates() == null || bounds
								.contains(grids.get(i)))) {
					amount++;
					sb.append(grid.toFetch() + ",");
				}
			}
			if (sb.charAt(sb.length() - 1) == ',') {
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append("]");
			break;
		default:
			break;
		}
		result.put("grids", sb.toString());

		System.out.println("Grids amount:" + amount);
		System.out.println(result.get("grids"));

		return result;
	}

}
