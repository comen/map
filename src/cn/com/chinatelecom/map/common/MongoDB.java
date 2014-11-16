package cn.com.chinatelecom.map.common;

import java.util.ArrayList;
import java.util.List;

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

	private MongoDB() {
		try {
			MongoClient mongoClient = new MongoClient(Config.getInstance()
					.getValue("dbhost"), Integer.parseInt(Config.getInstance()
					.getValue("dbport")));
			db = mongoClient.getDB(Config.getInstance().getValue("dbname"));
		} catch (Exception e) {
			System.out.println(e.getClass() + ":" + e.getMessage());
		}
	}

	public static MongoDB getInstance() {
		if (instance == null) {
			instance = new MongoDB();
		}
		return instance;
	}

	public boolean insert(String table, String json) {
		DBCollection coll = db.getCollection(table);
		DBObject q = (DBObject) JSON.parse(json);
		WriteResult wr = coll.save(q, WriteConcern.NORMAL);
		if (wr.getError() != null) {
			System.out.println(wr.getError());
			return false;
		}
		return true;
	}

	public boolean delete(String table, String json) {
		DBCollection coll = db.getCollection(table);
		DBObject q = (DBObject) JSON.parse(json);
		WriteResult wr = coll.remove(q, WriteConcern.NORMAL);
		if (wr.getError() != null) {
			return false;
		}
		return true;
	}

	public boolean update(String table, String qJson, String oJson) {
		DBCollection coll = db.getCollection(table);
		DBObject q = (DBObject) JSON.parse(qJson);
		DBObject o = (DBObject) JSON.parse(oJson);
		WriteResult wr = coll.update(q, o);
		if (wr.getError() != null) {
			return false;
		}
		return true;
	}

	public List<DBObject> findList(String table, String json) {
		DBCollection coll = db.getCollection(table);
		List<DBObject> dbl = null;
		DBCursor cursor = null;
		if (json != null) {
			DBObject dbo = (DBObject) JSON.parse(json);
			cursor = coll.find(dbo);
		} else {
			cursor = coll.find();
		}
		dbl = new ArrayList<DBObject>();
		while (cursor.hasNext()) {
			dbl.add(cursor.next());
		}
		return dbl;
	}

	public DBObject findOne(String table, String json) {
		DBCollection coll = db.getCollection(table);
		DBObject o = (DBObject) JSON.parse(json);
		return coll.findOne(o);
	}

	@Override
	public String toString() {
		return "MongoDB [db=" + db + "]";
	}

}
