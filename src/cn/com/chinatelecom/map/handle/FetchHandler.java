package cn.com.chinatelecom.map.handle;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.entity.Data;
import cn.com.chinatelecom.map.entity.Grid;
import cn.com.chinatelecom.map.utils.DateUtils;
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
		if (null == items) {
			logger.warn("没有请求数据!");
			return null;
		}

		int zoom = 0;
		String mode = null;
		String date = null;
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
				case "mode":
					mode = string;
					break;
				case "date":
					date = string;
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

		String format = "MM/dd/yyyy";
		Date specific = DateUtils.getSpecificDate(date, format);
		logger.info("抓取数据在缩放级别: " + zoom + ". 模式: " + mode + ". 日期: "
				+ specific);

		String json = "{GRID_CODE: '-1', GRID_COORDINATES:[{LONGITUDE:" + swlng
				+ ",LATITUDE:" + nelat + "},{LONGITUDE:" + swlng + ",LATITUDE:"
				+ swlat + "},{LONGITUDE:" + nelng + ",LATITUDE:" + swlat
				+ "},{LONGITUDE:" + nelng + ",LATITUDE:" + nelat + "}]}";
		Grid bounds = new Grid(json);

		StringBuffer sb = new StringBuffer("[");
		String color = null;
		int amount = 0;
		List<Grid> grids = Grid.findList(null);
		switch (zoom) {
		// 北区局级别12-13
		case 12:
		case 13:
			for (Grid grid : grids) {
				String code = grid.getCode();
				switch (code) {
				case "0":
					amount++;
					sb.append(grid.toFetch(color) + ",");
					break;
				case "1":
				case "2":
				case "3":
				case "4":
					break;
				default:
					if (!bounds.contains(grid))
						break;
					String rgb = Data.getFieldSpecialDisplay(specific, code,
							mode);
					color = StringUtils.getColor(rgb);
					if ("#FFFFFF".equals(color))
						break;
					amount++;
					sb.append(grid.toFetch(color) + ",");
				}
			}
			break;
		// 分局级别14-15
		case 14:
		case 15:
			for (Grid grid : grids) {
				String code = grid.getCode();
				switch (code) {
				case "0":
					break;
				case "1":
				case "2":
				case "3":
				case "4":
					amount++;
					sb.append(grid.toFetch(color) + ",");
					break;
				default:
					if (!bounds.contains(grid))
						break;
					String rgb = Data.getFieldSpecialDisplay(specific, code,
							mode);
					color = StringUtils.getColor(rgb);
					if ("#FFFFFF".equals(color))
						break;
					amount++;
					sb.append(grid.toFetch(color) + ",");
				}
			}
			break;
		// 网格级别16-19
		case 16:
		case 17:
		case 18:
		case 19:
			for (Grid grid : grids) {
				String code = grid.getCode();
				switch (code) {
				case "0":
				case "1":
				case "2":
				case "3":
				case "4":
					break;
				default:
					if (!bounds.contains(grid))
						break;
					String rgb = Data.getFieldSpecialDisplay(specific, code,
							mode);
					color = StringUtils.getColor(rgb);
					if ("#FFFFFF".equals(color)
							&& !MathUtils.randomTrue(18 - zoom))
						break;
					amount++;
					sb.append(grid.toFetch(color) + ",");
				}
			}
			break;
		default:
			break;
		}
		if (',' == sb.charAt(sb.length() - 1))
			sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		result.put("grids", sb.toString());
		logger.info("网格数量: " + amount);
		return result;
	}

}
