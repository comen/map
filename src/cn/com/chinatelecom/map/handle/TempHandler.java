/**
 * 
 */
package cn.com.chinatelecom.map.handle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.util.JSON;

/**
 * @author Shelwin
 *
 */
public class TempHandler implements IHandler {

	/* (non-Javadoc)
	 * @see cn.com.chinatelecom.map.handle.IHandler#handle(java.util.List)
	 */
	@Override
	public Map<String, Object> handle(List<FileItem> items) {
		// TODO Auto-generated method stub
		
		File file = new File("C:/eclipse-workspace/GRID.log");
		
		try {
			MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
			DB db = mongoClient.getDB("map");
			DBCollection coll = db.getCollection("grid2");
			coll.ensureIndex(new BasicDBObject("GRID_CODE", 1), "GRID_CODE", true);
			
			if (null != file && file.exists() && file.isFile()) {
				
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
				String line;
				while ((line = br.readLine()) != null) {
					BasicDBObject dbo = (BasicDBObject) JSON.parse(line);
					coll.save(dbo, WriteConcern.NORMAL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
