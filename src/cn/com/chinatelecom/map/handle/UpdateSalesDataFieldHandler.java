/**
 * 
 */
package cn.com.chinatelecom.map.handle;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.entity.User;
import cn.com.chinatelecom.map.utils.DateUtils;
import cn.com.chinatelecom.map.utils.StringUtils;

/**
 * @author Shelwin
 *
 */
public class UpdateSalesDataFieldHandler implements IHandler {

	/* (non-Javadoc)
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		String salesDataType = "";
		String description = "";
		String onlyDay = "1";
		String jueduizhiThreshold = "*";
		String huanbiThreshold = "*";
		String tongbiThreshold = "*";
		String status = "1";
		String category = "1";
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				try {
					String string = item.getString(Config.getInstance().getValue("charset").toString());
					
					switch(fieldName) {
					case "salesDataType":
						salesDataType = string;
						break;
					case "description":
						description = string;
						break;
					case "onlyDay":
						onlyDay = string;
						break;
					case "jueduizhiThreshold":
						jueduizhiThreshold = string;
						break;
					case "huanbiThreshold":
						huanbiThreshold = string;
						break;
					case "tongbiThreshold":
						tongbiThreshold = string;
						break;
					case "status":
						status = string;
						break;
					case "category":
						category = string;
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
		
		sb.append("{");
		sb.append("\"description\":\"" + description + "\"");
		sb.append(",\"onlyDay\":" + onlyDay);
		sb.append(",\"jueduizhiThreshold\":\"" + jueduizhiThreshold + "\"");
		sb.append(",\"huanbiThreshold\":\"" + huanbiThreshold + "\"");
		sb.append(",\"tongbiThreshold\":\"" + tongbiThreshold + "\"");
		sb.append(",\"status\":" + status);
		sb.append(",\"category\":" + category);
		sb.append("}");
		
		Config config = Config.getInstance();
		config.setValue(salesDataType, sb.toString());
		
		result.put("UpdateSalesDataFieldResult", salesDataType + " 修改成功！");
		
		return result;
	}

}
