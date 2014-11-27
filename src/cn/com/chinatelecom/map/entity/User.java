package cn.com.chinatelecom.map.entity;

import java.util.ArrayList;
import java.util.List;

import cn.com.chinatelecom.map.common.MongoDB;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * @author joseph
 * 
 */
public class User {
	
	protected String userName;
	protected String password;
	protected String role; /* 1 - System Administrator; 2 - Grid Data Administrator; 3 - Sales Data Administrator; 4 - Normal User */
	protected String realName;
	protected String department;
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getRole() {
		return role;
	}
	
	public User() {
		
	}
	
	public User(User user) {
		this.userName = user.userName;
		this.password = user.password;
		this.role = user.role;
		this.realName = user.realName;
		this.department = user.department;
	}

	public User(String json) {
		DBObject dbo = (DBObject) JSON.parse(json);
		setUser(dbo);
	}

	public User(DBObject dbo) {
		setUser(dbo);
	}
	
	private void setUser(DBObject dbo) {
		if (dbo.get("username") != null) {
			userName = dbo.get("username").toString();
		} else {
			return;
		}
		if (dbo.get("password") != null) {
			password = dbo.get("password").toString();
		}
		if (dbo.get("role") != null) {
			role = dbo.get("role").toString();
		}
	}
	
	public boolean exist() {
		DBObject dbo = MongoDB.getInstance().findOne("user", toString());
		if (dbo == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean insert() {
		return MongoDB.getInstance().insert("user", toString());
	}
	
	public boolean delete() {
		return MongoDB.getInstance().delete("user", toString());
	}
	
	public boolean update(String json) {
		return MongoDB.getInstance().update("user", toString(), json);
	}
	
	public static User findOne(String json) {
		return new User(MongoDB.getInstance().findOne("user", json));
	}
	
	public static List<User> findList(String json) {
		List<User> ul = new ArrayList<User>();
		List<DBObject> dbl = MongoDB.getInstance().findList("user", json);
		for (DBObject dbo : dbl) {
			ul.add(new User(dbo));
		}
		return ul;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("{'username':'" + userName + "'");
		if (password != null) {
			sb.append(",'password':'" + password + "'");
		}
		if (role != null) {
			sb.append(",'role':" + role);
		}
		sb.append("}");
		return sb.toString();
	}

}
