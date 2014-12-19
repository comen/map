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
import cn.com.chinatelecom.map.entity.Record;
import cn.com.chinatelecom.map.utils.DateUtils;
import cn.com.chinatelecom.map.utils.StringUtils;

/**
 * @author Shelwin
 *
 */
public class DeleteRecordHandler implements IHandler {

	/* (non-Javadoc)
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		Record record = new Record();
		String code = "";
		String dateStr = "";
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				try {
					String string = item.getString(Config.getInstance().getValue("charset").toString());
					
					switch(fieldName) {
					case "rid":
						code = string;
						break;
					case "date":
						dateStr = string;
						break;
					}
				} catch (java.io.UnsupportedEncodingException e) {
					String log = StringUtils.getLogPrefix(Level.SEVERE);
					System.out.println("\n" + log + "\n" + e.getClass()
							+ "\t:\t" + e.getMessage());
				}
			}
		}
		
		record.setCode(code);
		List<Record> recordList = Record.findList(record.getBasicDBObject().toString());
		if(recordList != null) {
			Date date = DateUtils.getSpecificDate(dateStr, "yyyy-MM-dd");
			int year1 = date.getYear();
			int month1 = date.getMonth() + 1;
			int day1 = date.getDate();
			Boolean deleteSuccess = false;
			for (Record list : recordList) {
				int year2 = list.getDate().getYear();
				int month2 = list.getDate().getMonth() + 1;
				int day2 = list.getDate().getDate();
				if (year2 == year1 && month2 == month1 && day2 == day1) {
					deleteSuccess = list.delete();
				}
			}
			if (deleteSuccess) {
				sb.append("{");
				sb.append("\"statusCode\":" + "\"200\"");
				sb.append(",\"message\":" + "\"网格变动记录删除成功！\"");
				sb.append(",\"navTabId\":" + "\"\"");
				sb.append(",\"rel\":" + "\"\"");
				sb.append(",\"callbackType\":" + "\"\"");
				sb.append(",\"forwardUrl\":" + "\"\"");
				sb.append("}");
			} else {
				sb.append("{");
				sb.append("\"statusCode\":" + "\"300\"");
				sb.append(",\"message\":" + "\"网格变动记录删除失败，请重新操作！\"");
				sb.append(",\"navTabId\":" + "\"\"");
				sb.append(",\"rel\":" + "\"\"");
				sb.append(",\"callbackType\":" + "\"\"");
				sb.append(",\"forwardUrl\":" + "\"\"");
				sb.append("}");
			}
		} else {
			sb.append("{");
			sb.append("\"statusCode\":" + "\"300\"");
			sb.append(",\"message\":" + "\"网格 " + code + " 变动记录不存在！\"");
			sb.append(",\"navTabId\":" + "\"\"");
			sb.append(",\"rel\":" + "\"\"");
			sb.append(",\"callbackType\":" + "\"\"");
			sb.append(",\"forwardUrl\":" + "\"\"");
			sb.append("}");
		}
		
		result.put("DeleteRecordResult", sb.toString());
		return result;
	}

}
