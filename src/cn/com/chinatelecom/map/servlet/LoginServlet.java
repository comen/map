package cn.com.chinatelecom.map.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.chinatelecom.map.handle.IHandler;
import cn.com.chinatelecom.map.handle.LoginHandler;
import cn.com.chinatelecom.map.process.IProcessor;
import cn.com.chinatelecom.map.process.DataProcessor;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "User Login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IProcessor processor;
	private IHandler handler;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		processor = new DataProcessor();
		handler = new LoginHandler();
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
