package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.ReimbursementDao;
import com.revature.models.Reimbursement;

public class ReimbursementServlet extends HttpServlet {
	
	private ReimbursementDao reimbDao = ReimbursementDao.currentIplementation;
	ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
		System.out.println("To context param: " + req.getServletContext().getInitParameter("To"));

		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:5500");
		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		resp.addHeader("Access-Control-Allow-Headers",
				"Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Reimbursement> reimbs = new ArrayList<Reimbursement>();
		String[] path = req.getRequestURI().split("/");
		System.out.println("yo"+Arrays.toString(path));
		System.out.println(path.length);
		if (path.length <= 3) {//[2] == "reimbursements") {
			String role = req.getParameter("ers_user_role");
			
			reimbs = reimbDao.findAll();
			System.out.println("all usernames");	
		} else if (path.length == 5) {
			System.out.println("somethings");
			System.out.println(path[4]);
			reimbs = reimbDao.findByStatus(path[4]);
			
		} 
		
		System.out.println(reimbs);
		String json = om.writeValueAsString(reimbs);
		
		resp.addHeader("content-type", "application/json");
		resp.getWriter().write(json);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// read the reimbursements from the request body
				Reimbursement r = (Reimbursement) om.readValue(req.getReader(), Reimbursement.class);

				System.out.println(r);

				int id = reimbDao.save(r);
				r.setId(id);

				String json = om.writeValueAsString(r);

				resp.getWriter().write(json);
				resp.setStatus(201); // created status code
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fetchStatus = req.getParameter("status");
		String fetchResolver = req.getParameter("resolver");
		String fetchReimbId = req.getParameter("id");
		
		int stat = Integer.parseInt(fetchStatus);
		int resolverId = Integer.parseInt(fetchResolver);
		int rId = Integer.parseInt(fetchReimbId);
		
		reimbDao.managerUpdateStatus(stat, resolverId, rId);

		resp.setStatus(201); // created status code
	}

}
