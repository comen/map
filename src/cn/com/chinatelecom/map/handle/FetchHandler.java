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

		// Record record = new Record(
		// "{GRID_CODE:'BQ-GQ-GQ-3093',GRID_NAME:'歌林春天小区',GRID_MANAGER:'王瑞德',GRID_ADDRESS:'万容路388弄8号',GRID_ERASE_DATETIME:'2014-11-08 00:35:00'}");
		// logger.debug(record.toString());
		// record.insert();

		// List<Grid> exports = Grid.findList(null);
		// for (Grid export : exports) {
		// logger.error(export.toString());
		// }

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

		StringBuffer sb = new StringBuffer();
		String color = null;
		int amount = 0;
		switch (zoom) {
		// 北区局级别12-13
		case 12:
		case 13:
			Grid grid = Grid.findOne("{'GRID_CODE':'0'}");
			if (null != grid) {
				amount++;
				sb.append("[" + grid.toFetch(color) + "]");
			}
			break;
		// 分局级别14-15
		case 14:
		case 15:
			sb = new StringBuffer("[");
			for (int i = 1; i != 5; i++) {
				grid = Grid.findOne("{'GRID_CODE':'" + i + "'}");
				if (null != grid) {
					amount++;
					sb.append(grid.toFetch(color) + ",");
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
			for (int i = 0, size = grids.size(); i != size; i++) {
				grid = grids.get(i);
				if (1 != grid.getCode().length()
						&& MathUtils.randomTrue(19 - zoom)
						&& bounds.contains(grid)) {
					amount++;
					String rgb = Data.getFieldSpecialDisplay(specific,
							grid.getCode(), mode);
					color = StringUtils.getColor(rgb);
					sb.append(grid.toFetch(color) + ",");
				}
			}
			if (',' == sb.charAt(sb.length() - 1))
				sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			break;
		default:
			break;
		}
		result.put("grids", sb.toString());

		logger.info("网格数量: " + amount);
		logger.debug(result.get("grids"));

		return result;
	}

}
