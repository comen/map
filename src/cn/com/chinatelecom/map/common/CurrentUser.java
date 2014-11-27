/**
 * 
 */
package cn.com.chinatelecom.map.common;

import cn.com.chinatelecom.map.entity.User;

/**
 * @author swei019
 *
 */
public class CurrentUser extends User {

	/**
	 * 
	 */
	
	private static CurrentUser instance;
	
	private CurrentUser() {
		// TODO Auto-generated constructor stub
	}
	
	public static CurrentUser getInstance() {
		if (instance == null) {
			instance = new CurrentUser();
		}
		return instance;
	}

}
