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
public class EditGridHandler implements IHandler {

	/* (non-Javadoc)
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		Grid grid = new Grid();
		String originalGridCode = "";
		String gridCode = "";
		String gridName = "";
		String gridManager = "";
		String gridAddress = "";
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				try {
					String string = item.getString(Config.getInstance().getValue("charset").toString());
					switch(fieldName) {
					case "original_grid_code":
						originalGridCode = string;
						break;
					case "grid_code":
						gridCode = string;
						break;
					case "grid_name":
						gridName = string;
						break;
					case "grid_manager":
						gridManager = string;
						break;
					case "grid_address":
						gridAddress = string;
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
		
		/* Check if grid code is changed */
		Boolean gridCodeChanged = false;
		if (originalGridCode.equals(gridCode)) { // No change
			gridCodeChanged = false;
		} else {
			gridCodeChanged = true;
		}
		
		if (gridCodeChanged) {
			grid.setCode(originalGridCode);
			if(grid.exist()) {
				Grid gridTmp = Grid.findOne(grid.toString());
				gridTmp.setCode(gridCode);
				gridTmp.setName(gridName);
				gridTmp.setManager(gridManager);
				gridTmp.setAddress(gridAddress);
				if (grid.delete() && gridTmp.insert()) {
					sb.append("{");
					sb.append("\"statusCode\":" + "\"200\"");
					sb.append(",\"message\":" + "\"网格信息修改成功！\"");
					sb.append(",\"navTabId\":" + "\"\"");
					sb.append(",\"rel\":" + "\"\"");
					sb.append(",\"callbackType\":" + "\"\"");
					sb.append(",\"forwardUrl\":" + "\"\"");
					sb.append("}");
				} else {
					sb.append("{");
					sb.append("\"statusCode\":" + "\"300\"");
					sb.append(",\"message\":" + "\"网格信息修改失败，请重新操作！\"");
					sb.append(",\"navTabId\":" + "\"\"");
					sb.append(",\"rel\":" + "\"\"");
					sb.append(",\"callbackType\":" + "\"\"");
					sb.append(",\"forwardUrl\":" + "\"\"");
					sb.append("}");
				}
			} else {
				sb.append("{");
				sb.append("\"statusCode\":" + "\"300\"");
				sb.append(",\"message\":" + "\"网格 " + originalGridCode + " 不存在！\"");
				sb.append(",\"navTabId\":" + "\"\"");
				sb.append(",\"rel\":" + "\"\"");
				sb.append(",\"callbackType\":" + "\"\"");
				sb.append(",\"forwardUrl\":" + "\"\"");
				sb.append("}");
			}
		} else {
			grid.setCode(originalGridCode);
			if(grid.exist()) {
				Grid gridTmp = Grid.findOne(grid.toString());
				gridTmp.setName(gridName);
				gridTmp.setManager(gridManager);
				gridTmp.setAddress(gridAddress);
				if (grid.update(gridTmp.toString())) {
					sb.append("{");
					sb.append("\"statusCode\":" + "\"200\"");
					sb.append(",\"message\":" + "\"网格信息修改成功！\"");
					sb.append(",\"navTabId\":" + "\"\"");
					sb.append(",\"rel\":" + "\"\"");
					sb.append(",\"callbackType\":" + "\"\"");
					sb.append(",\"forwardUrl\":" + "\"\"");
					sb.append("}");
				} else {
					sb.append("{");
					sb.append("\"statusCode\":" + "\"300\"");
					sb.append(",\"message\":" + "\"网格信息修改失败，请重新操作！\"");
					sb.append(",\"navTabId\":" + "\"\"");
					sb.append(",\"rel\":" + "\"\"");
					sb.append(",\"callbackType\":" + "\"\"");
					sb.append(",\"forwardUrl\":" + "\"\"");
					sb.append("}");
				}
			} else {
				sb.append("{");
				sb.append("\"statusCode\":" + "\"300\"");
				sb.append(",\"message\":" + "\"网格 " + originalGridCode + " 不存在！\"");
				sb.append(",\"navTabId\":" + "\"\"");
				sb.append(",\"rel\":" + "\"\"");
				sb.append(",\"callbackType\":" + "\"\"");
				sb.append(",\"forwardUrl\":" + "\"\"");
				sb.append("}");
			}
		}
		
		result.put("EditGridResult", sb.toString());
		return result;
	}

}
