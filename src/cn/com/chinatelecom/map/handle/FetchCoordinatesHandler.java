/**
 * 
 */
package cn.com.chinatelecom.map.handle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.entity.Coordinate;
import cn.com.chinatelecom.map.entity.Grid;
import cn.com.chinatelecom.map.utils.MathUtils;
import cn.com.chinatelecom.map.utils.StringUtils;

/**
 * @author Shelwin
 *
 */
public class FetchCoordinatesHandler implements IHandler {

	/* (non-Javadoc)
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		String address = "";
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				try {
					String string = item.getString(Config.getInstance().getValue("charset").toString());
					
					switch(fieldName) {
					case "address":
						address = string;
						break;
					}
				} catch (java.io.UnsupportedEncodingException e) {
					@SuppressWarnings("deprecation")
					String log = StringUtils.getLogPrefix(Level.SEVERE);
					System.out.println("\n" + log + "\n" + e.getClass()
							+ "\t:\t" + e.getMessage());
				}
			}
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (!(address == null || address.equals(""))) {
			if (!(address.startsWith("上海"))) {
				address = "上海市" + address;
			}
			Coordinate coordinate = Grid.getCoordinate(address);
			sb.append("{\"longitude\":");
			sb.append(MathUtils.getTitude(coordinate.getLongitude(), 5));
			sb.append(",\"latitude\":");
			sb.append(MathUtils.getTitude(coordinate.getLatitude(), 5));
			sb.append("}");
		}
		sb.append("]");
		
		result.put("FetchCoordinatesResult", sb.toString());
		return result;
	}
}
