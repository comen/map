package cn.com.chinatelecom.map.handle;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.entity.Grid;
import cn.com.chinatelecom.map.utils.FileUtils;
import cn.com.chinatelecom.map.utils.StringUtils;

/**
 * @author joseph
 *
 */
public class UploadHandler implements IHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		Map<String, Object> result = new HashMap<String, Object>();
		for (FileItem item : items) {
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				String string = item.getString();
				result.put(fieldName, string);
			} else {
				String filename = item.getName().trim();
				filename = FileUtils.getFileName(filename);
				if (!StringUtils.isLegal(filename, ".xls$")) {
					result.put("info", "�ļ���ʽ����");
					return result;
				}
				File file = new File(Config.getInstance()
						.getValue("uploadPath") + "/" + filename);

				try {
					FileUtils.writeFile(item.getInputStream(), file);
				} catch (Exception e) {
					result.put("info", e.getClass() + ":" + e.getMessage());
					return result;
				}

				String content = FileUtils.readFile(file);
				result.put("content", content);
				String[] splits = content.split("<br/>");
				Grid grid;
				for (String split : splits) {
					if (!split.trim().equals("")) {
						grid = new Grid(split);
						grid.insert();
					}
				}
				result.put("info", "�ļ��ϴ��ɹ���");

				// --------------------Test MongoDB Start--------------------
				// 北区局
				grid = new Grid(
						"{'GRID_CODE':'0','GRID_NAME':'北区局','GRID_COORDINATES':[{'LONGTITUDE':121.40850,'LATITUDE':31.34395},{'LONGTITUDE':121.40584,'LATITUDE':31.34146},{'LONGTITUDE':121.41022,'LATITUDE':31.32921},{'LONGTITUDE':121.40756,'LATITUDE':31.32298},{'LONGTITUDE':121.40713,'LATITUDE':31.31880},{'LONGTITUDE':121.40756,'LATITUDE':31.31308},{'LONGTITUDE':121.40352,'LATITUDE':31.30611},{'LONGTITUDE':121.39515,'LATITUDE':31.30956},{'LONGTITUDE':121.38983,'LATITUDE':31.30975},{'LONGTITUDE':121.38790,'LATITUDE':31.30813},{'LONGTITUDE':121.38606,'LATITUDE':31.30934},{'LONGTITUDE':121.38159,'LATITUDE':31.31000},{'LONGTITUDE':121.37889,'LATITUDE':31.30857},{'LONGTITUDE':121.36799,'LATITUDE':31.30876},{'LONGTITUDE':121.36202,'LATITUDE':31.30600},{'LONGTITUDE':121.35353,'LATITUDE':31.30780},{'LONGTITUDE':121.35374,'LATITUDE':31.30479},{'LONGTITUDE':121.35014,'LATITUDE':31.30468},{'LONGTITUDE':121.34529,'LATITUDE':31.29970},{'LONGTITUDE':121.34110,'LATITUDE':31.30028},{'LONGTITUDE':121.34190,'LATITUDE':31.29739},{'LONGTITUDE':121.33334,'LATITUDE':31.29502},{'LONGTITUDE':121.34054,'LATITUDE':31.28631},{'LONGTITUDE':121.33998,'LATITUDE':31.28439},{'LONGTITUDE':121.33874,'LATITUDE':31.28431},{'LONGTITUDE':121.33830,'LATITUDE':31.28395},{'LONGTITUDE':121.34408,'LATITUDE':31.28305},{'LONGTITUDE':121.36689,'LATITUDE':31.27729},{'LONGTITUDE':121.36704,'LATITUDE':31.27390},{'LONGTITUDE':121.36638,'LATITUDE':31.27081},{'LONGTITUDE':121.38867,'LATITUDE':31.26696},{'LONGTITUDE':121.39211,'LATITUDE':31.27010},{'LONGTITUDE':121.39977,'LATITUDE':31.27113},{'LONGTITUDE':121.44875,'LATITUDE':31.26181},{'LONGTITUDE':121.47317,'LATITUDE':31.25256},{'LONGTITUDE':121.47724,'LATITUDE':31.25359},{'LONGTITUDE':121.47917,'LATITUDE':31.24610},{'LONGTITUDE':121.48261,'LATITUDE':31.24753},{'LONGTITUDE':121.48716,'LATITUDE':31.24618},{'LONGTITUDE':121.49312,'LATITUDE':31.25018},{'LONGTITUDE':121.50025,'LATITUDE':31.24871},{'LONGTITUDE':121.50269,'LATITUDE':31.25084},{'LONGTITUDE':121.50175,'LATITUDE':31.25333},{'LONGTITUDE':121.50050,'LATITUDE':31.25417},{'LONGTITUDE':121.49956,'LATITUDE':31.25627},{'LONGTITUDE':121.49857,'LATITUDE':31.25700},{'LONGTITUDE':121.49887,'LATITUDE':31.25979},{'LONGTITUDE':121.49862,'LATITUDE':31.26232},{'LONGTITUDE':121.50003,'LATITUDE':31.26643},{'LONGTITUDE':121.50119,'LATITUDE':31.26757},{'LONGTITUDE':121.50050,'LATITUDE':31.26826},{'LONGTITUDE':121.50128,'LATITUDE':31.26973},{'LONGTITUDE':121.50080,'LATITUDE':31.27043},{'LONGTITUDE':121.50119,'LATITUDE':31.27120},{'LONGTITUDE':121.50308,'LATITUDE':31.27435},{'LONGTITUDE':121.50587,'LATITUDE':31.27387},{'LONGTITUDE':121.50934,'LATITUDE':31.28004},{'LONGTITUDE':121.50578,'LATITUDE':31.28235},{'LONGTITUDE':121.49420,'LATITUDE':31.28260},{'LONGTITUDE':121.49008,'LATITUDE':31.28103},{'LONGTITUDE':121.48488,'LATITUDE':31.28198},{'LONGTITUDE':121.47651,'LATITUDE':31.28587},{'LONGTITUDE':121.46888,'LATITUDE':31.28598},{'LONGTITUDE':121.46832,'LATITUDE':31.28807},{'LONGTITUDE':121.46883,'LATITUDE':31.29097},{'LONGTITUDE':121.46797,'LATITUDE':31.29485},{'LONGTITUDE':121.46673,'LATITUDE':31.29606},{'LONGTITUDE':121.46978,'LATITUDE':31.30021},{'LONGTITUDE':121.47154,'LATITUDE':31.30388},{'LONGTITUDE':121.47063,'LATITUDE':31.31077},{'LONGTITUDE':121.47703,'LATITUDE':31.31183},{'LONGTITUDE':121.48390,'LATITUDE':31.31799},{'LONGTITUDE':121.48480,'LATITUDE':31.32148},{'LONGTITUDE':121.48209,'LATITUDE':31.32804},{'LONGTITUDE':121.47529,'LATITUDE':31.32638},{'LONGTITUDE':121.47433,'LATITUDE':31.33493},{'LONGTITUDE':121.46956,'LATITUDE':31.33310},{'LONGTITUDE':121.46042,'LATITUDE':31.33288},{'LONGTITUDE':121.45188,'LATITUDE':31.32998},{'LONGTITUDE':121.44351,'LATITUDE':31.34890},{'LONGTITUDE':121.43656,'LATITUDE':31.34813},{'LONGTITUDE':121.43321,'LATITUDE':31.34450},{'LONGTITUDE':121.42540,'LATITUDE':31.34351},{'LONGTITUDE':121.41889,'LATITUDE':31.34498}]}");
				// 海川分局
				// grid = new
				// Grid("{'GRID_CODE':'1','GRID_NAME':'海川分局','GRID_COORDINATES':[]}");
				// 甘泉分局
				// grid = new
				// Grid("{'GRID_CODE':'2','GRID_NAME':'甘泉分局','GRID_COORDINATES':[]}");
				// 平顺分局
				// grid = new
				// Grid("{'GRID_CODE':'3','GRID_NAME':'平顺分局','GRID_COORDINATES':[]}");
				// 和田分局
				// grid = new
				// Grid("{'GRID_CODE':'4','GRID_NAME':'和田分局','GRID_COORDINATES':[]}");
				grid.insert();

				grid = new Grid("{GRID_CODE:'BQ-PS-SD-5045'}");
				System.out.println(grid.exist());
				grid.delete();
				System.out.println(grid.exist());

				grid = new Grid("{GRID_CODE:'BQ-PS-SD-5043'}");
				System.out.println(Grid.findOne(grid.toString()));
				grid.update("{GRID_CODE:'BQ-PS-SD-5043',GRID_NAME:'上海市体育运动学校',GRID_MANAGER:'钱惠平',GRID_ADDRESS:'上海市虹口区广中路406号',GRID_COORDINATES:[{LATITUDE:31.25214,LONGTITUDE:121.46386},{LATITUDE:31.25313,LONGTITUDE:121.46892}]}");
				System.out.println(Grid.findOne(grid.toString()));
				// --------------------Test MongoDB End--------------------
			}
		}
		return result;
	}

}
