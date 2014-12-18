package cn.com.chinatelecom.map.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cn.com.chinatelecom.map.common.MongoDB;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

/**
 * @author joseph
 *
 */
public class Record {

	public static Logger logger = Logger.getLogger(Record.class);

	private String code;
	private String name;
	private String manager;
	private String address;
	private Date date;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Record() {
		
	}

	public Record(String json) {
		if (null != json) {
			try {
				BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
				setRecord(bdbo);
			} catch (Exception e) {
				logger.fatal("解析记录字符串错误: " + e.getMessage());
			}
		} else {
			logger.warn("待设置记录字符串为空！");
		}
	}

	public Record(BasicDBObject bdbo) {
		if (null != bdbo)
			setRecord(bdbo);
		else
			logger.warn("待设置记录数据库对象为空！");
	}

	private void setRecord(BasicDBObject bdbo) {
		if (null != bdbo && null != bdbo.getString("GRID_CODE")) {
			code = bdbo.getString("GRID_CODE");
		} else {
			logger.warn("待设置记录ID为空！");
			return;
		}
		name = bdbo.getString("GRID_NAME");
		manager = bdbo.getString("GRID_MANAGER");
		address = bdbo.getString("GRID_ADDRESS");
		date = bdbo.getDate("GRID_DATETIME");
	}

	public boolean exist() {
		BasicDBObject bdbo = MongoDB.getInstance().findOne("record",
				getBasicDBObject());
		if (null == bdbo)
			return false;
		else
			return true;
	}

	public boolean insert() {
		return MongoDB.getInstance().insert("record", getBasicDBObject());
	}

	public boolean delete() {
		return MongoDB.getInstance().delete("record", getBasicDBObject());
	}

	public boolean update(String json) {
		BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
		return MongoDB.getInstance().update("record", getBasicDBObject(), bdbo);
	}

	public static Record findOne(String json) {
		BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
		bdbo = MongoDB.getInstance().findOne("record", bdbo);
		if (null == bdbo)
			return null;
		return new Record(bdbo);
	}
	
	public static List<Record> findList(String json) {
		List<Record> rl = new ArrayList<Record>();
		BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
		List<BasicDBObject> bdbol = MongoDB.getInstance().findList("record",
				bdbo);
		if (null == bdbol || bdbol.isEmpty())
			return null;
		for (BasicDBObject o : bdbol) {
			rl.add(new Record(o));
		}
		return rl;
	}

	public BasicDBObject getBasicDBObject() {
		BasicDBObject bdbo = new BasicDBObject();
		if (null != code)
			bdbo.append("GRID_CODE", code);
		if (null != name)
			bdbo.append("GRID_NAME", name);
		if (null != manager)
			bdbo.append("GRID_MANAGER", manager);
		if (null != address)
			bdbo.append("GRID_ADDRESS", address);
		if (null != date)
			bdbo.append("GRID_DATETIME", date);
		return bdbo;
	}

	public String toString() {
		return getBasicDBObject().toString();
	}

}
