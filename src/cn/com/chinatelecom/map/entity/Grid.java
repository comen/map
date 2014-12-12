package cn.com.chinatelecom.map.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.scene.shape.Polygon;
import cn.com.chinatelecom.map.common.GeoCoder;
import cn.com.chinatelecom.map.common.MongoDB;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

/**
 * @author joseph
 *
 */
public class Grid extends BasicMapObject implements IMapDBObject, Runnable {

	private String code;
	private String name;
	private String manager;
	private String address;
	private List<Coordinate> coordinates;

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
	
	public Grid() {
		
	}

	public Grid(String json) {
		if (null != json) {
			try {
				BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
				setGrid(bdbo);
			} catch (Exception e) {
				logger.warn("解析网格字符串错误: " + e.getMessage());
			}
		} else {
			logger.error("待设置网格字符串为空！");
		}
	}

	public Grid(BasicDBObject bdbo) {
		if (null != bdbo)
			setGrid(bdbo);
		else
			logger.error("待设置网格数据库对象为空！");
	}

	private void setGrid(BasicDBObject bdbo) {
		if (null != bdbo && null != bdbo.get("GRID_CODE")) {
			code = bdbo.getString("GRID_CODE");
		} else {
			logger.error("待设置网格ID为空！");
			return;
		}
		name = bdbo.getString("GRID_NAME");
		manager = bdbo.getString("GRID_MANAGER");
		address = bdbo.getString("GRID_ADDRESS");
		if (bdbo.containsField("GRID_COORDINATES")) {
			coordinates = new ArrayList<Coordinate>();
			BasicDBList bdbl = (BasicDBList) bdbo.get("GRID_COORDINATES");
			Iterator<Object> i = bdbl.iterator();
			while (i.hasNext()) {
				BasicDBObject o = (BasicDBObject) i.next();
				Coordinate coordinate = new Coordinate(o);
				coordinates.add(coordinate);
			}
		}
	}

	@Override
	public void run() {
		insert();
	}

	public boolean exist() {
		BasicDBObject bdbo = MongoDB.getInstance().findOne("grid",
				getBasicDBObject());
		if (null == bdbo)
			return false;
		else
			return true;
	}

	public boolean insert() {
		return MongoDB.getInstance().insert("grid", getBasicDBObject());
	}

	public boolean delete() {
		return MongoDB.getInstance().delete("grid", getBasicDBObject());
	}

	public boolean update(String json) {
		BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
		return MongoDB.getInstance().update("grid", getBasicDBObject(), bdbo);
	}

	public static Grid findOne(String json) {
		BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
		bdbo = MongoDB.getInstance().findOne("grid", bdbo);
		if (null == bdbo)
			return null;
		return new Grid(bdbo);
	}

	public static List<Grid> findList(String json) {
		List<Grid> gl = new ArrayList<Grid>();
		BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
		List<BasicDBObject> bdbol = MongoDB.getInstance()
				.findList("grid", bdbo);
		if (null == bdbol || bdbol.isEmpty())
			return null;
		for (BasicDBObject o : bdbol)
			gl.add(new Grid(o));
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
			return true;
		// return contains(grid.getAddress()); //网格按照地址判断实在太慢
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
		BasicDBObject bdbo = null;
		try {
			bdbo = (BasicDBObject) JSON.parse(json);
			if (null != bdbo && null != bdbo.getString("result")) {
				json = bdbo.getString("result");
				bdbo = (BasicDBObject) JSON.parse(json);
				if (null != bdbo.getString("location")) {
					json = bdbo.getString("location");
					bdbo = (BasicDBObject) JSON.parse(json);
					Double longitude = bdbo.getDouble("lng");
					Double latitude = bdbo.getDouble("lat");
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
		BasicDBObject bdbo = new BasicDBObject();
		if (null != code) {
			bdbo.append("GRID_CODE", code);
		}
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

}
