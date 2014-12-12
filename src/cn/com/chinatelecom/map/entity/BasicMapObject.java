package cn.com.chinatelecom.map.entity;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;

/**
 * @author joseph
 *
 */
public abstract class BasicMapObject {

	protected static Logger logger = Logger.getLogger(BasicMapObject.class);

	public abstract BasicDBObject getBasicDBObject();

	public String toString() {
		return getBasicDBObject().toString();
	}
}
