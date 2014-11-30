package cn.com.chinatelecom.map.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.scene.shape.Polygon;
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
		DBObject dbo = (DBObject) JSON.parse(json);
		setGrid(dbo);
	}

	public Grid(DBObject dbo) {
		setGrid(dbo);
	}

	private void setGrid(DBObject dbo) {
		if (dbo.get("GRID_CODE") != null) {
			code = dbo.get("GRID_CODE").toString();
		} else {
			return;
		}
		if (dbo.get("GRID_NAME") != null) {
			name = dbo.get("GRID_NAME").toString();
		}
		if (dbo.get("GRID_MANAGER") != null) {
			manager = dbo.get("GRID_MANAGER").toString();
		}
		if (dbo.get("GRID_ADDRESS") != null) {
			address = dbo.get("GRID_ADDRESS").toString();
		}
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
		if (dbo == null) {
			return false;
		} else {
			return true;
		}
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
		if (MongoDB.getInstance().findOne("grid", json) == null) {
			return null;
		}
		return new Grid(MongoDB.getInstance().findOne("grid", json));
	}

	public static List<Grid> findList(String json) {
		List<Grid> gl = new ArrayList<Grid>();
		List<DBObject> dbl = MongoDB.getInstance().findList("grid", json);
		if (dbl == null || dbl.isEmpty()) {
			return null;
		}
		for (DBObject dbo : dbl) {
			gl.add(new Grid(dbo));
		}
		return gl;
	}

	public boolean contains(Coordinate coordinate) {
		if (coordinates == null || coordinates.isEmpty() || coordinate == null) {
			return false;
		}

		if (getPolygon().contains(coordinate.getLongtitude(),
				coordinate.getLatitude())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean contains(Grid grid) {
		if (coordinates == null || coordinates.isEmpty() || grid == null) {
			return false;
		}

		Polygon polygon = getPolygon();
		List<Coordinate> points = grid.getCoordinates();
		for (Coordinate point : points) {
			if (!polygon.contains(point.getLongtitude(), point.getLatitude())) {
				return false;
			}
		}
		return true;
	}

	public Polygon getPolygon() {
		int size = coordinates.size();
		double[] points = new double[size * 2];
		for (int i = 0; i < size; i++) {
			points[i * 2] = coordinates.get(i).getLongtitude();
			points[i * 2 + 1] = coordinates.get(i).getLatitude();
		}
		Polygon polygon = new Polygon(points);
		return polygon;
	}

	public String toFetch() {
		StringBuffer sb = new StringBuffer("{c:'" + code + "'");
		if (address != null) {
			sb.append(",d:'" + address + "'");
		}
		if (coordinates != null && !coordinates.isEmpty()) {
			sb.append(",p:[");
			for (Coordinate coordinate : coordinates) {
				sb.append("{a:" + coordinate.getLatitude() + ",o:"
						+ coordinate.getLongtitude() + "},");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
		}
		sb.append("}");
		return sb.toString();
	}

	public String toInfo() {
		StringBuffer sb = new StringBuffer("网格名称:" + name + "<br/>");
		if (1 != code.length()) {
			sb.append("网格编号:" + code + "<br/>");
			sb.append("网格经理:" + manager + "<br/>");
			sb.append("网格地址:" + address);
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("{GRID_CODE:'" + code + "'");
		if (name != null) {
			sb.append(",GRID_NAME:'" + name + "'");
		}
		if (manager != null) {
			sb.append(",GRID_MANAGER:'" + manager + "'");
		}
		if (address != null) {
			sb.append(",GRID_ADDRESS:'" + address + "'");
		}
		if (coordinates != null && !coordinates.isEmpty()) {
			sb.append(",GRID_COORDINATES:[");
			for (Coordinate coordinate : coordinates) {
				sb.append("{LATITUDE:" + coordinate.getLatitude()
						+ ",LONGTITUDE:" + coordinate.getLongtitude() + "},");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
		}
		sb.append("}");
		return sb.toString();
	}
}
