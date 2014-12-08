package cn.com.chinatelecom.map.entity;

import com.mongodb.BasicDBObject;

import cn.com.chinatelecom.map.utils.MathUtils;

/**
 * @author joseph
 *
 */
public class Coordinate {

	private double latitude;
	private double longitude;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Coordinate(double latitude, double longitude) {
		this.latitude = MathUtils.getTitude(latitude, 5);
		this.longitude = MathUtils.getTitude(longitude, 5);
	}

	public Coordinate(BasicDBObject bdbo) {
		this.latitude = MathUtils.getTitude(bdbo.getDouble("LATITUDE"), 5);
		this.longitude = MathUtils.getTitude(bdbo.getDouble("LONGITUDE"), 5);
	}

	public BasicDBObject getBasicDBObject() {
		BasicDBObject bdbo = new BasicDBObject();
		bdbo.append("LATITUDE", latitude);
		bdbo.append("LONGITUDE", longitude);
		return bdbo;
	}

	@Override
	public String toString() {
		return getBasicDBObject().toString();
	}

}
