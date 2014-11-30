package cn.com.chinatelecom.map.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.common.MongoDB;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * @author Shelwin
 *
 */
public class Data {

	private String calculatedDate;
	private String gridCode;
	private String telephoneArrive;
	private String broadbandArrive;
	private String broadbandNew;
	private String broadbandRemove;
	private String broadbandMoveSetup;
	private String broadbandMoveUnload;
	private String broadbandOrderInTransit;
	private String additional_1;
	private String additional_2;
	private String additional_3;
	
	public void setGridCode(String gridCode) {
		this.gridCode = gridCode;
	}
	
	public void setTelephoneArrive(String telephoneArrive) {
		this.telephoneArrive = telephoneArrive;
	}
	
	public void setBroadbandArrive(String broadbandArrive) {
		this.broadbandArrive = broadbandArrive;
	}
	
	public void setBroadbandNew(String broadbandNew) {
		this.broadbandNew = broadbandNew;
	}
	
	public void setBroadbandRemove(String broadbandRemove) {
		this.broadbandRemove = broadbandRemove;
	}
	
	public void setBroadbandMoveSetup(String broadbandMoveSetup) {
		this.broadbandMoveSetup = broadbandMoveSetup;
	}
	
	public void setBroadbandMoveUnload(String broadbandMoveUnload) {
		this.broadbandMoveUnload = broadbandMoveUnload;
	}
	
	public void setBroadbandOrderStringransit(String broadbandOrderInTransit) {
		this.broadbandOrderInTransit = broadbandOrderInTransit;
	}
	
	public void setCalculatedDate(String calculatedDate) {
		this.calculatedDate = calculatedDate;
	}
	
	public String getGridCode() {
		return gridCode;
	}
	
	public String getTelephoneArrive() {
		return telephoneArrive;
	}
	
	public String getBroadbandArrive() {
		return broadbandArrive;
	}
	
	public String getBroadbandNew() {
		return broadbandNew;
	}
	
	public String getBroadbandRemove() {
		return broadbandRemove;
	}
	
	public String getBroadbandMoveSetup() {
		return broadbandMoveSetup;
	}
	
	public String getBroadbandMoveUnload() {
		return broadbandMoveUnload;
	}
	
	public String getBroadbandOrderStringransit() {
		return broadbandOrderInTransit;
	}
	
	public String getCalculatedDate() {
		return calculatedDate;
	}
	
	public Data() {
		
	}
	
	public Data(String json) {
		DBObject dbo = (DBObject) JSON.parse(json);
		setData(dbo);
	}

	public Data(DBObject dbo) {
		setData(dbo);
	}
	
	private void setData(DBObject dbo) {
		if (dbo.get("calculated_date") == null || (dbo.get("grid_code") == null)) {
			return;
		}
		calculatedDate = dbo.get("calculated_date").toString();
		gridCode = dbo.get("grid_code").toString();
		if (dbo.get("telephone_arrive") != null) {
			telephoneArrive = dbo.get("telephone_arrive").toString();
		}
		if (dbo.get("broadband_arrive") != null) {
			broadbandArrive = dbo.get("broadband_arrive").toString();
		}
		if (dbo.get("broadband_new") != null) {
			broadbandNew = dbo.get("broadband_new").toString();
		}
		if (dbo.get("broadband_remove") != null) {
			broadbandRemove = dbo.get("broadband_remove").toString();
		}
		if (dbo.get("broadband_move_setup") != null) {
			broadbandMoveSetup = dbo.get("broadband_move_setup").toString();
		}
		if (dbo.get("broadband_move_unload") != null) {
			broadbandMoveUnload = dbo.get("broadband_move_unload").toString();
		}
		if (dbo.get("broadband_order_in_transit") != null) {
			broadbandOrderInTransit = dbo.get("broadband_order_in_transit").toString();
		}
	}
	
	public boolean exist() {
		DBObject dbo = MongoDB.getInstance().findOne("data", toString());
		if (dbo == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean insert() {
		return MongoDB.getInstance().insert("data", toString());
	}
	
	public boolean delete() {
		return MongoDB.getInstance().delete("data", toString());
	}
	
	public boolean update(String json) {
		return MongoDB.getInstance().update("data", toString(), json);
	}
	
	public static Data findOne(String json) {
		return new Data(MongoDB.getInstance().findOne("data", json));
	}
	
	public static List<Data> findList(String json) {
		List<Data> dl = new ArrayList<Data>();
		List<DBObject> dbl = MongoDB.getInstance().findList("data", json);
		for (DBObject dbo : dbl) {
			dl.add(new Data(dbo));
		}
		return dl;
	}
	
//	public static String getFieldSpecialDisplay(String calculatedDate, String gridCode) {
//		if (calculatedDate == null || gridCode == null) {
//			return null;
//		}
//		StringBuffer sb = new StringBuffer();
//		sb.append("{");
//		sb.append("'calculated_date':'" + calculatedDate + "'");
//		sb.append(",'grid_code':'" + gridCode + "'");
//		sb.append("}");
//		
//		Data data = Data.findOne(sb.toString());
//		if (data.calculatedDate == null || data.gridCode == null) {
//			return null;
//		}
//		
//		Config config = Config.getInstance();
//		Map<String, Object> result = new HashMap<String, Object>();
//		if (data.telephoneArrive != null) {
//			Map<String, Object> fieldAttr = (Map<String, Object>) JSON.parse(config.getValue("telephoneArrive"));
//			String name = fieldAttr.get("name").toString();
//			int onlyDay = Integer.parseInt(fieldAttr.get("onlyDay").toString());
//			String huanbiThreshold = fieldAttr.get("huanbiThreshold").toString();
//			String tongbiThreshold = fieldAttr.get("tongbiThreshold").toString();
//			int status = Integer.parseInt(fieldAttr.get("status").toString());
//			int category = Integer.parseInt(fieldAttr.get("category").toString());
//			if (status == 1 && category == 1) {
//				result.put(name, 1);
//			}
//		}
//		
//		return null;
//	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		if (calculatedDate != null) {
			sb.append("'calculated_date':'" + calculatedDate + "'");
		}
		if (gridCode != null) {
			sb.append(",'grid_code':'" + gridCode + "'");
		}
		if (telephoneArrive != null) {
			sb.append(",'telephone_arrive':'" + telephoneArrive + "'");
		}
		if (broadbandArrive != null) {
			sb.append(",'broadband_arrive':'" + broadbandArrive + "'");
		}
		if (broadbandNew != null) {
			sb.append(",'broadband_new':'" + broadbandNew + "'");
		}
		if (broadbandRemove != null) {
			sb.append(",'broadband_remove':'" + broadbandRemove + "'");
		}
		if (broadbandMoveSetup != null) {
			sb.append(",'broadband_move_setup':'" + broadbandMoveSetup + "'");
		}
		if (broadbandMoveUnload != null) {
			sb.append(",'broadband_move_unload':'" + broadbandMoveUnload + "'");
		}
		if (broadbandOrderInTransit != null) {
			sb.append(",'broadband_order_in_transit':'" + broadbandOrderInTransit + "'");
		}
		if (calculatedDate != null) {
			sb.append(",'calculated_date':'" + calculatedDate + "'");
		}
		sb.append("}");
		return sb.toString();
	}

}
