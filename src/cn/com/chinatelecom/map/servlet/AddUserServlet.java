package cn.com.chinatelecom.map.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.chinatelecom.map.handle.AddUserHandler;
import cn.com.chinatelecom.map.handle.IHandler;
import cn.com.chinatelecom.map.process.DataProcessor;
import cn.com.chinatelecom.map.process.IProcessor;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet(description = "Add User", urlPatterns = { "/jsp/addUser" })
public class AddUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private IProcessor processor;
	private IHandler handler;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
        processor = new DataProcessor();
		handler = new AddUserHandler();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processor.process(request, handler, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processor.process(request, handler, response);
	}

}
