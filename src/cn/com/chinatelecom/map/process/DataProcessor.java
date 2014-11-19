package cn.com.chinatelecom.map.process;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.common.Repository;
import cn.com.chinatelecom.map.handle.IHandler;

/**
 * @author Shelwin
 *
 */
public class DataProcessor implements IProcessor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.chinatelecom.map.process.IProcessor#process(javax.servlet.http
	 * .HttpServletRequest, cn.com.chinatelecom.map.handle.IHandler,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void process(HttpServletRequest request, IHandler handler,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset="
				+ Config.getInstance().getValue("charset"));
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		
		Map<String, Object> result = handler.handle(Repository.getInstance()
				.parse(request));
		
		for (Entry<String, Object> eso : result.entrySet()) {
			session.setAttribute(eso.getKey(), eso.getValue());
			out.println(eso.getKey() + ":" + eso.getValue());
		}
		
		out.flush();
		out.close();
		
	}

}