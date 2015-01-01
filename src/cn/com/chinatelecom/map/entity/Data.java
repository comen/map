package cn.com.chinatelecom.map.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.common.MongoDB;
import cn.com.chinatelecom.map.utils.DateUtils;
import cn.com.chinatelecom.map.utils.MathUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

/**
 * @author Shelwin
 *
 */
public class Data implements Runnable {
	
	public static Logger logger = Logger.getLogger(Data.class);

	private Date calculatedDate;
	private String gridCode;
	private int telephoneArrive;
	private int broadbandArrive;
	private int broadbandNew;
	private int broadbandRemove;
	private int broadbandMoveSetup;
	private int broadbandMoveUnload;
	private int broadbandOrderInTransit;
	private int additional_1;
	private int additional_2;
	private int additional_3;
	private int additional_4;
	private int additional_5;
	private int additional_6;
	private int additional_7;
	private int additional_8;
	private int additional_9;
	private int additional_10;
	private int additional_11;
	private int additional_12;
	private int additional_13;
	
	private String address;
	private String salesDataType;

	public static String[] getNameOfMemberVariables() {
		String[] strArray = { "calculatedDate", "gridCode", "telephoneArrive",
				"broadbandArrive", "broadbandNew", "broadbandRemove",
				"broadbandMoveSetup", "broadbandMoveUnload",
				"broadbandOrderInTransit", "additional_1", "additional_2",
				"additional_3", "additional_4", "additional_5", "additional_6",
				"additional_7", "additional_8", "additional_9",
				"additional_10", "additional_11", "additional_12",
				"additional_13" };
		return strArray;
	}
	
