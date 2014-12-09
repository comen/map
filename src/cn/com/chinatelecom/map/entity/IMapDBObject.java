package cn.com.chinatelecom.map.entity;

/**
 * @author joseph
 *
 */
public interface IMapDBObject {
	
	public abstract boolean exist();

	public abstract boolean insert();

	public abstract boolean delete();

	public abstract boolean update(String json);
	
}
