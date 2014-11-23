package cn.com.chinatelecom.map.process;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.chinatelecom.map.common.Config;
import cn.com.chinatelecom.map.common.Repository;
import cn.com.chinatelecom.map.handle.IHandler;

/**
 * @author joseph
 *
 */
public class PageProcessor implements IProcessor {

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

		Map<String, Object> result = handler.handle(Repository.getInstance()
				.parse(request));
		if (result == null) {
			return;
		}
		for (Entry<String, Object> eso : result.entrySet()) {
			out.println(eso.getKey() + "-->" + eso.getValue());
		}

		out.flush();
		out.close();
	}

}
