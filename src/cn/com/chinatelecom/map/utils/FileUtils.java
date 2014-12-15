package cn.com.chinatelecom.map.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mongodb.BasicDBObject;

/**
 * @author joseph
 *
 */
public class FileUtils {

	private static Logger logger = Logger.getLogger(FileUtils.class);

	public static boolean writeFile(InputStream is, File file) {
		if (null == is || null == file) {
			logger.error("输入流或文件为空！");
			return false;
		}

		OutputStream os;
		try {
			os = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			for (int i = 0; (i = is.read(buffer)) != -1;) {
				os.write(buffer, 0, i);
			}
			is.close();
			os.flush();
			os.close();
			return true;
		} catch (Exception e) {
			logger.fatal("写文件发生错误: " + e.getMessage());
			return false;
		}
	}

	public static List<String> readFile(File file) {
		if (null == file) {
			logger.error("待读取文件为空！");
			return null;
		}

		try {
			FileInputStream fis = new FileInputStream(file);
			List<String> result = new ArrayList<String>();
			BasicDBObject bdbo = new BasicDBObject();
			List<String> titles = new ArrayList<String>();
			String title;
			Workbook wb = null;
			if (file.getName().endsWith(".xls"))
				wb = (Workbook) new HSSFWorkbook(fis);
			else if (file.getName().endsWith(".xlsx"))
				wb = (Workbook) new XSSFWorkbook(fis);
			if (null == wb) {
				logger.error("待读取文件非法！");
				return null;
			}
			Sheet sheet = wb.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			int columns = sheet.getRow(0).getPhysicalNumberOfCells();
			for (int i = 0; i != rows; i++) {
				Row row = sheet.getRow(i);
				if (null == row) {
					continue;
				}
				for (int j = 0; j != columns; j++) {
					Cell cell = row.getCell(j);
					if (null == cell) {
						continue;
					}
					int type = cell.getCellType();
					Object object = handCell(type, cell);
					if (0 == i) {
						titles.add(object.toString());
					} else {
						title = titles.get(j);
						bdbo.append(title, object);
					}
				}
				if (0 != i)
					result.add(bdbo.toString());
			}
			return result;
		} catch (Exception e) {
			logger.fatal("读取表格文件错误: " + e.getMessage());
			return null;
		}
	}

	private static Object handCell(int type, Cell cell) {
		if (null == cell)
			return null;
		switch (type) {
		case Cell.CELL_TYPE_NUMERIC:
			double d = cell.getNumericCellValue();
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = HSSFDateUtil.getJavaDate(d);
				return date;
			} else {
				return d;
			}
		case Cell.CELL_TYPE_BLANK:
			return "";
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();
		case Cell.CELL_TYPE_ERROR:
			return cell.getErrorCellValue();
		case Cell.CELL_TYPE_FORMULA:
			return handCell(cell.getCachedFormulaResultType(), cell);
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		default:
			return null;
		}
	}

	public static String getFileName(String path) {
		if (path == null) {
			return null;
		}
		File file = new File(path);
		if (!file.exists()) {
			return null;
		}
		return file.getName();
	}

}
