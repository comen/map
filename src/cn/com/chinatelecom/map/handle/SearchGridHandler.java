/**
 * 
 */
package cn.com.chinatelecom.map.handle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	
	class SortByGridCodeAsc implements Comparator<Grid> {
		public int compare(Grid g1, Grid g2) {
			return g1.getCode().compareTo(g2.getCode());
		}
	}
	
	class SortByGridCodeDsc implements Comparator<Grid> {
		public int compare(Grid g1, Grid g2) {
			return g2.getCode().compareTo(g1.getCode());
		}
	}
	
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		List<Grid> gridList = new ArrayList<Grid>();
		StringBuffer sb = new StringBuffer();
		
		Boolean advSearch = false;
		/* For Grid Normal Search */
		String gridCode = "";
		/* For User Advanced Search */
		String advGridCode = "";
		String advGridName = "";
		String advGridManager = "";
		String advGridAddress = "";
		String advSort = "";
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				try {
					String string = item.getString(Config.getInstance().getValue("charset").toString());
					
					switch(fieldName) {
					case "advsearch":
						advSearch = Boolean.parseBoolean(string);
						break;
					case "adv_sort":
						advSort = string;
						break;
					/* For User Normal Search */
					case "gridcode":
						gridCode = string;
						break;
					/* For User Advanced Search */
					case "adv_gridcode":
						advGridCode = string;
						break;
					case "adv_gridname":
						advGridName = string;
						break;
					case "adv_gridmanager":
						advGridManager = string;
						break;
					case "adv_gridaddress":
						advGridAddress = string;
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
		
		if (advSearch == false) {
			Grid grid = new Grid();
			if (!(gridCode == null || gridCode.equals(""))) {
				grid.setCode(gridCode);
			}
			gridList = Grid.findList(grid.getBasicDBObject().toString());
		} else {
			List<Grid> gridListTmp = Grid.findList(null); // Search all grids
			if (gridListTmp != null) {
				for (Grid grid : gridListTmp) {
					if (!(advGridCode == null || advGridCode.equals(""))) {
						if (!(grid.getCode() != null && grid.getCode().contains(advGridCode))) {
							continue;
						}
					}
					if (!(advGridName == null || advGridName.equals(""))) {
						if (!(grid.getName() != null && grid.getName().contains(advGridName))) {
							continue;
						}
					}
					if (!(advGridManager == null || advGridManager.equals(""))) {
						if (!(grid.getManager() != null && grid.getManager().contains(advGridManager))) {
							continue;
						}
					}
					if (!(advGridAddress == null || advGridAddress.equals(""))) {
						if (!(grid.getAddress() != null && grid.getAddress().contains(advGridAddress))) {
							continue;
						}
					}
					gridList.add(grid);
				}
			}
		}
		
		/* Sort Grid List */
		switch (advSort) {
		case "g_asending":
			Collections.sort(gridList, new SortByGridCodeAsc());
			break;
		case "g_descending":
			Collections.sort(gridList, new SortByGridCodeDsc());
			break;
		default:
			Collections.sort(gridList, new SortByGridCodeAsc());
		}
		
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
