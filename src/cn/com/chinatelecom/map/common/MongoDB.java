package cn.com.chinatelecom.map.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

/**
 * @author joseph
 *
 */
public class MongoDB {

	private static MongoDB instance;
	private DB db;
	private Logger logger = Logger.getLogger(MongoDB.class);

	private MongoDB() {
		try {
			MongoClient mongoClient = new MongoClient(Config.getInstance()
					.getValue("dbhost"), Integer.parseInt(Config.getInstance()
					.getValue("dbport")));
			db = mongoClient.getDB(Config.getInstance().getValue("dbname"));
		} catch (Exception e) {
			logger.fatal("初始化数据库失败: " + e.getMessage());
		}
	}

	public static MongoDB getInstance() {
		if (null == instance)
			instance = new MongoDB();
		return instance;
	}

	public void indexOn(String table, String index) {
		DBCollection coll = db.getCollection(table);
		coll.ensureIndex(new BasicDBObject(index, 1), index, true);
	}

	@Deprecated
	public boolean insert(String table, String json) {
		BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
		return insert(table, bdbo);
	}

	public boolean insert(String table, BasicDBObject bdbo) {
		DBCollection coll = db.getCollection(table);
		WriteResult wr = coll.save(bdbo, WriteConcern.NORMAL);
		String error = wr.getError();
		if (null != error) {
			logger.error("插入数据库错误: " + error);
			return false;
		}
		return true;
	}

	@Deprecated
	public boolean delete(String table, String json) {
		BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
		return delete(table, bdbo);
	}

	public boolean delete(String table, BasicDBObject bdbo) {
		DBCollection coll = db.getCollection(table);
		WriteResult wr = coll.remove(bdbo, WriteConcern.NORMAL);
		String error = wr.getError();
		if (null != error) {
			logger.error("删除数据错误: " + error);
			return false;
		}
		return true;
	}

	@Deprecated
	public boolean update(String table, String qJson, String oJson) {
		BasicDBObject q = (BasicDBObject) JSON.parse(qJson);
		BasicDBObject o = (BasicDBObject) JSON.parse(oJson);
		return update(table, q, o);
	}

	public boolean update(String table, BasicDBObject q, BasicDBObject o) {
		DBCollection coll = db.getCollection(table);
		WriteResult wr = coll.update(q, o);
		String error = wr.getError();
		if (null != error) {
			logger.error("更新数据错误: " + error);
			return false;
		}
		return true;
	}

	@Deprecated
	public List<DBObject> findList(String table, String json) {
		DBCollection coll = db.getCollection(table);
		List<DBObject> dbl = null;
		DBCursor cursor = null;
		if (null != json) {
			DBObject dbo = (DBObject) JSON.parse(json);
			cursor = coll.find(dbo);
		} else {
			cursor = coll.find();
		}
		dbl = new ArrayList<DBObject>();
		while (cursor.hasNext())
			dbl.add(cursor.next());
		return dbl;
	}

	public List<BasicDBObject> findList(String table, BasicDBObject bdbo) {
		DBCollection coll = db.getCollection(table);
		DBCursor cursor = null;
		if (null != bdbo)
			cursor = coll.find(bdbo);
		else
			cursor = coll.find();
		List<BasicDBObject> bdbol = new ArrayList<BasicDBObject>();
		while (cursor.hasNext()) {
			DBObject dbo = cursor.next();
			bdbol.add((BasicDBObject) dbo);
		}
		return bdbol;
	}

	@Deprecated
	public DBObject findOne(String table, String json) {
		DBCollection coll = db.getCollection(table);
		DBObject o = (DBObject) JSON.parse(json);
		return coll.findOne(o);
	}

	public BasicDBObject findOne(String table, BasicDBObject bdbo) {
		DBCollection coll = db.getCollection(table);
		if (null == bdbo)
			return null;
		return (BasicDBObject) coll.findOne(bdbo);
	}

	@Override
	public String toString() {
		return "MongoDB [db=" + db + "]";
	}

}
