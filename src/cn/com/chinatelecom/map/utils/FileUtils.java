package cn.com.chinatelecom.map.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import jxl.Sheet;
import jxl.Workbook;

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

	public static String readFile(File file) {
		if (null == file) {
			logger.error("待读取文件为空！");
			return null;
		}
		String result = null;
		StringBuffer sb = new StringBuffer();
		if (file.getName().endsWith(".xls")) {
			try {
				Workbook book = Workbook.getWorkbook(file);
				Sheet sheet = book.getSheet(0);
				int rows = sheet.getRows();
				int columns = sheet.getColumns();
				List<String> titles = new ArrayList<String>();
				for (int row = 0; row != rows; row++) {
					if (0 != row)
						sb.append("{");
					for (int column = 0; column != columns; column++) {
						String content = sheet.getCell(column, row)
								.getContents();
						if (0 == row) {
							titles.add(content);
						} else {
							sb.append("'" + titles.get(column) + "':'"
									+ content + "'");
							if (column != columns - 1) {
								sb.append(",");
							}
						}
					}
					if (0 != row)
						sb.append("}<br/>");
				}
				result = sb.toString();
			} catch (Exception e) {
				logger.fatal("读取文件错误: " + e.getMessage());
			}
		}
		return result;
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
