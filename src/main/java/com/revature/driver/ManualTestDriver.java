package com.revature.driver;

import com.revature.daos.UserDao;
import com.revature.daos.ReimbursementDao;

public class ManualTestDriver {
	public static void main(String[] args) {
		UserDao userDao = UserDao.currentIplementation;
		ReimbursementDao reimbDao = ReimbursementDao.currentIplementation;
		
		System.out.println("find all reimbursements");
		reimbDao.findAll().forEach(reimb -> {
			System.out.println(reimb);
		});
		
		String u = "sg7son";
		System.out.println("find all reimbs for username");
		reimbDao.findByUsername(u).forEach(un -> {
			System.out.println(un);
		});
		
		String pw = "password";
		System.out.println("find reimbs for username and password");
		reimbDao.findByUsernameAndPassword(u, pw).forEach(upw -> {
			System.out.println(upw);
		});
		
		//userDao.findByUsernameAndPassword(u, pw);
		System.out.println(userDao.findByUsernameAndPassword(u, pw));
		
		String r = "manager";
		System.out.println("find manager");
		System.out.println(userDao.findRole(r));
		
		String s = "pending";
		System.out.println("find all pending status");
		reimbDao.findByStatus(s).forEach(stat -> {
			System.out.println(stat);
		});	
	}
}
