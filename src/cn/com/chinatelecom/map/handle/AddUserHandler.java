/**
 * 
 */
package cn.com.chinatelecom.map.handle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.entity.User;
import cn.com.chinatelecom.map.utils.DateUtils;

/**
 * @author Shelwin
 *
 */
public class AddUserHandler implements IHandler {

	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		User user = new User();
		String userName = "";
		String password = "";
		int role = 4;	// Set default as Normal User
		String realName = "";
		String department = "";
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				try {
					String string = item.getString(Config.getInstance().getValue("charset").toString());
					
					switch(fieldName) {
					case "username":
						userName = string;
						break;
					case "password":
						password = string;
						break;
					case "role":
						try {
							role = Integer.parseInt(string);
						} catch (Exception e) {
							logger.error("解析用户角色信息失败：" + e.getMessage());
						}
						break;
					case "realname":
						realName = string;
						break;
					case "department":
						department = string;
						break;
					}
				} catch (java.io.UnsupportedEncodingException e) {
					logger.error("UnsupportedEncodingException: " + e.getMessage());
				}
			}
		}
		
		user.setUserName(userName);
		if(user.exist()) {
			sb.append("{");
			sb.append("\"statusCode\":" + "\"300\"");
			sb.append(",\"message\":" + "\"用户名 " + userName + " 已存在！\"");
			sb.append(",\"navTabId\":" + "\"\"");
			sb.append(",\"rel\":" + "\"\"");
			sb.append(",\"callbackType\":" + "\"\"");
			sb.append(",\"forwardUrl\":" + "\"\"");
			sb.append("}");
		} else {
			user.setPassword(password);
			user.setRole(role);
			user.setRealName(realName);
			user.setDepartment(department);
			user.setCreateDate(DateUtils.getCurrentDate());
			if (user.insert()) {
				sb.append("{");
				sb.append("\"statusCode\":" + "\"200\"");
				sb.append(",\"message\":" + "\"用户添加成功！\"");
				sb.append(",\"navTabId\":" + "\"\"");
				sb.append(",\"rel\":" + "\"\"");
				sb.append(",\"callbackType\":" + "\"\"");
				sb.append(",\"forwardUrl\":" + "\"\"");
				sb.append("}");
			} else {
				sb.append("{");
				sb.append("\"statusCode\":" + "\"300\"");
				sb.append(",\"message\":" + "\"用户添加失败，请重新操作！\"");
				sb.append(",\"navTabId\":" + "\"\"");
				sb.append(",\"rel\":" + "\"\"");
				sb.append(",\"callbackType\":" + "\"\"");
				sb.append(",\"forwardUrl\":" + "\"\"");
				sb.append("}");
			}
		}
		
		result.put("AddUserResult", sb.toString());
		return result;
	}

}
