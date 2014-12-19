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
		StringBuffer sb = new StringBuffer();
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
					}
				} catch (java.io.UnsupportedEncodingException e) {
					logger.error("获取密码修改数据失败：" + e.getMessage());
				}
			}
		}
		
		if(user.exist()) {
			User userTmp = User.findOne(user.toString());
			userTmp.setPassword(newPassword);
			if (user.update(userTmp.toString())) {
				sb.append("{");
				sb.append("\"statusCode\":" + "\"200\"");
				sb.append(",\"message\":" + "\"密码修改成功！\"");
				sb.append(",\"navTabId\":" + "\"\"");
				sb.append(",\"rel\":" + "\"\"");
				sb.append(",\"callbackType\":" + "\"closeCurrent\"");
				sb.append(",\"forwardUrl\":" + "\"\"");
				sb.append("}");
			} else {
				sb.append("{");
				sb.append("\"statusCode\":" + "\"300\"");
				sb.append(",\"message\":" + "\"密码修改失败，请重新操作！\"");
				sb.append(",\"navTabId\":" + "\"\"");
				sb.append(",\"rel\":" + "\"\"");
				sb.append(",\"callbackType\":" + "\"\"");
				sb.append(",\"forwardUrl\":" + "\"\"");
				sb.append("}");
			}
		} else {
			sb.append("{");
			sb.append("\"statusCode\":" + "\"300\"");
			sb.append(",\"message\":" + "\"原密码输入有误，请重新操作！\"");
			sb.append(",\"navTabId\":" + "\"\"");
			sb.append(",\"rel\":" + "\"\"");
			sb.append(",\"callbackType\":" + "\"\"");
			sb.append(",\"forwardUrl\":" + "\"\"");
			sb.append("}");
		}
		
		result.put("ChgPwdResult", sb.toString());
		return result;
	}

}
