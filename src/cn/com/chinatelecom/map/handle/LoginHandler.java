package cn.com.chinatelecom.map.handle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.entity.User;

public class LoginHandler implements IHandler {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		User user = new User();
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				try {
					String string = item.getString(Config.getInstance().getValue("charset").toString());
				
					switch(fieldName) {
					case "username":
						user.setUserName(string);
						break;
					case "password":
						user.setPassword(string);
						break;
					case "role":
						user.setRole(string);
						break;
					}
				}catch (java.io.UnsupportedEncodingException e) {
				}
			}
		}
		
		if(user.exist()) {
			result.put("username", user.getUserName());
			result.put("role", user.getRole());
		} else {
			result.put("-1", "Login Falied");
		}
		
		return result;
	}
	
}
