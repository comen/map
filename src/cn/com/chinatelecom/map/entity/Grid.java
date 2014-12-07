package cn.com.chinatelecom.map.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import javafx.scene.shape.Polygon;
import cn.com.chinatelecom.map.common.GeoCoder;
import cn.com.chinatelecom.map.common.MongoDB;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * @author joseph
 *
 */
public class Grid {

	private String code;
	private String name;
	private String manager;
	private String address;
	private List<Coordinate> coordinates;
	private static Logger logger = Logger.getLogger(Grid.class);

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

	public List<Coordinate> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	public Grid(String json) {
		if (null != json) {
			try {
				DBObject dbo = (DBObject) JSON.parse(json);
				setGrid(dbo);
			} catch (Exception e) {
				logger.warn("解析网格字符串错误: " + e.getMessage());
			}
		} else {
			logger.error("待设置网格字符串为空！");
		}
	}

	public Grid(DBObject dbo) {
		if (null != dbo)
			setGrid(dbo);
		else
			logger.error("待设置网格数据库对象为空！");
	}

	private void setGrid(DBObject dbo) {
		if (null != dbo && null != dbo.get("GRID_CODE")) {
			code = dbo.get("GRID_CODE").toString();
		} else {
			logger.error("待设置网格ID为空！");
			return;
		}
		if (null != dbo.get("GRID_NAME"))
			name = dbo.get("GRID_NAME").toString();
		if (null != dbo.get("GRID_MANAGER"))
			manager = dbo.get("GRID_MANAGER").toString();
		if (null != dbo.get("GRID_ADDRESS"))
			address = dbo.get("GRID_ADDRESS").toString();
		if (dbo.containsField("GRID_COORDINATES")) {
			coordinates = new ArrayList<Coordinate>();
			BasicDBList bdbl = (BasicDBList) dbo.get("GRID_COORDINATES");
			Iterator<Object> i = bdbl.iterator();
			while (i.hasNext()) {
				BasicDBObject bdbo = (BasicDBObject) i.next();
				Coordinate coordinate = new Coordinate(bdbo);
				coordinates.add(coordinate);
			}
		}
		MongoDB.getInstance().indexOn("grid", "GRID_CODE");
	}

	public boolean exist() {
		DBObject dbo = MongoDB.getInstance().findOne("grid", toString());
		if (null == dbo)
			return false;
		else
			return true;
	}

	public boolean insert() {
		return MongoDB.getInstance().insert("grid", toString());
	}

	public boolean delete() {
		return MongoDB.getInstance().delete("grid", toString());
	}

	public boolean update(String json) {
		return MongoDB.getInstance().update("grid", toString(), json);
	}

	public static Grid findOne(String json) {
		DBObject dbo = MongoDB.getInstance().findOne("grid", json);
		if (null == dbo)
			return null;
		return new Grid(dbo);
	}

	public static List<Grid> findList(String json) {
		List<Grid> gl = new ArrayList<Grid>();
		List<DBObject> dbl = MongoDB.getInstance().findList("grid", json);
		if (null == dbl || dbl.isEmpty())
			return null;
		for (DBObject dbo : dbl)
			gl.add(new Grid(dbo));
		return gl;
	}

	public static Grid search(String address) {
		List<Grid> grids = findList(null);
		if (null == grids || null == address)
			return null;
		for (Grid grid : grids) {
			if (grid.contains(address) && 1 != grid.getCode().length()) {
				return grid;
			}
		}
		return null;
	}

	public boolean contains(Coordinate coordinate) {
		if (null == coordinates || coordinates.isEmpty() || null == coordinate)
			return false;
		if (getPolygon().contains(coordinate.getLongitude(),
				coordinate.getLatitude()))
			return true;
		else
			return false;
	}

	public boolean contains(Grid grid) {
		if (null == coordinates || coordinates.isEmpty() || null == grid)
			return false;
		Polygon polygon = getPolygon();
		List<Coordinate> points = grid.getCoordinates();
		if (null == points)
			return contains(grid.getAddress());
		for (Coordinate point : points) {
			if (polygon.contains(point.getLongitude(), point.getLatitude())) {
				return true;
			}
		}
		return false;
	}

	public boolean contains(String address) {
		if (null == coordinates || coordinates.isEmpty() || null == address
				|| address.trim().equals(""))
			return false;
		Coordinate coordinate = getCoordinate(address);
		return contains(coordinate);
	}

	public Polygon getPolygon() {
		int size = coordinates.size();
		double[] points = new double[size * 2];
		for (int i = 0; i != size; i++) {
			points[i * 2] = coordinates.get(i).getLongitude();
			points[i * 2 + 1] = coordinates.get(i).getLatitude();
		}
		Polygon polygon = new Polygon(points);
		return polygon;
	}

	public static Coordinate getCoordinate(String address) {
		if (null == address)
			return null;
		String json = GeoCoder.getInstance().geoCode(address);
		DBObject dbo = null;
		try {
			dbo = (DBObject) JSON.parse(json);
			if (null != dbo && null != dbo.get("result")) {
				json = dbo.get("result").toString();
				dbo = (DBObject) JSON.parse(json);
				if (null != dbo.get("location")) {
					json = dbo.get("location").toString();
					dbo = (DBObject) JSON.parse(json);
					Double longitude = (Double) dbo.get("lng");
					Double latitude = (Double) dbo.get("lat");
					Coordinate coordinate = new Coordinate(latitude, longitude);
					return coordinate;
				}
			}
		} catch (Exception e) {
			logger.fatal("根据网格地址(" + address + ")获取坐标失败: " + e.getMessage());
		}
		return null;
	}

	public BasicDBObject getBasicDBObject() {
		BasicDBObject bdbo = new BasicDBObject("GRID_CODE", code);
		if (null != name)
			bdbo.append("GRID_NAME", name);
		if (null != manager)
			bdbo.append("GRID_MANAGER", manager);
		if (null != address)
			bdbo.append("GRID_ADDRESS", address);
		if (null != coordinates && !coordinates.isEmpty()) {
			BasicDBList bdbl = new BasicDBList();
			for (Coordinate coordinate : coordinates) {
				bdbl.add(coordinate);
			}
			bdbo.append("GRID_COORDINATES", bdbl);
		}
		return bdbo;
	}

	public String toFetch(String color) {
		StringBuffer sb = new StringBuffer("{c:'" + code + "'");
		if (null != address)
			sb.append(",d:'" + address + "'");
		if (null != color)
			sb.append(",r:'" + color + "'");
		if (null != coordinates && !coordinates.isEmpty()) {
			sb.append(",p:[");
			for (Coordinate coordinate : coordinates) {
				sb.append("{a:" + coordinate.getLatitude() + ",o:"
						+ coordinate.getLongitude() + "},");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
		}
		sb.append("}");
		return sb.toString();
	}

	public String toInfo(String data) {
		StringBuffer sb = new StringBuffer("<i>网格名称:" + name + "<br/>");
		if (1 != code.length()) {
			sb.append("网格编号:" + code + "<br/>");
			sb.append("网格经理:" + manager + "<br/>");
			sb.append("网格地址:" + address);
		}
		sb.append("</i>");
		if (null != data && !"".equals(data))
			sb.append("<br/>" + data);
		return sb.toString();
	}

	@Override
	public String toString() {
		return getBasicDBObject().toString();
	}
}
