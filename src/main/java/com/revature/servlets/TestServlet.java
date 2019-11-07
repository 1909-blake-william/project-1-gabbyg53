package com.revature.servlets;

import java.io.IOException;

//import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		// TODO Auto-generated method stub
//		super.init(config);
//		
//		System.out.println("Poto init param: " + config.getInitParameter("Poto"));
//		System.out.println("To context param: " + config.getServletContext().getInitParameter("To"));
//	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("request received by url: " + req.getRequestURI());
		resp.getWriter().write("Hello from my ers servlet");
	}

}
