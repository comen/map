package cn.com.chinatelecom.map.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cn.com.chinatelecom.map.common.MongoDB;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

/**
 * @author Shelwin
 * 
 */
public class User {
	
	public static Logger logger = Logger.getLogger(User.class);
	
	private String userName;
	private String password;
	private int role; /* 1 - System Administrator; 2 - Grid Data Administrator; 3 - Sales Data Administrator; 4 - Normal User */
	private String realName;
	private String department;
	private Date createDate;
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRole(int role) {
		this.role = role;
	}
	
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getRole() {
		return role;
	}
	
	public String getRealName() {
		return realName;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public User() {
		
	}
	
	public User(User user) {
		this.userName = user.userName;
		this.password = user.password;
		this.role = user.role;
		this.realName = user.realName;
		this.department = user.department;
		this.createDate = user.createDate;
	}
	
	public User(String json) {
		if (null != json) {
			try {
				BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
				setUser(bdbo);
			} catch (Exception e) {
				logger.error("解析用户字符串错误: " + e.getMessage());
			}
		} else {
			logger.warn("待设置用户字符串为空！");
		}
	}
	
	public User(BasicDBObject bdbo) {
		if (null != bdbo)
			setUser(bdbo);
		else
			logger.warn("待设置用户数据库对象为空！");
	}
	
	private void setUser(BasicDBObject bdbo) {
		if (null != bdbo && null != bdbo.getString("username")) {
			userName = bdbo.getString("username");
		} else {
			logger.warn("待设置用户名为空！");
			return;
		}
		password = bdbo.getString("password");
		realName = bdbo.getString("realname");
		department = bdbo.getString("department");
		createDate = bdbo.getDate("createdate");
		if (bdbo.get("role") != null) {
			try {
				role = Integer.parseInt(bdbo.get("role").toString());
			} catch (Exception e) {
				logger.error("解析用户角色信息失败：" + e.getMessage());
			}
		}
	}
	
	public boolean exist() {
		BasicDBObject bdbo = MongoDB.getInstance().findOne("user",
				getBasicDBObject());
		if (null == bdbo)
			return false;
		else
			return true;
	}
	
	public boolean insert() {
		return MongoDB.getInstance().insert("user", getBasicDBObject());
	}
	
	public boolean delete() {
		return MongoDB.getInstance().delete("user", getBasicDBObject());
	}
	
	public boolean update(String json) {
		BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
		return MongoDB.getInstance().update("user", getBasicDBObject(), bdbo);
	}
	
	public static User findOne(String json) {
		BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
		bdbo = MongoDB.getInstance().findOne("user", bdbo);
		if (null == bdbo)
			return null;
		return new User(bdbo);
	}
	
	public static List<User> findList(String json) {
		List<User> ul = new ArrayList<User>();
		BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
		List<BasicDBObject> bdbol = MongoDB.getInstance().findList("user",
				bdbo);
		if (null == bdbol || bdbol.isEmpty())
			return null;
		for (BasicDBObject o : bdbol) {
			ul.add(new User(o));
		}
		return ul;
	}
	
	public BasicDBObject getBasicDBObject() {
		BasicDBObject bdbo = new BasicDBObject();
		if (null != userName)
			bdbo.append("username", userName);
		if (null != password)
			bdbo.append("password", password);
		if (role > 0)
			bdbo.append("role", role);
		if (null != realName)
			bdbo.append("realname", realName);
		if (null != department)
			bdbo.append("department", department);
		if (null != createDate)
			bdbo.append("createdate", createDate);
		return bdbo;
	}
	
	@Override
	public String toString() {
		return getBasicDBObject().toString();
	}

}
