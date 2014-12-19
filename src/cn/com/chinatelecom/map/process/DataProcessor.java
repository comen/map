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
		
		Map<String, Object> result = handler.handle(Repository.getInstance()
				.parse(request));
		if (null == result) {
			logger.warn("没有响应数据!");
			return;
		}

		response.setContentType("text/html;charset="
				+ Config.getInstance().getValue("charset"));
		PrintWriter out = response.getWriter();
		for (Entry<String, Object> eso : result.entrySet()) {
			out.println(eso.getValue());
			System.out.println(eso.getValue().toString());
		}
		out.flush();
		out.close();

	}

}
