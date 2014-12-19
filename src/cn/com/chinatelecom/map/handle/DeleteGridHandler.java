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
import cn.com.chinatelecom.map.entity.Record;
import cn.com.chinatelecom.map.utils.DateUtils;

/**
 * @author Shelwin
 *
 */
public class DeleteGridHandler implements IHandler {

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
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				try {
					String string = item.getString(Config.getInstance().getValue("charset").toString());
					
					switch(fieldName) {
					case "gid":
						code = string;
						break;
					}
				} catch (java.io.UnsupportedEncodingException e) {
					logger.error("获取网格删除数据失败：" + e.getMessage());
				}
			}
		}
		
		grid.setCode(code);
		if(grid.exist()) {
			Grid gridTmp = Grid.findOne(grid.getBasicDBObject().toString());
			if (grid.delete()) {
				/* Record Delete Operation */
				Record record = new Record();
				record.setCode(gridTmp.getCode());
				record.setName(gridTmp.getName());
				record.setManager(gridTmp.getManager());
				record.setAddress(gridTmp.getAddress());
				record.setDate(DateUtils.getCurrentDate());
				record.insert();
				
				sb.append("{");
				sb.append("\"statusCode\":" + "\"200\"");
				sb.append(",\"message\":" + "\"网格删除成功！\"");
				sb.append(",\"navTabId\":" + "\"\"");
				sb.append(",\"rel\":" + "\"\"");
				sb.append(",\"callbackType\":" + "\"\"");
				sb.append(",\"forwardUrl\":" + "\"\"");
				sb.append("}");
			} else {
				sb.append("{");
				sb.append("\"statusCode\":" + "\"300\"");
				sb.append(",\"message\":" + "\"网格删除失败，请重新操作！\"");
				sb.append(",\"navTabId\":" + "\"\"");
				sb.append(",\"rel\":" + "\"\"");
				sb.append(",\"callbackType\":" + "\"\"");
				sb.append(",\"forwardUrl\":" + "\"\"");
				sb.append("}");
			}
		} else {
			sb.append("{");
			sb.append("\"statusCode\":" + "\"300\"");
			sb.append(",\"message\":" + "\"网格 " + code + " 不存在！\"");
			sb.append(",\"navTabId\":" + "\"\"");
			sb.append(",\"rel\":" + "\"\"");
			sb.append(",\"callbackType\":" + "\"\"");
			sb.append(",\"forwardUrl\":" + "\"\"");
			sb.append("}");
		}
		
		result.put("DelUserResult", sb.toString());
		return result;
	}

}
