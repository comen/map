/**
 * 
 */
package cn.com.chinatelecom.map.handle;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.entity.Data;
import cn.com.chinatelecom.map.utils.DateUtils;

/**
 * @author Shelwin
 *
 */
public class DeleteSalesDataHandler implements IHandler {

	/* (non-Javadoc)
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		List<Data> dataList = new ArrayList<Data>();
		Date date = null;
		String salesData = "";
		String[] salesDataArray = null;
		String message = "";
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				try {
					String string = item.getString(Config.getInstance().getValue("charset").toString());
					
					switch(fieldName) {
					case "date":
						date = DateUtils.getSpecificDate(string, "yyyy-MM-dd");
						break;
					case "salesData":
						salesData = string;
						salesDataArray = salesData.split(",");
						break;
					}
				} catch (java.io.UnsupportedEncodingException e) {
					logger.error("获取营销数据删除数据失败：" + e.getMessage());
				}
			}
		}
		
		if (date == null || salesDataArray == null) {
			message = "[错误] 数据解析操作异常，请重新操作！";
		} else {
			Data data = new Data();
			Data dataTmp = new Data();
			data.setCalculatedDate(date);
			dataList = Data.findList(data.getBasicDBObject());
			if (dataList != null) {
				for (Data eachData : dataList) {
					dataTmp = eachData;
					for (int i = 0; i < salesDataArray.length; i++) {
						if (eachData.getValue(salesDataArray[i]) != null) {
							dataTmp.setValue(salesDataArray[i], 0);
						}
					}
					eachData.update(dataTmp.getBasicDBObject());
				}
			}
			message = "操作成功！";
		}
		
		result.put("DeleteSalesDataResult", message);
		return result;
	}

}
