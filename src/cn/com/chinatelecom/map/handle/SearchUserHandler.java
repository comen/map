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
public class SearchUserHandler implements IHandler {

	/* (non-Javadoc)
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		User user = new User();
		String userName = "";
		int role = 0;
		String realName = "";
		String department = "";
//		Date createDate = new Date();
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				try {
					String string = item.getString(Config.getInstance().getValue("charset").toString());
					
					switch(fieldName) {
					case "username":
						userName = string;
						break;
					case "role":
						role = Integer.parseInt(string);
						break;
					case "realname":
						realName = string;
						break;
					case "department":
						department = string;
						break;
//					case "createdate":
//						createDate = string;
//						break;
					}
				} catch (java.io.UnsupportedEncodingException e) {
					String log = StringUtils.getLogPrefix(Level.WARNING);
					System.out.println("\n" + log + "\n" + e.getClass()
							+ "\t:\t" + e.getMessage());
				}
			}
		}
		
		if (!userName.equals("")) {
			user.setUserName(userName);
		}
		if (role != 0) {
			user.setRole(role);
		}
		if (!realName.equals("")) {
			user.setRealName(realName);
		}
		if (!department.equals("")) {
			user.setDepartment(department);
		}
		
		List<User> userList = User.findList(user.toString());
		StringBuffer sb = new StringBuffer();
		
		sb = new StringBuffer();
		sb.append("{");
		for (int i = 0; i < userList.size(); i++) {
			sb.append(userList.get(i).toString());
			if (i < userList.size()-1) {
				sb.append(",");
			}
		}
		sb.append("}");
		
		result.put("SearchUserResult", sb.toString());
		return result;
	}

}
