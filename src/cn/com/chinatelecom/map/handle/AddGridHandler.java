/**
 * 
 */
package cn.com.chinatelecom.map.handle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.entity.Grid;

/**
 * @author Shelwin
 *
 */
public class AddGridHandler implements IHandler {

	/* (non-Javadoc)
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		Grid grid = new Grid();
		String code = "";
		String name = "";
		String manager = "";
		String address = "";
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				try {
					String string = item.getString(Config.getInstance().getValue("charset").toString());
					
					switch(fieldName) {
					case "grid_code":
						code = string;
						break;
					case "grid_name":
						name = string;
						break;
					case "grid_manager":
						manager = string;
						break;
					case "grid_address":
						address = string;
						break;
					}
				} catch (java.io.UnsupportedEncodingException e) {
					logger.error("获取新增网格数据失败：" + e.getMessage());
				}
			}
		}
		
		grid.setCode(code);
		if(grid.exist()) {
			sb.append("{");
			sb.append("\"statusCode\":" + "\"300\"");
			sb.append(",\"message\":" + "\"网格 " + code + " 已存在！\"");
			sb.append(",\"navTabId\":" + "\"\"");
			sb.append(",\"rel\":" + "\"\"");
			sb.append(",\"callbackType\":" + "\"\"");
			sb.append(",\"forwardUrl\":" + "\"\"");
			sb.append("}");
		} else {
			grid.setName(name);
			grid.setManager(manager);
			grid.setAddress(address);
			if (grid.insert()) {
				sb.append("{");
				sb.append("\"statusCode\":" + "\"200\"");
				sb.append(",\"message\":" + "\"网格添加成功！\"");
				sb.append(",\"navTabId\":" + "\"\"");
				sb.append(",\"rel\":" + "\"\"");
				sb.append(",\"callbackType\":" + "\"\"");
				sb.append(",\"forwardUrl\":" + "\"\"");
				sb.append("}");
			} else {
				sb.append("{");
				sb.append("\"statusCode\":" + "\"300\"");
				sb.append(",\"message\":" + "\"网格添加失败，请重新操作！\"");
				sb.append(",\"navTabId\":" + "\"\"");
				sb.append(",\"rel\":" + "\"\"");
				sb.append(",\"callbackType\":" + "\"\"");
				sb.append(",\"forwardUrl\":" + "\"\"");
				sb.append("}");
			}
		}
		
		result.put("AddGridResult", sb.toString());
		return result;
	}

}
