package cn.com.chinatelecom.map.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.chinatelecom.map.handle.IHandler;
import cn.com.chinatelecom.map.handle.UploadHandler;
import cn.com.chinatelecom.map.process.IProcessor;
import cn.com.chinatelecom.map.process.PageProcessor;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet(description = "Upload file", urlPatterns = { "/jsp/upload" })
public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IProcessor processor;
	private IHandler handler;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();
		processor = new PageProcessor();
		handler = new UploadHandler();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processor.process(request, handler, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processor.process(request, handler, response);
	}

}
