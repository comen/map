package cn.com.chinatelecom.map.process;

import java.io.IOException;
<<<<<<< HEAD
import java.io.PrintWriter;
=======
>>>>>>> 0c521acfea37c5e2084e8e9f80a5c2daf7cc6a19
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

<<<<<<< HEAD
import cn.com.chinatelecom.map.common.Config;
=======
>>>>>>> 0c521acfea37c5e2084e8e9f80a5c2daf7cc6a19
import cn.com.chinatelecom.map.common.Repository;
import cn.com.chinatelecom.map.handle.IHandler;

/**
<<<<<<< HEAD
 * @author Shelwin
=======
 * @author joseph
>>>>>>> 0c521acfea37c5e2084e8e9f80a5c2daf7cc6a19
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
<<<<<<< HEAD
		
		response.setContentType("text/html;charset="
				+ Config.getInstance().getValue("charset"));
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		Map<String, Object> result = handler.handle(Repository.getInstance()
				.parse(request));
		
		if(result != null) { //µÇÂ¼³É¹¦
			for (Entry<String, Object> eso : result.entrySet()) {
				session.setAttribute(eso.getKey(), eso.getValue());
			}
			out.print("1");
		} else {
			out.print("0");
		}
		
		out.flush();
		out.close();
=======

		HttpSession session = request.getSession();
		Map<String, Object> result = handler.handle(Repository.getInstance()
				.parse(request));
		if (result == null) {
			return;
		}
		for (Entry<String, Object> eso : result.entrySet()) {
			session.setAttribute(eso.getKey(), eso.getValue());
		}
>>>>>>> 0c521acfea37c5e2084e8e9f80a5c2daf7cc6a19
		
	}

}
