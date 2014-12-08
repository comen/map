/**
 * 
 */
package cn.com.chinatelecom.map.handle;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.fileupload.FileItem;

import com.mongodb.util.JSON;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.entity.Data;
import cn.com.chinatelecom.map.entity.Grid;
import cn.com.chinatelecom.map.utils.DateUtils;
import cn.com.chinatelecom.map.utils.FileUtils;
import cn.com.chinatelecom.map.utils.StringUtils;

/**
 * @author Shelwin
 *
 */
public class UploadSalesDataHandler implements IHandler {

	/* (non-Javadoc)
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		String salesDataType = "";
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				if (fieldName.equals("salesDataType")) {
					salesDataType = item.getString();
				}
			} else {
				if (!salesDataType.equals("")) {
					String filename = item.getName().trim();
					File file = new File(Config.getInstance()
							.getValue("uploadPath") + "/" + filename);
					
					try {
						FileUtils.writeFile(item.getInputStream(), file);
					} catch (Exception e) {
						String log = StringUtils.getLogPrefix(Level.SEVERE);
						System.out.println("\n" + log + "\n" + e.getClass()
								+ "\t:\t" + e.getMessage());
						return result;
					}
					
					String content = FileUtils.readFile(file);
					String[] splits = content.split("<br/>");
					
					for (String split : splits) {
						if (!split.trim().equals("")) {
							@SuppressWarnings("unchecked")
							Map<String, Object> fileLine = (Map<String, Object>) JSON.parse(split);
							String gridCode = (String) fileLine.get(Config.getInstance().getValue("gridCodeColumn"));
							String office = (String) fileLine.get(Config.getInstance().getValue("officeColumn"));
							String suboffice = (String) fileLine.get(Config.getInstance().getValue("subofficeColumn"));
							String dateStr = (String) fileLine.get(Config.getInstance().getValue("dateColumn"));
							String address = (String) fileLine.get(Config.getInstance().getValue("addressColumn"));
							
							if (office == null || office.equals("") || suboffice == null || suboffice.equals("")) {
								continue;
							}
							
							Date calculatedDate = DateUtils.getCurrentDate();
							if (!(dateStr == null || dateStr.equals(""))) {
								calculatedDate = DateUtils.getSpecificDate(dateStr, Config.getInstance().getValue("dateFormat"));
							}
							
							try {
								/* Accumulate for Office */
								if (office.equals("北区") || office.equals("北区局")) {
									Data officeData = new Data();
									officeData.setCalculatedDate(calculatedDate);
									officeData.setGridCode("0");
									if (officeData.exist()) {
										Data dataTmp = Data.findOne(officeData.toString());
										if (dataTmp.getValue(salesDataType) != null) {
											dataTmp.setValue(salesDataType, Integer.parseInt(dataTmp.getValue(salesDataType).toString()) + 1);
										} else {
											dataTmp.setValue(salesDataType, 1);
										}
										officeData.update(dataTmp.toString());
									} else {
										officeData.setValue(salesDataType, Integer.parseInt(officeData.getValue(salesDataType).toString()) + 1);
										officeData.insert();
									}
									/* Accumulate for Sub-office */
									Data subOfficeData = new Data();
									boolean subOfficeOK = true;
									subOfficeData.setCalculatedDate(calculatedDate);
									switch (suboffice) {
									case "海川":
									case "海川分局":
										subOfficeData.setGridCode("1");
										break;
									case "甘泉":
									case "甘泉分局":
										subOfficeData.setGridCode("2");
										break;
									case "平顺":
									case "平顺分局":
										subOfficeData.setGridCode("3");
										break;
									case "和田":
									case "和田分局":
										subOfficeData.setGridCode("4");
										break;
									default:
										subOfficeOK = false;
										break;
									}
									if (subOfficeOK) {
										if (subOfficeData.exist()) {
											Data dataTmp = Data.findOne(subOfficeData.toString());
											if (dataTmp.getValue(salesDataType) != null) {
												dataTmp.setValue(salesDataType, Integer.parseInt(dataTmp.getValue(salesDataType).toString()) + 1);
											} else {
												dataTmp.setValue(salesDataType, 1);
											}
											subOfficeData.update(dataTmp.toString());
										} else {
											subOfficeData.setValue(salesDataType, 1);
											subOfficeData.insert();
										}
										/* Accumulate for Grid */
										if (gridCode == null || gridCode.equals("")) {
											if (address == null || address.equals("")) {
												continue;
											} else {
												/*if (!address.startsWith("上海")) {
													address = "上海市" + address;
												}*/
												Grid grid = Grid.search(address);
												if (grid != null) {
													gridCode = grid.getCode();
												} else {
													continue;
												}
											}
										}
										Data gridData = new Data();
										gridData.setCalculatedDate(calculatedDate);
										gridData.setGridCode(gridCode);
										if (gridData.exist()) {
											Data dataTmp = Data.findOne(gridData.toString());
											if (dataTmp.getValue(salesDataType) != null) {
												dataTmp.setValue(salesDataType, Integer.parseInt(dataTmp.getValue(salesDataType).toString()) + 1);
											} else {
												dataTmp.setValue(salesDataType, 1);
											}
											gridData.update(dataTmp.toString());
										} else {
											gridData.setValue(salesDataType, 1);
											gridData.insert();
										}
									}
								}
							} catch (Exception e) {
								String log = StringUtils.getLogPrefix(Level.SEVERE);
								System.out.println("\n" + log + "\n" + e.getClass()
										+ "\t:\t" + e.getMessage());
							}
						}
					}
					
					file.delete();
					result.put("info", "文件上传成功！");
				}
			}
		}
		
		return result;
	}

}
