/**
 * 
 */
package cn.com.chinatelecom.map.handle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.entity.User;
import cn.com.chinatelecom.map.utils.StringUtils;

/**
 * @author Shelwin
 *
 */
public class EditUserHandler implements IHandler {

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
					case "resetpassword":
						password = string;
						break;
					case "role":
						try {
							role = Integer.parseInt(string);
						} catch (Exception e) {
							@SuppressWarnings("deprecation")
							String log = StringUtils.getLogPrefix(Level.SEVERE);
							System.out.println("\n" + log + "\n" + e.getClass()
									+ "\t:\t" + e.getMessage());
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
			User userTmp = User.findOne(user.toString());
			userTmp.setPassword(password);
			userTmp.setRole(role);
			userTmp.setRealName(realName);
			userTmp.setDepartment(department);
			if (user.update(userTmp.toString())) {
				sb.append("{");
				sb.append("\"statusCode\":" + "\"200\"");
				sb.append(",\"message\":" + "\"用户信息修改成功！\"");
				sb.append(",\"navTabId\":" + "\"\"");
				sb.append(",\"rel\":" + "\"\"");
				sb.append(",\"callbackType\":" + "\"\"");
				sb.append(",\"forwardUrl\":" + "\"\"");
				sb.append("}");
			} else {
				sb.append("{");
				sb.append("\"statusCode\":" + "\"300\"");
				sb.append(",\"message\":" + "\"用户信息修改失败，请重新操作！\"");
				sb.append(",\"navTabId\":" + "\"\"");
				sb.append(",\"rel\":" + "\"\"");
				sb.append(",\"callbackType\":" + "\"\"");
				sb.append(",\"forwardUrl\":" + "\"\"");
				sb.append("}");
			}
		} else {
			sb.append("{");
			sb.append("\"statusCode\":" + "\"300\"");
			sb.append(",\"message\":" + "\"用户名 " + userName + " 不存在！\"");
			sb.append(",\"navTabId\":" + "\"\"");
			sb.append(",\"rel\":" + "\"\"");
			sb.append(",\"callbackType\":" + "\"\"");
			sb.append(",\"forwardUrl\":" + "\"\"");
			sb.append("}");
		}
		
		result.put("EditUserResult", sb.toString());
		return result;
	}

}
