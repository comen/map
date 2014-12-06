package cn.com.chinatelecom.map.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.common.MongoDB;
import cn.com.chinatelecom.map.utils.DateUtils;
import cn.com.chinatelecom.map.utils.MathUtils;
import cn.com.chinatelecom.map.utils.StringUtils;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * @author Shelwin
 *
 */
public class Data {

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
	
	public static String[] getNameOfMemberVariables() {
		String[] strArray = {
				"calculatedDate",
				"gridCode",
				"telephoneArrive",
				"broadbandArrive",
				"broadbandNew",
				"broadbandRemove",
				"broadbandMoveSetup",
				"broadbandMoveUnload",
				"broadbandOrderInTransit",
				"additional_1",
				"additional_2",
				"additional_3",
				"additional_4",
				"additional_5",
				"additional_6",
				"additional_7",
				"additional_8",
				"additional_9",
				"additional_10",
				"additional_11",
				"additional_12",
				"additional_13"
		}; 
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
		String[] namesOfMemVar = getNameOfMemberVariables();
		for (int i = 0; i < namesOfMemVar.length; i++) {
			if (dbo.get(namesOfMemVar[i]) != null) {
				setValue(namesOfMemVar[i], dbo.get(namesOfMemVar[i]));
			}
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
	
	private static Object getValueOfMemberVariables(Data data, String memberVariable) {
		return data.getValue(memberVariable);
	}
	
	public static Map<String, Object> getFieldDescAndQty(Date calculatedDate, String gridCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		Data data = new Data();
		data.setCalculatedDate(calculatedDate);
		data.setGridCode(gridCode);
		if (data.exist()) {
			data = Data.findOne(data.toString());
		} else {
			return null;
		}
		String[] namesOfMemVar = getNameOfMemberVariables();
		for (int i = 0; i < namesOfMemVar.length; i++) {
			if (namesOfMemVar[i].equalsIgnoreCase("calculatedDate") || namesOfMemVar[i].equalsIgnoreCase("gridCode")) {
				continue;
			}
			if (fieldIsOnUse(namesOfMemVar[i])) {
				String fieldDesc = getFieldDesc(namesOfMemVar[i]);
				int fieldQty = 0;
				try {
					fieldQty = Integer.parseInt(data.getValue(namesOfMemVar[i]).toString());
				} catch (Exception e) {
					String log = StringUtils.getLogPrefix(Level.SEVERE);
					System.out.println("\n" + log + "\n" + e.getClass()
							+ "\t:\t" + e.getMessage());
				}
				result.put(fieldDesc, fieldQty);
			}
		}
		
		return result;
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
	
	public static Data getDataOfDay(Date calculatedDate, String gridCode) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("'calculated_date':'" + calculatedDate.toString() + "'");
		sb.append(",'grid_code':'" + gridCode + "'");
		sb.append("}");
		return Data.findOne(sb.toString());
	}
	
	public static List<Data> getDataOfWeek(Date calculatedDate, String gridCode) {
        Date firstDayOfWeek = DateUtils.getFirstDayOfThisWeek(calculatedDate);
        Date lastDayOfWeek = DateUtils.getLastDayOfThisWeek(calculatedDate);
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("'calculated_date':{'$gte:" + firstDayOfWeek.getTime() + ",'$lte:'" + lastDayOfWeek.getTime() + "}");
		sb.append(",'grid_code':'" + gridCode + "'");
		sb.append("}");
		return Data.findList(sb.toString());
	}
	
	public static List<Data> getDataOfMonth(Date calculatedDate, String gridCode) {
		Date firstDayOfMonth = DateUtils.getFirstDayOfThisMonth(calculatedDate);
        Date lastDayOfMonth = DateUtils.getLastDayOfThisMonth(calculatedDate);
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("'calculated_date':{'$gte:" + firstDayOfMonth.getTime() + ",'$lte:'" + lastDayOfMonth.getTime() + "}");
		sb.append(",'grid_code':'" + gridCode + "'");
		sb.append("}");
		return Data.findList(sb.toString());
	}
	
	public static String getFieldSpecialDisplay(Date calculatedDate, String gridCode, String mode ) {
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
		} else if (mode.equalsIgnoreCase("Year")) {
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
			if (onlyDay > 0) { // field should be only displayed in DAY
				String jueduizhiThreshold = field.get("jueduizhiThreshold").toString();
				try {
					double toDouble = Double.parseDouble(jueduizhiThreshold);
					if ( Double.parseDouble(Integer.toString(value)) > toDouble) {
						green++;
					} else {
						normal++;
					}
				} catch (Exception e) { // jueduizhiThreshold = "*"
					normal++;
				}
			} else {
				String jueduizhiThreshold = field.get("jueduizhiThreshold").toString();
				try {
					double toDouble = Double.parseDouble(jueduizhiThreshold);
					int category = Integer.parseInt(field.get("category").toString());
					if (category > 0) { // good
						if (Double.parseDouble(Integer.toString(value)) > toDouble) {
							green++;
						} else {
							normal++;
						}
					} else { // bad
						if (Double.parseDouble(Integer.toString(value)) > toDouble) {
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
				for (int j = 0; j < dataListThisWeek.size(); j++) {
					Data data = dataListThisWeek.get(j);
					sumThisWeek = sumThisWeek + Integer.parseInt(getValueOfMemberVariables(data, namesOfMemVar[i]).toString());
				}
				int sumLastWeek = 0;
				for (int j = 0; j < dataListLastWeek.size(); j++) {
					Data data = dataListLastWeek.get(j);
					sumLastWeek = sumLastWeek + Integer.parseInt(getValueOfMemberVariables(data, namesOfMemVar[i]).toString());
				}
				double huanbiGrowthRate = MathUtils.calcuGrowthRate(sumThisWeek, sumLastWeek);
				/* Compare Huanbi growth rate with threshold */
				int category = Integer.parseInt(field.get("category").toString());
				String huanbiThreshold = field.get("huanbiThreshold").toString();
				try {
					double toDouble = Double.parseDouble(huanbiThreshold);
					if (category > 0) {	// good
						if (huanbiGrowthRate > toDouble) {
							green++;
						} else if (huanbiGrowthRate < toDouble) {
							red++;
						} else {
							normal++;
						}
					} else { // bad
						if (Math.abs(huanbiGrowthRate) > toDouble) {
							red++;
						} else if (Math.abs(huanbiGrowthRate) < toDouble) {
							green++;
						} else {
							normal++;
						}
					}
				} catch (Exception e) { // huanbiThreshold = "*"
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
				for (int j = 0; j < dataListThisMonth.size(); j++) {
					Data data = dataListThisMonth.get(j);
					sumThisMonth = sumThisMonth + Integer.parseInt(getValueOfMemberVariables(data, namesOfMemVar[i]).toString());
				}
				int sumLastMonth = 0;
				for (int j = 0; j < dataListLastMonth.size(); j++) {
					Data data = dataListLastMonth.get(j);
					sumLastMonth = sumLastMonth + Integer.parseInt(getValueOfMemberVariables(data, namesOfMemVar[i]).toString());
				}
				int sumThisMonthLastYear = 0;
				for (int j = 0; j < dataListThisMonthLastYear.size(); j++) {
					Data data = dataListThisMonthLastYear.get(j);
					sumThisMonthLastYear = sumThisMonthLastYear + Integer.parseInt(getValueOfMemberVariables(data, namesOfMemVar[i]).toString());
				}
				double huanbiGrowthRate = MathUtils.calcuGrowthRate(sumThisMonth, sumLastMonth);
				double tongbiGrowthRate = MathUtils.calcuGrowthRate(sumThisMonth, sumThisMonthLastYear);
				/* Compare Huanbi (& Tongbi) growth rate with threshold */
				int category = Integer.parseInt(field.get("category").toString());
				String huanbiThreshold = field.get("huanbiThreshold").toString();
				try {
					double toDouble = Double.parseDouble(huanbiThreshold);
					if (category > 0) { // good
						/* Huanbi */
						if (huanbiGrowthRate > toDouble) {
							green++;
						} else if (huanbiGrowthRate < toDouble) {
							red++;
						} else {
							normal++;
						}
						/* Tongbi */
						if (tongbiGrowthRate > toDouble) {
							green++;
						} else if (tongbiGrowthRate < toDouble) {
							red++;
						} else {
							normal++;
						}
					} else { // bad
						/* Huanbi */
						if (Math.abs(huanbiGrowthRate) > toDouble) {
							red++;
						} else if (Math.abs(huanbiGrowthRate) < toDouble) {
							green++;
						} else {
							normal++;
						}
						/* Tongbi */
						if (Math.abs(tongbiGrowthRate) > toDouble) {
							red++;
						} else if (Math.abs(tongbiGrowthRate) < toDouble) {
							green++;
						} else {
							normal++;
						}
					}
				} catch (Exception e) { // huanbiThreshold = "*"
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
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("'calculatedDate':" + calculatedDate.getTime());
		sb.append(",'gridCode':'" + gridCode + "'");
		if (telephoneArrive > 0) {
			sb.append(",'telephoneArrive':" + telephoneArrive);
		}
		if (broadbandArrive > 0) {
			sb.append(",'broadbandArrive':" + broadbandArrive);
		}
		if (broadbandNew > 0) {
			sb.append(",'broadbandNew':" + broadbandNew);
		}
		if (broadbandRemove > 0) {
			sb.append(",'broadbandRemove':" + broadbandRemove);
		}
		if (broadbandMoveSetup > 0) {
			sb.append(",'broadbandMoveSetup':" + broadbandMoveSetup);
		}
		if (broadbandMoveUnload > 0) {
			sb.append(",'broadbandMoveUnload':" + broadbandMoveUnload);
		}
		if (broadbandOrderInTransit > 0) {
			sb.append(",'broadbandOrderInTransit':" + broadbandOrderInTransit);
		}
		if (additional_1 > 0) {
			sb.append(",'additional_1':" + additional_1);
		}
		if (additional_2 > 0) {
			sb.append(",'additional_2':" + additional_2);
		}
		if (additional_3 > 0) {
			sb.append(",'additional_3':" + additional_3);
		}
		if (additional_4 > 0) {
			sb.append(",'additional_4':" + additional_4);
		}
		if (additional_5 > 0) {
			sb.append(",'additional_5':" + additional_5);
		}
		if (additional_6 > 0) {
			sb.append(",'additional_6':" + additional_6);
		}
		if (additional_7 > 0) {
			sb.append(",'additional_7':" + additional_7);
		}
		if (additional_8 > 0) {
			sb.append(",'additional_8':" + additional_8);
		}
		if (additional_9 > 0) {
			sb.append(",'additional_9':" + additional_9);
		}
		if (additional_10 > 0) {
			sb.append(",'additional_10':" + additional_10);
		}
		if (additional_11 > 0) {
			sb.append(",'additional_11':" + additional_11);
		}
		if (additional_12 > 0) {
			sb.append(",'additional_12':" + additional_12);
		}
		if (additional_13 > 0) {
			sb.append(",'additional_13':" + additional_13);
		}
		sb.append("}");
		return sb.toString();
	}

}
