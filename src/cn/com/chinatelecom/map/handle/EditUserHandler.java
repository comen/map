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

/**
 * @author Shelwin
 *
 */
public class EditUserHandler implements IHandler {

	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		User user = new User();
		String userName = "";
		String password = "";
		String role = "";
		String realName = "";
		String department = "";
		String createDate = "";
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				try {
					String string = item.getString(Config.getInstance().getValue("charset").toString());
					
					switch(fieldName) {
					case "username":
						userName = string;
						break;
					case "resetpassword":
						password = string;
						break;
					case "role":
						role = string;
						break;
					case "realname":
						realName = string;
						break;
					case "department":
						department = string;
						break;
					case "createdate":
						createDate = string;
						break;
					}
				} catch (java.io.UnsupportedEncodingException e) {}
			}
		}
		
		user.setUserName(userName);
		if(user.exist()) {
			User userTmp = new User(user);
			userTmp.setPassword(password);
			userTmp.setRole(role);
			userTmp.setRealName(realName);
			userTmp.setDepartment(department);
			userTmp.setCreateDate(createDate);
			if (user.update(userTmp.toString())) {
				result.put("statusCode", "200");
				result.put("message", "用户信息修改成功！");
				result.put("navTabId", "");
				result.put("rel", "");
				result.put("callbackType", "");
				result.put("forwardUrl", "");
			} else {
				result.put("statusCode", "300");
				result.put("message", "用户信息修改失败，请重新操作！");
				result.put("navTabId", "");
				result.put("rel", "");
				result.put("callbackType", "");
				result.put("forwardUrl", "");
			}
		} else {
			result.put("statusCode", "300");
			result.put("message", "用户名 " + userName + " 不存在！");
			result.put("navTabId", "");
			result.put("rel", "");
			result.put("callbackType", "");
			result.put("forwardUrl", "");
		}
		
		return result;
	}

}
