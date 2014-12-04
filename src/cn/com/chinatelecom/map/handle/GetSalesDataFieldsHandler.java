/**
 * 
 */
package cn.com.chinatelecom.map.handle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.entity.Data;

/**
 * @author Shelwin
 *
 */
public class GetSalesDataFieldsHandler implements IHandler {

	/* (non-Javadoc)
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
//		int status = 1;
//		
//		for (FileItem item : items) {
//			if (item.isFormField()) {
//				String fieldName = item.getFieldName();
//				if (fieldName.equals("status")) {
//					status = Integer.parseInt(item.getString());
//				}
//			}
//		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		String[] namesOfMemVar = Data.getNameOfMemberVariables();
		for (int i = 0; i < namesOfMemVar.length; i++) {
			if (namesOfMemVar[i].equalsIgnoreCase("calculatedDate") || namesOfMemVar[i].equalsIgnoreCase("gridCode")) {
				continue;
			}
			if (Data.fieldIsOnUse(namesOfMemVar[i])) {
				sb.append("{\"field\":\"" + namesOfMemVar[i] + "\"," + "\"description\":\"" + Data.getFieldDesc(namesOfMemVar[i]) + "\"},");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		result.put("SalesDataFields", sb.toString());
		
		return result;
	}

}
