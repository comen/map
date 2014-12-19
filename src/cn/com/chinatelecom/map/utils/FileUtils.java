package cn.com.chinatelecom.map.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.xmlbeans.XmlException;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

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

	@SuppressWarnings("resource")
	public static String readFile(File file) {
		if (null == file) {
			logger.error("待读取文件为空！");
			return null;
		}

		POIXMLTextExtractor extractor = null;
		try {
			if (file.getName().endsWith(".xls"))
				extractor = new MapXLSExtractor(file);
			else
				extractor = new MapXLSXExtractor(file);
		} catch (Exception e) {
			logger.fatal("解析excel文件错误: " + e.getMessage());
			return null;
		}
		return extractor.getText();
	}

	private static class MapXLSExtractor extends POIXMLTextExtractor {
		private NPOIFSFileSystem fs;
		private boolean includeSheetNames;
		private boolean formulasNotResults;

		public MapXLSExtractor(File file) throws XmlException,
				OpenXML4JException, IOException {
			this(new NPOIFSFileSystem(file));
		}

		public MapXLSExtractor(NPOIFSFileSystem fs) throws XmlException,
				OpenXML4JException, IOException {
			super(null);
			this.fs = fs;
			this.includeSheetNames = false;
			this.formulasNotResults = false;
		}

		public String getText() {
			try {
				HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot(), false);
				String title;
				List<String> titles = new ArrayList<String>();
				StringBuffer text = new StringBuffer();
				BasicDBObject bdbo = new BasicDBObject();
				int number = wb.getNumberOfSheets();
				for (int index = 0; index != number; index++) {
					Sheet sheet = wb.getSheetAt(index);
					if (includeSheetNames)
						text.append(sheet.getSheetName() + "\n");
					int rows = sheet.getPhysicalNumberOfRows();
					int columns = sheet.getRow(0).getPhysicalNumberOfCells();
					for (int i = 0; i != rows; i++) {
						Row row = sheet.getRow(i);
						if (null == row)
							continue;
						for (int j = 0; j != columns; j++) {
							Cell cell = row.getCell(j);
							if (null == cell)
								continue;
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
							text.append(bdbo.toString() + "\n");
					}
				}
				return text.toString();
			} catch (Exception e) {
				logger.fatal("读取表格文件错误: " + e.getMessage());
				return null;
			}
		}

		private Object handCell(int type, Cell cell) {
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
				if (formulasNotResults)
					return cell.getCellFormula();
				else
					return handCell(cell.getCachedFormulaResultType(), cell);
			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			default:
				return null;
			}
		}
	}

	/**
	 * Implementation of a text extractor from OOXML Excel files that uses SAX
	 * event based parsing.
	 */
	private static class MapXLSXExtractor extends POIXMLTextExtractor {
		private OPCPackage container;
		/**
		 * Should sheet names be included? Default is true
		 */
		private boolean includeSheetNames;
		/**
		 * Should we return the formula itself, and not the result it produces?
		 * Default is false
		 */
		private boolean formulasNotResults;

		/**
		 * These are the different kinds of cells we support. We keep track of
		 * the current one between the start and end.
		 */
		enum xssfDataType {
			BOOLEAN, ERROR, FORMULA, INLINE_STRING, SST_STRING, NUMBER,
		}

		public MapXLSXExtractor(File file) throws XmlException,
				OpenXML4JException, IOException {
			this(OPCPackage.open(file));
		}

		public MapXLSXExtractor(OPCPackage container) throws XmlException,
				OpenXML4JException, IOException {
			super(null);
			this.container = container;
			this.includeSheetNames = false;
			this.formulasNotResults = false;
		}

		/**
		 * Processes the file and returns the text
		 */
		public String getText() {
			try {
				ReadOnlySharedStringsTable tables = new ReadOnlySharedStringsTable(
						container);
				XSSFReader xssfReader = new XSSFReader(container);
				StylesTable styles = xssfReader.getStylesTable();
				XSSFReader.SheetIterator iterator = (XSSFReader.SheetIterator) xssfReader
						.getSheetsData();
				StringBuffer text = new StringBuffer();
				ContentHandler handler = new MapSheetHandler(styles, tables,
						text);
				while (iterator.hasNext()) {
					InputStream stream = iterator.next();
					if (includeSheetNames)
						text.append(iterator.getSheetName() + "\n");
					InputSource source = new InputSource(stream);
					SAXParserFactory factory = SAXParserFactory.newInstance();
					SAXParser parser = factory.newSAXParser();
					XMLReader reader = parser.getXMLReader();
					reader.setContentHandler(handler);
					reader.parse(source);
					stream.close();
				}
				return text.toString();
			} catch (Exception e) {
				logger.fatal("获取xlsx文件内容错误: " + e.getMessage());
				return null;
			}
		}

		/**
		 * Handler for sheets. Processes each row and cell, formatting Cells as
		 * best as it can.
		 */
		class MapSheetHandler extends DefaultHandler {
			/**
			 * Table with the styles used for formatting
			 */
			private StylesTable stylesTable;
			/**
			 * Table to read
			 */
			private ReadOnlySharedStringsTable sharedStringsTable;
			/**
			 * Where our text is going
			 */
			private final StringBuffer output;
			/**
			 * Set when V start element is seen.
			 */
			private boolean vIsOpen;
			/**
			 * Set when F start element is seen.
			 */
			private boolean fIsOpen;
			/**
			 * Set when cell start element is seen; used when cell close element
			 * is seen.
			 */
			private xssfDataType nextDataType;
			/**
			 * Used to format numeric cell values.
			 */
			private short formatIndex;
			private String formatString;
			private final DataFormatter formatter;
			/**
			 * Gathers characters as they are seen.
			 */
			private StringBuffer value = new StringBuffer();
			private StringBuffer formula = new StringBuffer();
			/**
			 * Gathers titles as they are seen.
			 */
			private List<String> titles;
			/**
			 * Gathers json object.
			 */
			private BasicDBObject bdbo;
			/**
			 * Indicate when are cells seen.
			 */
			private boolean firstRow = true;
			private int index = 0;

			/**
			 * Accepts objects needed while parsing.
			 *
			 * @param styles
			 *            Table of styles
			 * @param strings
			 *            Table of shared strings
			 * @param cols
			 *            Minimum number of columns to show
			 * @param target
			 *            Sink for output
			 */
			public MapSheetHandler(StylesTable styles,
					ReadOnlySharedStringsTable strings, StringBuffer output) {
				this.stylesTable = styles;
				this.sharedStringsTable = strings;
				this.output = output;
				this.nextDataType = xssfDataType.NUMBER;
				this.formatter = new DataFormatter();
				this.titles = new ArrayList<String>();
				this.bdbo = new BasicDBObject();
			}

			public void startElement(String uri, String localName, String name,
					Attributes attributes) throws SAXException {
				if ("inlineStr".equals(name) || "v".equals(name)) {
					vIsOpen = true;
					value.setLength(0);
				} else if ("f".equals(name)) {
					formula.setLength(0);
					if (nextDataType == xssfDataType.NUMBER)
						nextDataType = xssfDataType.FORMULA;
					String type = attributes.getValue("t");
					if (type != null && type.equals("shared"))
						logger.error("不支持共享公式！");
					else
						fIsOpen = true;
				} else if ("row".equals(name)) {
					index = 0;
				} else if ("c".equals(name)) {
					this.nextDataType = xssfDataType.NUMBER;
					this.formatIndex = -1;
					this.formatString = null;
					String cellType = attributes.getValue("t");
					String cellStyleStr = attributes.getValue("s");
					if ("b".equals(cellType))
						nextDataType = xssfDataType.BOOLEAN;
					else if ("e".equals(cellType))
						nextDataType = xssfDataType.ERROR;
					else if ("inlineStr".equals(cellType))
						nextDataType = xssfDataType.INLINE_STRING;
					else if ("s".equals(cellType))
						nextDataType = xssfDataType.SST_STRING;
					else if ("str".equals(cellType))
						nextDataType = xssfDataType.FORMULA;
					else if (cellStyleStr != null) {
						int styleIndex = Integer.parseInt(cellStyleStr);
						XSSFCellStyle style = stylesTable
								.getStyleAt(styleIndex);
						this.formatIndex = style.getDataFormat();
						this.formatString = style.getDataFormatString();
						if (this.formatString == null)
							this.formatString = BuiltinFormats
									.getBuiltinFormat(this.formatIndex);
					}
				}
			}

			public void endElement(String uri, String localName, String name)
					throws SAXException {
				Object content = null;
				if ("v".equals(name)) {
					vIsOpen = false;
					switch (nextDataType) {
					case BOOLEAN:
						char first = value.charAt(0);
						content = first == '0' ? "FALSE" : "TRUE";
						break;
					case ERROR:
						content = "ERROR:" + value.toString();
						break;
					case FORMULA:
						if (formulasNotResults)
							content = formula.toString();
						else
							content = value.toString();
						break;
					case INLINE_STRING:
						XSSFRichTextString rtsi = new XSSFRichTextString(
								value.toString());
						content = rtsi.toString();
						break;
					case SST_STRING:
						String sstIndex = value.toString();
						try {
							int idx = Integer.parseInt(sstIndex);
							XSSFRichTextString rtss = new XSSFRichTextString(
									sharedStringsTable.getEntryAt(idx));
							content = rtss.toString();
						} catch (NumberFormatException ex) {
							logger.fatal("解析富文本失败('" + sstIndex + "': "
									+ ex.toString() + ")");
						}
						break;
					case NUMBER:
						String n = value.toString();
						if (this.formatString != null) {
							double number = Double.parseDouble(n);
							boolean isDate = isCellDateFormatted(number,
									this.formatIndex, this.formatString);
							if (isDate)
								content = DateUtil.getJavaDate(number);
							else
								content = formatter.formatRawCellContents(
										number, this.formatIndex,
										this.formatString);
						} else {
							content = n;
						}
						break;
					default:
						content = "(TODO: Unexpected type: " + nextDataType
								+ ")";
						break;
					}
					if (firstRow)
						titles.add(content.toString());
					else
						bdbo.append(titles.get(index++), content);
				} else if ("f".equals(name)) {
					fIsOpen = false;
				} else if ("row".equals(name)) {
					if (firstRow)
						firstRow = false;
					else
						output.append(bdbo.toString() + "\n");
				}
			}

			/**
			 * Captures characters only if a suitable element is open.
			 * Originally was just "v"; extended for inlineStr also.
			 */
			public void characters(char[] ch, int start, int length)
					throws SAXException {
				if (vIsOpen)
					value.append(ch, start, length);
				if (fIsOpen)
					formula.append(ch, start, length);
			}

			private boolean isCellDateFormatted(double value,
					short formatIndex, String formatString) {
				boolean isDate = false;
				if (DateUtil.isValidExcelDate(value))
					isDate = DateUtil.isADateFormat(formatIndex, formatString);
				return isDate;
			}
		}
	}
}
