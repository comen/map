package cn.com.chinatelecom.map.handle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.entity.User;

public class ChangePwdHandler implements IHandler {

	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		User user = new User();
		String newPassword = "";
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				try {
					String string = item.getString(Config.getInstance().getValue("charset").toString());
					
					switch(fieldName) {
					case "username":
						user.setUserName(string);
						break;
					case "oldPassword":
						user.setPassword(string);
						break;
					case "newPassword":
						newPassword = string;
						break;
					case "role":
						user.setRole(string);
						break;
					}
				} catch (java.io.UnsupportedEncodingException e) {}
			}
		}
		
		if(user.exist()) {
			User userTmp = new User(user);
			userTmp.setPassword(newPassword);
			if (user.update(userTmp.toString())) {
				result.put("statusCode", "200");
				result.put("message", "密码修改成功！");
				result.put("navTabId", "");
				result.put("rel", "");
				result.put("callbackType", "closeCurrent");
				result.put("forwardUrl", "");
			} else {
				result.put("statusCode", "300");
				result.put("message", "密码修改失败，请重新操作！");
				result.put("navTabId", "");
				result.put("rel", "");
				result.put("callbackType", "");
				result.put("forwardUrl", "");
			}
		} else {
			result.put("statusCode", "300");
			result.put("message", "原密码输入有误，请重新操作！");
			result.put("navTabId", "");
			result.put("rel", "");
			result.put("callbackType", "");
			result.put("forwardUrl", "");
		}
		
		return result;
	}

}
