package cn.com.chinatelecom.map.entity;

import java.util.ArrayList;
import java.util.List;

import cn.com.chinatelecom.map.common.MongoDB;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * @author joseph
 *
 */
public class Data {

	private String office;					// 北区电信局 - 便于以后扩展，增加该字段
	private String suboffice;				// 分局
	private String gridCode;				// 网格
	private String telephoneArrive;			// 固话到达数
	private String broadbandArrive;			// 宽带到达数
	private String broadbandNew;			// 宽带新装
	private String broadbandRemove;			// 宽带拆机
	private String broadbandMoveSetup;		// 宽带移机（装）
	private String broadbandMoveUnload;		// 宽带移机（拆）
	private String broadbandOrderInTransit;	// 宽带在途订单
	private String calculatedDate;			// 统计日期
	
	
	public void setOffice(String office) {
		this.office = office;
	}
	
	public void setSuboffice(String suboffice) {
		this.suboffice = suboffice;
	}
	
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
	
	public String getOffice() {
		return office;
	}
	
	public String getSuboffice() {
		return suboffice;
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
	
	public Data(String json) {
		DBObject dbo = (DBObject) JSON.parse(json);
		setData(dbo);
	}

	public Data(DBObject dbo) {
		setData(dbo);
	}
	
	private void setData(DBObject dbo) {
		if (dbo.get("office") != null) {
			office = dbo.get("office").toString();
		} else {
			return;
		}
		if (dbo.get("suboffice") != null) {
			suboffice = dbo.get("suboffice").toString();
		} else {
			return;
		}
		if (dbo.get("grid_code") != null) {
			gridCode = dbo.get("grid_code").toString();
		} else {
			return;
		}
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
		if (dbo.get("calculated_date") != null) {
			calculatedDate = dbo.get("calculated_date").toString();
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
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("{'office':'" + office + "'");
		if (suboffice != null) {
			sb.append(",'suboffice':'" + suboffice + "'");
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
