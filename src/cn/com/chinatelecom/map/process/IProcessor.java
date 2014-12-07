package cn.com.chinatelecom.map.process;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.com.chinatelecom.map.handle.IHandler;

/**
 * @author joseph
 *
 */
public interface IProcessor {
	
	Logger logger = Logger.getLogger(IProcessor.class);

	public void process(HttpServletRequest request, IHandler handler,
			HttpServletResponse response) throws ServletException, IOException;

}
