/**
 * 
 */
package cn.com.chinatelecom.map.handle;

//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.entity.User;
import cn.com.chinatelecom.map.utils.DateUtils;
import cn.com.chinatelecom.map.utils.StringUtils;

/**
 * @author Shelwin
 *
 */
public class SearchUserHandler implements IHandler {

	/* (non-Javadoc)
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	
	class SortByUserNameAsc implements Comparator<User> {
		public int compare(User u1, User u2) {
			return u1.getUserName().compareTo(u2.getUserName());
		}
	}
	
	class SortByUserNameDsc implements Comparator<User> {
		public int compare(User u1, User u2) {
			return u2.getUserName().compareTo(u1.getUserName());
		}
	}
	
	class SortByCreateDateAsc implements Comparator<User> {
		public int compare(User u1, User u2) {
			return u1.getCreateDate().compareTo(u2.getCreateDate());
		}
	}
	
	class SortByCreateDateDsc implements Comparator<User> {
		public int compare(User u1, User u2) {
			return u2.getCreateDate().compareTo(u1.getCreateDate());
		}
	}
	
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		List<User> userList = new ArrayList<User>();
		StringBuffer sb = new StringBuffer();
		
		Boolean advSearch = false;
		/* For User Normal Search */
		String userName = "";
		int role = 0;
		Date createDate = null;
		/* For User Advanced Search */
		String advUserName = "";
		String advRealName = "";
		String advDepartment = "";
		Date advStartDate = null;
		Date advEndDate = null;
		String advSort = "";
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				try {
					String string = item.getString(Config.getInstance().getValue("charset").toString());
					
					switch(fieldName) {
					case "advsearch":
						advSearch = Boolean.parseBoolean(string);
						break;
					case "adv_sort":
						advSort = string;
						break;
					/* For User Normal Search */
					case "username":
						userName = string;
						break;
					case "role":
						try {
							role = Integer.parseInt(string);
						} catch (Exception e) {}
						break;
					case "createdate":
						if (!(string == null || string.equals(""))) {
							createDate = DateUtils.getSpecificDate(string, "yyyy-MM-dd");
						}
						break;
					/* For User Advanced Search */
					case "adv_username":
						advUserName = string;
						break;
					case "adv_realname":
						advRealName = string;
						break;
					case "adv_department":
						advDepartment = string;
						break;
					case "adv_startdate":
						if (!(string == null || string.equals(""))) {
							advStartDate = DateUtils.getSpecificDate(string, "yyyy-MM-dd");
						}
						break;
					case "adv_enddate":
						if (!(string == null || string.equals(""))) {
							advEndDate = DateUtils.getSpecificDate(string, "yyyy-MM-dd");
						}
						break;
					}
				} catch (java.io.UnsupportedEncodingException e) {
					@SuppressWarnings("deprecation")
					String log = StringUtils.getLogPrefix(Level.SEVERE);
					System.out.println("\n" + log + "\n" + e.getClass()
							+ "\t:\t" + e.getMessage());
				}
			}
		}
		
		if (advSearch == false) {
			User user = new User();
			if (!(userName == null || userName.equals(""))) {
				user.setUserName(userName);
			}
			if (role != 0) {
				user.setRole(role);
			}
			if (createDate != null) {
				user.setCreateDate(createDate);
			}
			userList = User.findList(user.toString());
		} else {
			List<User> userListTmp = User.findList(null); // Search all users
			if (userListTmp != null) {
				for (User user : userListTmp) {
					if (!(advUserName == null || advUserName.equals(""))) {
						if (!(user.getUserName() != null && user.getUserName().contains(advUserName))) {
							continue;
						}
					}
					if (!(advRealName == null || advRealName.equals(""))) {
						if (!(user.getRealName() != null && user.getRealName().contains(advRealName))) {
							continue;
						}
					}
					if (!(advDepartment == null || advDepartment.equals(""))) {
						if (!(user.getDepartment() != null && user.getDepartment().contains(advDepartment))) {
							continue;
						}
					}
					if (advStartDate != null) {
						if (user.getCreateDate() == null || user.getCreateDate().before(advStartDate)) {
							continue;
						}
					}
					if (advEndDate != null) {
						if (user.getCreateDate() == null || user.getCreateDate().after(advEndDate)) {
							continue;
						}
					}
					userList.add(user);
				}
			}
		}
		
		/* Sort User List */
		if (userList != null) {
			switch (advSort) {
			case "u_asending":
				Collections.sort(userList, new SortByUserNameAsc());
				break;
			case "d_asending":
				Collections.sort(userList, new SortByCreateDateAsc());
				break;
			case "u_descending":
				Collections.sort(userList, new SortByUserNameDsc());
				break;
			case "d_descending":
				Collections.sort(userList, new SortByCreateDateDsc());
				break;
			default:
				Collections.sort(userList, new SortByUserNameAsc());
			}
		}
		
		/* Prepare Output */
		sb.append("[");
		if (userList != null) {
			for (int i = 0; i < userList.size(); i++) {
				/* Clear password */
				userList.get(i).setPassword(null);
				
				sb.append(userList.get(i).toString());
				if (i < userList.size()-1) {
					sb.append(",");
				}
			}
		}
		sb.append("]");
		
		result.put("SearchUserResult", sb.toString());
		return result;
	}
}
