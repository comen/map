package cn.com.chinatelecom.map.entity;

import com.mongodb.BasicDBObject;

import cn.com.chinatelecom.map.utils.MathUtils;

/**
 * @author joseph
 *
 */
public class Coordinate {

	private double latitude;
	private double longtitude;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public Coordinate(double latitude, double longtitude) {
		this.latitude = MathUtils.getTitude(latitude, 5);
		this.longtitude = MathUtils.getTitude(longtitude, 5);
	}

	public Coordinate(BasicDBObject bdbo) {
		this.latitude = MathUtils.getTitude(bdbo.getDouble("LATITUDE"), 5);
		this.longtitude = MathUtils.getTitude(bdbo.getDouble("LONGTITUDE"), 5);
	}

	@Override
	public String toString() {
		return "Coordinate [latitude=" + latitude + ", longtitude="
				+ longtitude + "]";
	}

}
