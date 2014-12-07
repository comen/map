package cn.com.chinatelecom.map.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cn.com.chinatelecom.map.common.MongoDB;
import cn.com.chinatelecom.map.utils.DateUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * @author joseph
 *
 */
public class Record {

	private String code;
	private String name;
	private String manager;
	private String address;
	private Date date;
	private static Logger logger = Logger.getLogger(Record.class);

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

	public Record(String json) {
		if (null != json) {
			try {
				DBObject dbo = (DBObject) JSON.parse(json);
				setRecord(dbo);
			} catch (Exception e) {
				logger.warn("解析记录字符串错误: " + e.getMessage());
			}
		} else {
			logger.error("待设置记录字符串为空！");
		}
	}

	public Record(DBObject dbo) {
		if (null != dbo)
			setRecord(dbo);
		else
			logger.error("待设置记录数据库对象为空！");
	}

	private void setRecord(DBObject dbo) {
		if (null != dbo && null != dbo.get("GRID_CODE")) {
			code = dbo.get("GRID_CODE").toString();
		} else {
			logger.error("待设置记录ID为空！");
			return;
		}
		if (null != dbo.get("GRID_NAME"))
			name = dbo.get("GRID_NAME").toString();
		if (null != dbo.get("GRID_MANAGER"))
			manager = dbo.get("GRID_MANAGER").toString();
		if (null != dbo.get("GRID_ADDRESS"))
			address = dbo.get("GRID_ADDRESS").toString();
		if (null != dbo.get("GRID_ERASE_DATETIME")) {
			date = DateUtils.getSpecificDate(dbo.get("GRID_ERASE_DATETIME")
					.toString(), "yyyy-MM-dd HH:mm:ss");
		}
		MongoDB.getInstance().indexOn("record", "GRID_CODE");
	}

	public boolean exist() {
		DBObject dbo = MongoDB.getInstance().findOne("record", toString());
		if (null == dbo)
			return false;
		else
			return true;
	}

	public boolean insert() {
		return MongoDB.getInstance().insert("record", toString());
	}

	public boolean delete() {
		return MongoDB.getInstance().delete("record", toString());
	}

	public boolean update(String json) {
		return MongoDB.getInstance().update("record", toString(), json);
	}

	public static Record findOne(String json) {
		DBObject dbo = MongoDB.getInstance().findOne("record", json);
		if (null == dbo)
			return null;
		return new Record(dbo);
	}

	public static List<Record> findList(String json) {
		List<Record> rl = new ArrayList<Record>();
		List<DBObject> dbl = MongoDB.getInstance().findList("record", json);
		if (null == dbl || dbl.isEmpty())
			return null;
		for (DBObject dbo : dbl)
			rl.add(new Record(dbo));
		return rl;
	}

	public BasicDBObject getBasicDBObject() {
		BasicDBObject bdbo = new BasicDBObject("GRID_CODE", code);
		if (null != name)
			bdbo.append("GRID_NAME", name);
		if (null != manager)
			bdbo.append("GRID_MANAGER", manager);
		if (null != address)
			bdbo.append("GRID_ADDRESS", address);
		if (null != date)
			bdbo.append("GRID_ERASE_DATETIME", date);
		return bdbo;
	}

	@Override
	public String toString() {
		return getBasicDBObject().toString();
	}

}
