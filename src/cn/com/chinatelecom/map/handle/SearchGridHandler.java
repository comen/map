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
import cn.com.chinatelecom.map.entity.Grid;
import cn.com.chinatelecom.map.utils.StringUtils;

/**
 * @author Shelwin
 *
 */
public class SearchGridHandler implements IHandler {

	/* (non-Javadoc)
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		Grid grid = new Grid();
		String gridCode = "";
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				try {
					String string = item.getString(Config.getInstance().getValue("charset").toString());
					
					switch(fieldName) {
					case "gridcode":
						gridCode = string;
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
		
		if (!(gridCode == null || gridCode.equals(""))) {
			grid.setCode(gridCode);
		}
		
		List<Grid> gridList = Grid.findList(grid.toString());
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (gridList != null) {
			for (int i = 0; i < gridList.size(); i++) {
				/* Ignore GRID_CODE:0,1,2,3,4 */
				switch (gridList.get(i).getCode()) {
				case "0":
				case "1":
				case "2":
				case "3":
				case "4":
					continue;
				}
				
				/* Clear coordinates */
				gridList.get(i).setCoordinates(null);
				
				sb.append(gridList.get(i).toString());
				if (i < gridList.size()-1) {
					sb.append(",");
				}
			}
		}
		sb.append("]");
		
		result.put("SearchGridResult", sb.toString());
		return result;
	}
}
