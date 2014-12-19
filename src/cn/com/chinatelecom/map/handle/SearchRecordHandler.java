/**
 * 
 */
package cn.com.chinatelecom.map.handle;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.entity.Record;
import cn.com.chinatelecom.map.utils.DateUtils;

/**
 * @author Shelwin
 *
 */
public class SearchRecordHandler implements IHandler {

	/* (non-Javadoc)
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	
	class SortByDateAsc implements Comparator<Record> {
		public int compare(Record r1, Record r2) {
			return r1.getDate().compareTo(r2.getDate());
		}
	}
	
	class SortByDateDsc implements Comparator<Record> {
		public int compare(Record r1, Record r2) {
			return r2.getDate().compareTo(r1.getDate());
		}
	}
	
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		Record record = new Record();
		String gridCode = "";
		Date changeDate = null;
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				try {
					String string = item.getString(Config.getInstance().getValue("charset").toString());
					
					switch(fieldName) {
					case "gridcode":
						gridCode = string;
						break;
					case "changedate":
						if (!(string == null || string.equals(""))) {
							changeDate = DateUtils.getSpecificDate(string, "yyyy-MM-dd");
						}
						break;
					}
				} catch (java.io.UnsupportedEncodingException e) {
					logger.error("获取网格变动日志数据失败：" + e.getMessage());
				}
			}
		}
		
		if (!(gridCode == null || gridCode.equals(""))) {
			record.setCode(gridCode);
		}
		if (changeDate != null) {
			record.setDate(changeDate);
		}
		
		List<Record> recordList = Record.findList(record.getBasicDBObject().toString());
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (recordList != null) {
			/* Sort Record List */
			Collections.sort(recordList, new SortByDateDsc());
			
			for (int i = 0; i < recordList.size(); i++) {
				sb.append(recordList.get(i).getBasicDBObject().toString());
				if (i < recordList.size()-1) {
					sb.append(",");
				}
			}
		}
		sb.append("]");
		
		result.put("SearchRecordResult", sb.toString());
		return result;
	}

}
