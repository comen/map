/**
 * 
 */
package cn.com.chinatelecom.map.handle;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.fileupload.FileItem;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.entity.Data;
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
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		
		// 这里Data实现了Runnable接口
		List<Data> tasks = new ArrayList<Data>();
	  
		Map<String, Object> result = new HashMap<String, Object>();
		String salesDataType = "";
		
		if (items == null) {
			logger.warn("没有请求数据!");
			result.put("Warn", "没有请求数据!");
			return result;
		}
		
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				if (fieldName.equals("salesDataType")) {
					salesDataType = item.getString();
				}
			} else {
				if (!salesDataType.equals("")) {
					String filename = item.getName().trim();
					filename = StringUtils.getFileName(filename);
					if (!StringUtils.isLegal(filename, ".xls[x]?$")) {
						logger.error("文件格式有误: " + filename);
						result.put("Error", "文件格式有误: " + filename);
						return result;
					}
					File file = new File(Config.getInstance()
							.getValue("uploadPath") + "/" + filename);
					
					try {
						FileUtils.writeFile(item.getInputStream(), file);
					} catch (Exception e) {
						logger.fatal("获取上传文件输入流错误: " + e.getMessage());
						result.put("Fatal", "获取上传文件输入流错误: " + e.getMessage());
						return result;
					}
					
					List<String> lines = FileUtils.readFile(file);
					if (null == lines) {
						logger.error("读取文件失败: " + filename);
						result.put("Error", "读取文件失败: " + filename);
						return result;
					}
					
					for (String line : lines) {
						if (!line.trim().equals("")) {
							BasicDBObject bdbo = (BasicDBObject) JSON.parse(line);
							String gridCode = bdbo.getString(Config.getInstance().getValue("gridCodeColumn"));
							String address = bdbo.getString(Config.getInstance().getValue("addressColumn"));
							String suboffice = bdbo.getString(Config.getInstance().getValue("subofficeColumn"));
							Date calculatedDate = bdbo.getDate(Config.getInstance().getValue("dateColumn"));
							
							if (suboffice == null || suboffice.equals("")) {
								continue;
							}
							
							if (calculatedDate == null) {
								calculatedDate = DateUtils.getCurrentDate();
							}
							
							try {
								/* Accumulate for Office */
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
									officeData.setValue(salesDataType, 1);
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
									if (gridCode != null) {
										String[] gridCodeSplit = gridCode.split("-");
										if (gridCodeSplit.length < 4) { // Grid code is invalid
											if (address == null || address.equals("")) {
												continue;
											} else {
												/*if (!address.startsWith("上海")) {
													address = "上海市" + address;
												}*/
												Data gridData = new Data();
												gridData.setCalculatedDate(calculatedDate);
												gridData.setAddress(address);
												gridData.setSalesDataType(salesDataType);
												/* Added to multi-threads processing list */
												tasks.add(gridData);
												continue;
											}
										}
									} else {
										if (address == null || address.equals("")) {
											continue;
										} else {
											/*if (!address.startsWith("上海")) {
												address = "上海市" + address;
											}*/
											Data gridData = new Data();
											gridData.setCalculatedDate(calculatedDate);
											gridData.setAddress(address);
											gridData.setSalesDataType(salesDataType);
											/* Added to multi-threads processing list */
											tasks.add(gridData);
											continue;
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
							} catch (Exception e) {
								logger.error("文件数据解析出错: " + e.getMessage());
								result.put("Error", "文件数据解析出错: " + e.getMessage());
								return result;
							}
						}
					}
					
					if (tasks.isEmpty()) {
						file.delete();
						result.put("Success", "文件上传成功！");
					} else {
						// 根据CPU和任务数动态分配线程池大小
						int cpu = Runtime.getRuntime().availableProcessors();
						int size = Math.floorDiv(cpu * tasks.size(), 10) + 1;
						ExecutorService threadPool = Executors.newFixedThreadPool(size);
						// 执行所有任务并关闭线程池
						for (Data task : tasks) {
							threadPool.execute(task);
						}
						threadPool.shutdown();
						/*// 等待所有线程结束后的后续操作
						try {
							if (threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS)) {
								// TODO
								file.delete();
								result.put("info", "文件上传成功！");
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						file.delete();
						result.put("Success", "文件上传成功！");
					}
				}
			}
		}
		
		return result;
	}

}