	public void setValue(String nameOfMemberVariable, Object value) {
		if (nameOfMemberVariable.equalsIgnoreCase("calculatedDate")) {
			setCalculatedDate(new Date((long) value));
		} else if (nameOfMemberVariable.equalsIgnoreCase("gridCode")) {
			setGridCode((String) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("telephoneArrive")) {
			setTelephoneArrive((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("broadbandArrive")) {
			setBroadbandArrive((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("broadbandNew")) {
			setBroadbandNew((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("broadbandRemove")) {
			setBroadbandRemove((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("broadbandMoveSetup")) {
			setBroadbandMoveSetup((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("broadbandMoveUnload")) {
			setBroadbandMoveUnload((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("broadbandOrderInTransit")) {
			setBroadbandOrderInTransit((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_1")) {
			setAdditional_1((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_2")) {
			setAdditional_2((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_3")) {
			setAdditional_3((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_4")) {
			setAdditional_4((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_5")) {
			setAdditional_5((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_6")) {
			setAdditional_6((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_7")) {
			setAdditional_7((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_8")) {
			setAdditional_8((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_9")) {
			setAdditional_9((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_10")) {
			setAdditional_10((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_11")) {
			setAdditional_11((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_12")) {
			setAdditional_12((int) value);
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_13")) {
			setAdditional_13((int) value);
		}
	}

	public Object getValue(String nameOfMemberVariable) {
		if (nameOfMemberVariable.equalsIgnoreCase("calculatedDate")) {
			return getCalculatedDate();
		} else if (nameOfMemberVariable.equalsIgnoreCase("gridCode")) {
			return getGridCode();
		} else if (nameOfMemberVariable.equalsIgnoreCase("telephoneArrive")) {
			return getTelephoneArrive();
		} else if (nameOfMemberVariable.equalsIgnoreCase("broadbandArrive")) {
			return getBroadbandArrive();
		} else if (nameOfMemberVariable.equalsIgnoreCase("broadbandNew")) {
			return getBroadbandNew();
		} else if (nameOfMemberVariable.equalsIgnoreCase("broadbandRemove")) {
			return getBroadbandRemove();
		} else if (nameOfMemberVariable.equalsIgnoreCase("broadbandMoveSetup")) {
			return getBroadbandMoveSetup();
		} else if (nameOfMemberVariable.equalsIgnoreCase("broadbandMoveUnload")) {
			return getBroadbandMoveUnload();
		} else if (nameOfMemberVariable.equalsIgnoreCase("broadbandOrderInTransit")) {
			return getBroadbandOrderInTransit();
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_1")) {
			return getAdditional_1();
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_2")) {
			return getAdditional_2();
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_3")) {
			return getAdditional_3();
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_4")) {
			return getAdditional_4();
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_5")) {
			return getAdditional_5();
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_6")) {
			return getAdditional_6();
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_7")) {
			return getAdditional_7();
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_8")) {
			return getAdditional_8();
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_9")) {
			return getAdditional_9();
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_10")) {
			return getAdditional_10();
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_11")) {
			return getAdditional_11();
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_12")) {
			return getAdditional_12();
		} else if (nameOfMemberVariable.equalsIgnoreCase("additional_13")) {
			return getAdditional_13();
		} else {
			return null;
		}
	}
	
	public void setCalculatedDate(Date calculatedDate) {
		this.calculatedDate = calculatedDate;
	}
	
	public void setGridCode(String gridCode) {
		this.gridCode = gridCode;
	}
	
	public void setTelephoneArrive(int telephoneArrive) {
		this.telephoneArrive = telephoneArrive;
	}
	
	public void setBroadbandArrive(int broadbandArrive) {
		this.broadbandArrive = broadbandArrive;
	}
	
	public void setBroadbandNew(int broadbandNew) {
		this.broadbandNew = broadbandNew;
	}
	
	public void setBroadbandRemove(int broadbandRemove) {
		this.broadbandRemove = broadbandRemove;
	}
	
	public void setBroadbandMoveSetup(int broadbandMoveSetup) {
		this.broadbandMoveSetup = broadbandMoveSetup;
	}
	
	public void setBroadbandMoveUnload(int broadbandMoveUnload) {
		this.broadbandMoveUnload = broadbandMoveUnload;
	}
	
	public void setBroadbandOrderInTransit(int broadbandOrderInTransit) {
		this.broadbandOrderInTransit = broadbandOrderInTransit;
	}
	
	public void setAdditional_1(int additional_1) {
		this.additional_1 = additional_1;
	}
	
	public void setAdditional_2(int additional_2) {
		this.additional_2 = additional_2;
	}
	
	public void setAdditional_3(int additional_3) {
		this.additional_3 = additional_3;
	}
	
	public void setAdditional_4(int additional_4) {
		this.additional_4 = additional_4;
	}
	
	public void setAdditional_5(int additional_5) {
		this.additional_5 = additional_5;
	}
	
	public void setAdditional_6(int additional_6) {
		this.additional_6 = additional_6;
	}
	
	public void setAdditional_7(int additional_7) {
		this.additional_7 = additional_7;
	}
	
	public void setAdditional_8(int additional_8) {
		this.additional_8 = additional_8;
	}
	
	public void setAdditional_9(int additional_9) {
		this.additional_9 = additional_9;
	}
	
	public void setAdditional_10(int additional_10) {
		this.additional_10 = additional_10;
	}
	
	public void setAdditional_11(int additional_11) {
		this.additional_11 = additional_11;
	}
	
	public void setAdditional_12(int additional_12) {
		this.additional_12 = additional_12;
	}
	
	public void setAdditional_13(int additional_13) {
		this.additional_13 = additional_13;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setSalesDataType(String salesDataType) {
		this.salesDataType = salesDataType;
	}
	
	public Date getCalculatedDate() {
		return calculatedDate;
	}
	
	public String getGridCode() {
		return gridCode;
	}
	
	public int getTelephoneArrive() {
		return telephoneArrive;
	}
	
	public int getBroadbandArrive() {
		return broadbandArrive;
	}
	
	public int getBroadbandNew() {
		return broadbandNew;
	}
	
	public int getBroadbandRemove() {
		return broadbandRemove;
	}
	
	public int getBroadbandMoveSetup() {
		return broadbandMoveSetup;
	}
	
	public int getBroadbandMoveUnload() {
		return broadbandMoveUnload;
	}
	
	public int getBroadbandOrderInTransit() {
		return broadbandOrderInTransit;
	}
	
	public int getAdditional_1() {
		return additional_1;
	}
	
	public int getAdditional_2() {
		return additional_2;
	}
	
	public int getAdditional_3() {
		return additional_3;
	}
	
	public int getAdditional_4() {
		return additional_4;
	}
	
	public int getAdditional_5() {
		return additional_5;
	}
	
	public int getAdditional_6() {
		return additional_6;
	}
	
	public int getAdditional_7() {
		return additional_7;
	}
	
	public int getAdditional_8() {
		return additional_8;
	}
	
	public int getAdditional_9() {
		return additional_9;
	}
	
	public int getAdditional_10() {
		return additional_10;
	}
	
	public int getAdditional_11() {
		return additional_11;
	}
	
	public int getAdditional_12() {
		return additional_12;
	}
	
	public int getAdditional_13() {
		return additional_13;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getSalesDataType() {
		return salesDataType;
	}
	
	public Data() {
		
	}
	
	public Data(String json) {
		if (null != json) {
			try {
				BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
				setData(bdbo);
			} catch (Exception e) {
				logger.error("解析营销数据字符串错误: " + e.getMessage());
			}
		} else {
			logger.warn("待设置营销数据字符串为空！");
		}
	}

	public Data(BasicDBObject bdbo) {
		if (null != bdbo)
			setData(bdbo);
		else
			logger.warn("待设置营销数据数据库对象为空！");
	}

	private void setData(BasicDBObject bdbo) {
		String[] namesOfMemVar = getNameOfMemberVariables();
		for (int i = 0; i < namesOfMemVar.length; i++) {
			if (bdbo.get(namesOfMemVar[i]) != null) {
				setValue(namesOfMemVar[i], bdbo.get(namesOfMemVar[i]));
			}
		}
	}

	public boolean exist() {
		BasicDBObject bdbo = MongoDB.getInstance().findOne("data",
				getBasicDBObject());
		if (null == bdbo)
			return false;
		else
			return true;
	}
	
	public boolean insert() {
		if (calculatedDate == null || gridCode == null) {
			logger.error("营销数据ID为空！");
			return false;
		}
		return MongoDB.getInstance().insert("data", getBasicDBObject());
	}
	
	public boolean delete() {
		if (calculatedDate == null || gridCode == null) {
			logger.error("营销数据ID为空！");
			return false;
		}
		return MongoDB.getInstance().delete("data", getBasicDBObject());
	}
	
	@Deprecated
	public boolean update(String json) {
		if (calculatedDate == null || gridCode == null) {
			logger.error("营销数据ID为空！");
			return false;
		}
		BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
		return MongoDB.getInstance().update("data", getBasicDBObject(), bdbo);
	}
	
	public boolean update(BasicDBObject bdbo) {
		if (calculatedDate == null || gridCode == null) {
			logger.error("营销数据ID为空！");
			return false;
		}
		return MongoDB.getInstance().update("data", getBasicDBObject(), bdbo);
	}
	
	@Deprecated
	public static Data findOne(String json) {
		BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
		bdbo = MongoDB.getInstance().findOne("data", bdbo);
		if (null == bdbo)
			return null;
		return new Data(bdbo);
	}
	
	public static Data findOne(BasicDBObject bdbo) {
		bdbo = MongoDB.getInstance().findOne("data", bdbo);
		if (null == bdbo)
			return null;
		return new Data(bdbo);
	}
	
	@Deprecated
	public static List<Data> findList(String json) {
		List<Data> dl = new ArrayList<Data>();
		BasicDBObject bdbo = (BasicDBObject) JSON.parse(json);
		List<BasicDBObject> bdbol = MongoDB.getInstance().findList("data",
				bdbo);
		if (null == bdbol || bdbol.isEmpty())
			return null;
		for (BasicDBObject o : bdbol) {
			dl.add(new Data(o));
		}
		return dl;
	}
	
	public static List<Data> findList(BasicDBObject bdbo) {
		List<Data> dl = new ArrayList<Data>();
		List<BasicDBObject> bdbol = MongoDB.getInstance().findList("data",
				bdbo);
		if (null == bdbol || bdbol.isEmpty())
			return null;
		for (BasicDBObject o : bdbol) {
			dl.add(new Data(o));
		}
		return dl;
	}
	
	private static Object getValueOfMemberVariables(Data data, String memberVariable) {
		return data.getValue(memberVariable);
	}
	
	public static String getFieldDescAndQty(Date calculatedDate, String gridCode, String mode) {
		Data data = new Data();
		data.setCalculatedDate(calculatedDate);
		data.setGridCode(gridCode);
		/*if (!data.exist()) {
			return null;
		}*/
		if (mode.equalsIgnoreCase("Day")) {
			return getFieldDescAndQtyInDay(calculatedDate, gridCode);
		} else if (mode.equalsIgnoreCase("Week")) {
			return getFieldDescAndQtyInWeek(calculatedDate, gridCode);
		} else if (mode.equalsIgnoreCase("Month")) {
			return getFieldDescAndQtyInMonth(calculatedDate, gridCode);
		} else {
			return null;
		}
	}
	
	public static String getFieldDescAndQtyInDay(Date calculatedDate, String gridCode) {
		StringBuffer sb = new StringBuffer();
		Data data = getDataOfDay(calculatedDate, gridCode);
		
		String[] namesOfMemVar = getNameOfMemberVariables();
		for (int i = 0; i < namesOfMemVar.length; i++) {
			if (namesOfMemVar[i].equalsIgnoreCase("calculatedDate") || namesOfMemVar[i].equalsIgnoreCase("gridCode")) {
				continue;
			}
			if (fieldIsOnUse(namesOfMemVar[i])) {
				String fieldDesc = getFieldDesc(namesOfMemVar[i]);
				int fieldQty = 0;
				if (data == null) {
					sb.append(fieldDesc);
					sb.append("：");	//冒号
					sb.append(0);
					sb.append("；<br />"); //分号
				} else {
					try {
						fieldQty = Integer.parseInt(data.getValue(namesOfMemVar[i]).toString());
					} catch (Exception e) {
						logger.error("按日解析数据错误: " + e.getMessage());
					}
					sb.append(fieldDesc);
					sb.append("：");	//冒号
					sb.append(fieldQty);
					sb.append("；<br />"); //分号
				}
			}
		}
		return sb.toString();
	}
	
	public static String getFieldDescAndQtyInWeek(Date calculatedDate, String gridCode) {
		StringBuffer sb = new StringBuffer();
		List<Data> dataListThisWeek = getDataOfWeek(calculatedDate, gridCode);
		List<Data> dataListLastWeek = getDataOfWeek(DateUtils.getFirstDayOfLastWeek(calculatedDate), gridCode);
		
		Config config = Config.getInstance();
		String[] namesOfMemVar = getNameOfMemberVariables();
		
		for (int i = 0; i < namesOfMemVar.length; i++) {
			if (namesOfMemVar[i].equalsIgnoreCase("calculatedDate") || namesOfMemVar[i].equalsIgnoreCase("gridCode")) {
				continue;
			}
			@SuppressWarnings("unchecked")
			Map<String, Object> field = (Map<String, Object>) JSON.parse(config.getValue(namesOfMemVar[i]));
			
			int status = Integer.parseInt(field.get("status").toString());
			if (status < 0) { // field not in use
				continue;
			}

			int onlyDay = Integer.parseInt(field.get("onlyDay").toString());
			if (onlyDay > 0) { // field should be only displayed in DAY
				continue;
			} else {
				/* Calculate Huanbi growth rate comparing this week with last week */
				int sumThisWeek = 0;
				if (dataListThisWeek != null) {
					for (int j = 0; j < dataListThisWeek.size(); j++) {
						Data data = dataListThisWeek.get(j);
						sumThisWeek = sumThisWeek + Integer.parseInt(getValueOfMemberVariables(data, namesOfMemVar[i]).toString());
					}
				}
				int sumLastWeek = 0;
				if (dataListLastWeek != null) {
					for (int j = 0; j < dataListLastWeek.size(); j++) {
						Data data = dataListLastWeek.get(j);
						sumLastWeek = sumLastWeek + Integer.parseInt(getValueOfMemberVariables(data, namesOfMemVar[i]).toString());
					}
				}
				int huanbiGrowth = MathUtils.calcuGrowth(sumThisWeek, sumLastWeek);
				
				String fieldDesc = getFieldDesc(namesOfMemVar[i]);
				sb.append(fieldDesc);
				sb.append("：");	//冒号
				sb.append(sumThisWeek);
				sb.append("，&nbsp;"); //逗号
				
				sb.append("环比：");
				if (gridCode.length() < 2) { //区局、分局级
					if (sumLastWeek != 0) {
						double huanbiGrowthRate = MathUtils.getTitude(MathUtils.calcuGrowthRate(sumThisWeek, sumLastWeek) * 100, 2); //百分比形式
						sb.append(huanbiGrowthRate);
						sb.append("%；<br />");
					} else {
						sb.append("无");
						sb.append("%；<br />");
					}
				} else { //网格级
					sb.append(huanbiGrowth);
					sb.append("<br />");
				}
				
			}
		}
		return sb.toString();
	}
	
	public static String getFieldDescAndQtyInMonth(Date calculatedDate, String gridCode) {
		StringBuffer sb = new StringBuffer();
		List<Data> dataListThisMonth = getDataOfMonth(calculatedDate, gridCode);
		List<Data> dataListLastMonth = getDataOfMonth(DateUtils.getFirstDayOfLastMonth(calculatedDate), gridCode);
		List<Data> dataListThisMonthLastYear = getDataOfMonth(DateUtils.getFirstDayOfThisMonthLastYear(calculatedDate), gridCode);
		
		Config config = Config.getInstance();
		String[] namesOfMemVar = getNameOfMemberVariables();
		
		for (int i = 0; i < namesOfMemVar.length; i++) {
			if (namesOfMemVar[i].equalsIgnoreCase("calculatedDate") || namesOfMemVar[i].equalsIgnoreCase("gridCode")) {
				continue;
			}
			@SuppressWarnings("unchecked")
			Map<String, Object> field = (Map<String, Object>) JSON.parse(config.getValue(namesOfMemVar[i]));
			
			int status = Integer.parseInt(field.get("status").toString());
			if (status < 0) { // field not in use
				continue;
			}
			int onlyDay = Integer.parseInt(field.get("onlyDay").toString());
			if (onlyDay > 0) { // field should be only displayed in DAY
				continue;
			} else {
				/* Calculate Huanbi (& Tongbi) growth rate comparing this month with last month (& same month in last year) */
				int sumThisMonth = 0;
				if (dataListThisMonth != null) {
					for (int j = 0; j < dataListThisMonth.size(); j++) {
						Data data = dataListThisMonth.get(j);
						sumThisMonth = sumThisMonth + Integer.parseInt(getValueOfMemberVariables(data, namesOfMemVar[i]).toString());
					}
				}
				int sumLastMonth = 0;
				if (dataListLastMonth != null) {
					for (int j = 0; j < dataListLastMonth.size(); j++) {
						Data data = dataListLastMonth.get(j);
						sumLastMonth = sumLastMonth + Integer.parseInt(getValueOfMemberVariables(data, namesOfMemVar[i]).toString());
					}
				}
				int sumThisMonthLastYear = 0;
				if (dataListThisMonthLastYear != null) {
					for (int j = 0; j < dataListThisMonthLastYear.size(); j++) {
						Data data = dataListThisMonthLastYear.get(j);
						sumThisMonthLastYear = sumThisMonthLastYear + Integer.parseInt(getValueOfMemberVariables(data, namesOfMemVar[i]).toString());
					}
				}
				int huanbiGrowth = MathUtils.calcuGrowth(sumThisMonth, sumLastMonth);
				int tongbiGrowth = MathUtils.calcuGrowth(sumThisMonth, sumThisMonthLastYear);
				
				String fieldDesc = getFieldDesc(namesOfMemVar[i]);
				sb.append(fieldDesc);
				sb.append("：");	//冒号
				sb.append(sumThisMonth);
				sb.append("，&nbsp;"); //逗号
				
				sb.append("环比：");
				if (gridCode.length() < 2) { //区局、分局级
					if (sumLastMonth != 0) {
						double huanbiGrowthRate = MathUtils.getTitude(MathUtils.calcuGrowthRate(sumThisMonth, sumLastMonth) * 100, 2); //百分比形式
						sb.append(huanbiGrowthRate);
						sb.append("%，&nbsp;");
					} else {
						sb.append("无");
						sb.append("%，&nbsp;");
					}
				} else { //网格级
					sb.append(huanbiGrowth);
					sb.append("，&nbsp;");
				}
				
				sb.append("同比：");
				if (gridCode.length() < 2) { //区局、分局级
					if (sumThisMonthLastYear != 0) {
						double tongbiGrowthRate = MathUtils.getTitude(MathUtils.calcuGrowthRate(sumThisMonth, sumThisMonthLastYear) * 100, 2); //百分比形式
						sb.append(tongbiGrowthRate);
						sb.append("%；<br />");
					} else {
						sb.append("无");
						sb.append("%；<br />");
					}
				} else { //网格级
					sb.append(tongbiGrowth);
					sb.append("<br />");
				}
				
			}
		}
		return sb.toString();
	}
	
	public static boolean fieldIsOnUse(String memberVariable) {
		Config config = Config.getInstance();
		@SuppressWarnings("unchecked")
		Map<String, Object> field = (Map<String, Object>) JSON.parse(config.getValue(memberVariable));

		int status = Integer.parseInt(field.get("status").toString());
		if (status < 0) { // field not in use
			return false;
		} else {
			return true;
		}
	}
	
	public static String getFieldDesc(String memberVariable) {
		Config config = Config.getInstance();
		@SuppressWarnings("unchecked")
		Map<String, Object> field = (Map<String, Object>) JSON.parse(config.getValue(memberVariable));
		return field.get("description").toString();
	}
	
	public static String getOnlyDay(String memberVariable) {
		Config config = Config.getInstance();
		@SuppressWarnings("unchecked")
		Map<String, Object> field = (Map<String, Object>) JSON.parse(config.getValue(memberVariable));
		return field.get("onlyDay").toString();
	}
	
	public static String getJueduizhiThreshold(String memberVariable) {
		Config config = Config.getInstance();
		@SuppressWarnings("unchecked")
		Map<String, Object> field = (Map<String, Object>) JSON.parse(config.getValue(memberVariable));
		return field.get("jueduizhiThreshold").toString();
	}
	
	public static String getHuanbiThreshold(String memberVariable) {
		Config config = Config.getInstance();
		@SuppressWarnings("unchecked")
		Map<String, Object> field = (Map<String, Object>) JSON.parse(config.getValue(memberVariable));
		return field.get("huanbiThreshold").toString();
	}
	
	public static String getTongbiThreshold(String memberVariable) {
		Config config = Config.getInstance();
		@SuppressWarnings("unchecked")
		Map<String, Object> field = (Map<String, Object>) JSON.parse(config.getValue(memberVariable));
		return field.get("tongbiThreshold").toString();
	}
	
	public static String getStatus(String memberVariable) {
		Config config = Config.getInstance();
		@SuppressWarnings("unchecked")
		Map<String, Object> field = (Map<String, Object>) JSON.parse(config.getValue(memberVariable));
		return field.get("status").toString();
	}
	
	public static String getCategory(String memberVariable) {
		Config config = Config.getInstance();
		@SuppressWarnings("unchecked")
		Map<String, Object> field = (Map<String, Object>) JSON.parse(config.getValue(memberVariable));
		return field.get("category").toString();
	}
	
	public static Data getDataOfDay(Date calculatedDate, String gridCode) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("'calculatedDate':" + calculatedDate.getTime());
		sb.append(",'gridCode':'" + gridCode + "'");
		sb.append("}");
		return Data.findOne(sb.toString());
	}
	
	public static List<Data> getDataOfWeek(Date calculatedDate, String gridCode) {
        Date firstDayOfWeek = DateUtils.getFirstDayOfThisWeek(calculatedDate);
        Date lastDayOfWeek = DateUtils.getLastDayOfThisWeek(calculatedDate);
		
        StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("'calculatedDate':{$gte:" + firstDayOfWeek.getTime() + ",$lte:" + lastDayOfWeek.getTime() + "}");
		sb.append(",'gridCode':'" + gridCode + "'");
		sb.append("}");
		return Data.findList(sb.toString());
	}
	
	public static List<Data> getDataOfMonth(Date calculatedDate, String gridCode) {
		Date firstDayOfMonth = DateUtils.getFirstDayOfThisMonth(calculatedDate);
        Date lastDayOfMonth = DateUtils.getLastDayOfThisMonth(calculatedDate);
        
        StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("'calculatedDate':{$gte:" + firstDayOfMonth.getTime() + ",$lte:" + lastDayOfMonth.getTime() + "}");
		sb.append(",'gridCode':'" + gridCode + "'");
		sb.append("}");
		return Data.findList(sb.toString());
	}
	
	public static String getFieldSpecialDisplay(Date calculatedDate, String gridCode, String mode) {
		Data data = new Data();
		data.setCalculatedDate(calculatedDate);
		data.setGridCode(gridCode);
		if (!data.exist()) {
			return null;
		}
		if (mode.equalsIgnoreCase("Day")) {
			return getFieldSpecialDisplayInDay(calculatedDate, gridCode);
		} else if (mode.equalsIgnoreCase("Week")) {
			return getFieldSpecialDisplayInWeek(calculatedDate, gridCode);
		} else if (mode.equalsIgnoreCase("Month")) {
			return getFieldSpecialDisplayInMonth(calculatedDate, gridCode);
		} else {
			return null;
		}
	}
	
	public static String getFieldSpecialDisplayInDay(Date calculatedDate, String gridCode) {
		Data data = getDataOfDay(calculatedDate, gridCode);
		Config config = Config.getInstance();
		String[] namesOfMemVar = getNameOfMemberVariables();
		int red = 0;
		int green = 0;
		int normal = 0;
		
		for (int i = 0; i < namesOfMemVar.length; i++) {
			if (namesOfMemVar[i].equalsIgnoreCase("calculatedDate") || namesOfMemVar[i].equalsIgnoreCase("gridCode")) {
				continue;
			}
			@SuppressWarnings("unchecked")
			Map<String, Object> field = (Map<String, Object>) JSON.parse(config.getValue(namesOfMemVar[i]));
			int status = Integer.parseInt(field.get("status").toString());
			if (status < 0) { // field not in use
				continue;
			}
			
			int value = Integer.parseInt(getValueOfMemberVariables(data, namesOfMemVar[i]).toString());
			int onlyDay = Integer.parseInt(field.get("onlyDay").toString());
			String jueduizhiThreshold = field.get("jueduizhiThreshold").toString();
			if (onlyDay > 0) { // field should be only displayed in DAY
				try {
					double jueduizhiThresholdInDouble = Double.parseDouble(jueduizhiThreshold);
					if (Double.parseDouble(Integer.toString(value)) > jueduizhiThresholdInDouble) {
						green++;
					} else {
						normal++;
					}
				} catch (Exception e) { // jueduizhiThreshold = "*"
					normal++;
				}
			} else {
				try {
					double jueduizhiThresholdInDouble = Double.parseDouble(jueduizhiThreshold);
					int category = Integer.parseInt(field.get("category").toString());
					if (category > 0) { // good
						if (Double.parseDouble(Integer.toString(value)) > jueduizhiThresholdInDouble) {
							green++;
						} else {
							normal++;
						}
					} else { // bad
						if (Double.parseDouble(Integer.toString(value)) > jueduizhiThresholdInDouble) {
							red++;
						} else {
							normal++;
						}
					}
				} catch (Exception e) { // jueduizhiThreshold = "*"
					normal++;
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("'R':" + Integer.toString(red));
		sb.append(",'G':" + Integer.toString(green));
		sb.append(",'B':" + Integer.toString(normal));
		sb.append("}");
		
		return sb.toString();
	}
	
	public static String getFieldSpecialDisplayInWeek(Date calculatedDate, String gridCode) {
		List<Data> dataListThisWeek = getDataOfWeek(calculatedDate, gridCode);
		List<Data> dataListLastWeek = getDataOfWeek(DateUtils.getFirstDayOfLastWeek(calculatedDate), gridCode);
		
		Config config = Config.getInstance();
		String[] namesOfMemVar = getNameOfMemberVariables();
		int red = 0;
		int green = 0;
		int normal = 0;
		
		for (int i = 0; i < namesOfMemVar.length; i++) {
			if (namesOfMemVar[i].equalsIgnoreCase("calculatedDate") || namesOfMemVar[i].equalsIgnoreCase("gridCode")) {
				continue;
			}
			@SuppressWarnings("unchecked")
			Map<String, Object> field = (Map<String, Object>) JSON.parse(config.getValue(namesOfMemVar[i]));
			
			int status = Integer.parseInt(field.get("status").toString());
			if (status < 0) { // field not in use
				continue;
			}
			int onlyDay = Integer.parseInt(field.get("onlyDay").toString());
			if (onlyDay > 0) { // field should be only displayed in DAY
				continue;
			} else {
				/* Calculate Huanbi growth rate comparing this week with last week */
				int sumThisWeek = 0;
				if (dataListThisWeek != null) {
					for (int j = 0; j < dataListThisWeek.size(); j++) {
						Data data = dataListThisWeek.get(j);
						sumThisWeek = sumThisWeek + Integer.parseInt(getValueOfMemberVariables(data, namesOfMemVar[i]).toString());
					}
				}
				int sumLastWeek = 0;
				if (dataListLastWeek != null) {
					for (int j = 0; j < dataListLastWeek.size(); j++) {
						Data data = dataListLastWeek.get(j);
						sumLastWeek = sumLastWeek + Integer.parseInt(getValueOfMemberVariables(data, namesOfMemVar[i]).toString());
					}
				}
				
				if (sumLastWeek != 0) {
					double huanbiGrowthRate = MathUtils.getTitude(MathUtils.calcuGrowthRate(sumThisWeek, sumLastWeek) * 100, 2); //百分比形式
					/* Compare Huanbi growth rate with threshold */
					int category = Integer.parseInt(field.get("category").toString());
					String huanbiThreshold = field.get("huanbiThreshold").toString();
					try {
						double huanbiThresholdInDouble = Double.parseDouble(huanbiThreshold);
						if (category > 0) {	// good
							if (huanbiGrowthRate > huanbiThresholdInDouble) {
								green++;
							} else if (huanbiGrowthRate < huanbiThresholdInDouble) {
								red++;
							} else {
								normal++;
							}
						} else { // bad
							if (huanbiGrowthRate > 0 || Math.abs(huanbiGrowthRate) < huanbiThresholdInDouble) {
								red++;
							} else if (Math.abs(huanbiGrowthRate) > huanbiThresholdInDouble) {
								green++;
							} else {
								normal++;
							}
						}
					} catch (Exception e) { // huanbiThreshold = "*"
						normal++;
					}
				} else {
					normal++;
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("'R':" + Integer.toString(red));
		sb.append(",'G':" + Integer.toString(green));
		sb.append(",'B':" + Integer.toString(normal));
		sb.append("}");
		
		return sb.toString();
	}
	
	public static String getFieldSpecialDisplayInMonth(Date calculatedDate, String gridCode) {
		List<Data> dataListThisMonth = getDataOfMonth(calculatedDate, gridCode);
		List<Data> dataListLastMonth = getDataOfMonth(DateUtils.getFirstDayOfLastMonth(calculatedDate), gridCode);
		List<Data> dataListThisMonthLastYear = getDataOfMonth(DateUtils.getFirstDayOfThisMonthLastYear(calculatedDate), gridCode);
		
		Config config = Config.getInstance();
		String[] namesOfMemVar = getNameOfMemberVariables();
		int red = 0;
		int green = 0;
		int normal = 0;
		
		for (int i = 0; i < namesOfMemVar.length; i++) {
			if (namesOfMemVar[i].equalsIgnoreCase("calculatedDate") || namesOfMemVar[i].equalsIgnoreCase("gridCode")) {
				continue;
			}
			@SuppressWarnings("unchecked")
			Map<String, Object> field = (Map<String, Object>) JSON.parse(config.getValue(namesOfMemVar[i]));
			
			int status = Integer.parseInt(field.get("status").toString());
			if (status < 0) { // field not in use
				continue;
			}

			int onlyDay = Integer.parseInt(field.get("onlyDay").toString());
			if (onlyDay > 0) { // field should be only displayed in DAY
				continue;
			} else {
				/* Calculate Huanbi (& Tongbi) growth rate comparing this month with last month (& same month in last year) */
				int sumThisMonth = 0;
				if (dataListThisMonth != null) {
					for (int j = 0; j < dataListThisMonth.size(); j++) {
						Data data = dataListThisMonth.get(j);
						sumThisMonth = sumThisMonth + Integer.parseInt(getValueOfMemberVariables(data, namesOfMemVar[i]).toString());
					}
				}
				int sumLastMonth = 0;
				if (dataListLastMonth != null) {
					for (int j = 0; j < dataListLastMonth.size(); j++) {
						Data data = dataListLastMonth.get(j);
						sumLastMonth = sumLastMonth + Integer.parseInt(getValueOfMemberVariables(data, namesOfMemVar[i]).toString());
					}
				}
				int sumThisMonthLastYear = 0;
				if (dataListThisMonthLastYear != null) {
					for (int j = 0; j < dataListThisMonthLastYear.size(); j++) {
						Data data = dataListThisMonthLastYear.get(j);
						sumThisMonthLastYear = sumThisMonthLastYear + Integer.parseInt(getValueOfMemberVariables(data, namesOfMemVar[i]).toString());
					}
				}
				
				/* Compare Huanbi (& Tongbi) growth rate with threshold */
				int category = Integer.parseInt(field.get("category").toString());
				/* Huanbi */
				if (sumLastMonth != 0) {
					double huanbiGrowthRate = MathUtils.getTitude(MathUtils.calcuGrowthRate(sumThisMonth, sumLastMonth) * 100, 2); //百分比形式
					String huanbiThreshold = field.get("huanbiThreshold").toString();
					try {
						double huanbiThresholdInDouble = Double.parseDouble(huanbiThreshold);
						if (category > 0) { // good
							/* Huanbi */
							if (huanbiGrowthRate > huanbiThresholdInDouble) {
								green++;
							} else if (huanbiGrowthRate < huanbiThresholdInDouble) {
								red++;
							} else {
								normal++;
							}
						} else { // bad
							/* Huanbi */
							if (huanbiGrowthRate > 0 || Math.abs(huanbiGrowthRate) < huanbiThresholdInDouble) {
								red++;
							} else if (Math.abs(huanbiGrowthRate) > huanbiThresholdInDouble) {
								green++;
							} else {
								normal++;
							}
						}
					} catch (Exception e) { // huanbiThreshold = "*"
						normal++;
					}
				} else {
					normal++;
				}
				/* Tongbi */
				if (sumThisMonthLastYear != 0) {
					double tongbiGrowthRate = MathUtils.getTitude(MathUtils.calcuGrowthRate(sumThisMonth, sumThisMonthLastYear) * 100, 2); //百分比形式
					String tongbiThreshold = field.get("tongbiThreshold").toString();
					try {
						double tongbiThresholdInDouble = Double.parseDouble(tongbiThreshold);
						if (category > 0) { // good
							/* Tongbi */
							if (tongbiGrowthRate > tongbiThresholdInDouble) {
								green++;
							} else if (tongbiGrowthRate < tongbiThresholdInDouble) {
								red++;
							} else {
								normal++;
							}
						} else { // bad
							/* Tongbi */
							if (tongbiGrowthRate > 0 || Math.abs(tongbiGrowthRate) < tongbiThresholdInDouble) {
								red++;
							} else if (Math.abs(tongbiGrowthRate) > tongbiThresholdInDouble) {
								green++;
							} else {
								normal++;
							}
						}
					} catch (Exception e) { // tongbiThreshold = "*"
						normal++;
					}
				} else {
					normal++;
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("'R':" + Integer.toString(red));
		sb.append(",'G':" + Integer.toString(green));
		sb.append(",'B':" + Integer.toString(normal));
		sb.append("}");
		
		return sb.toString();
	}
	
	public BasicDBObject getBasicDBObject() {
		BasicDBObject bdbo = new BasicDBObject();
		if (calculatedDate != null) {
			//转化成UTC时间保存至数据库
			bdbo.append("calculatedDate", calculatedDate.getTime());
		}
		if (gridCode != null) {
			bdbo.append("gridCode", gridCode);
		}
		if (telephoneArrive > 0) {
			bdbo.append("telephoneArrive", telephoneArrive);
		}
		if (broadbandArrive > 0) {
			bdbo.append("broadbandArrive", broadbandArrive);
		}
		if (broadbandNew > 0) {
			bdbo.append("broadbandNew", broadbandNew);
		}
		if (broadbandRemove > 0) {
			bdbo.append("broadbandRemove", broadbandRemove);
		}
		if (broadbandMoveSetup > 0) {
			bdbo.append("broadbandMoveSetup", broadbandMoveSetup);
		}
		if (broadbandMoveUnload > 0) {
			bdbo.append("broadbandMoveUnload", broadbandMoveUnload);
		}
		if (broadbandOrderInTransit > 0) {
			bdbo.append("broadbandOrderInTransit", broadbandOrderInTransit);
		}
		if (additional_1 > 0) {
			bdbo.append("additional_1", additional_1);
		}
		if (additional_2 > 0) {
			bdbo.append("additional_2", additional_2);
		}
		if (additional_3 > 0) {
			bdbo.append("additional_3", additional_3);
		}
		if (additional_4 > 0) {
			bdbo.append("additional_4", additional_4);
		}
		if (additional_5 > 0) {
			bdbo.append("additional_5", additional_5);
		}
		if (additional_6 > 0) {
			bdbo.append("additional_6", additional_6);
		}
		if (additional_7 > 0) {
			bdbo.append("additional_7", additional_7);
		}
		if (additional_8 > 0) {
			bdbo.append("additional_8", additional_8);
		}
		if (additional_9 > 0) {
			bdbo.append("additional_9", additional_9);
		}
		if (additional_10 > 0) {
			bdbo.append("additional_10", additional_10);
		}
		if (additional_11 > 0) {
			bdbo.append("additional_11", additional_11);
		}
		if (additional_12 > 0) {
			bdbo.append("additional_12", additional_12);
		}
		if (additional_13 > 0) {
			bdbo.append("additional_13", additional_13);
		}
		
		return bdbo;
	}

	@Override
	public String toString() {
		return getBasicDBObject().toString();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (address == null || address.equals("")) {
			return;
		}
		Grid grid = Grid.search(address);
		if (grid == null) {
			logger.warn("安装地址未落在任何网格中： " + address);
			return;
		} else {
			gridCode = grid.getCode();
		}
		if (exist()) {
			Data dataTmp = Data.findOne(getBasicDBObject());
			if (dataTmp.getValue(salesDataType) == null) {
				dataTmp.setValue(salesDataType, 1);
			} else {
				dataTmp.setValue(salesDataType, Integer.parseInt(dataTmp.getValue(salesDataType).toString()) + 1);
			}
			update(dataTmp.getBasicDBObject());
		} else {
			setValue(salesDataType, 1);
			insert();
		}
	}

}
