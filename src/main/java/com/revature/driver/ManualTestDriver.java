package com.revature.driver;

import com.revature.daos.UserDao;
import com.revature.daos.ReimbursementDao;

public class ManualTestDriver {
	public static void main(String[] args) {
		UserDao userDao = UserDao.currentIplementation;
		ReimbursementDao reimbtDao = ReimbursementDao.currentIplementation;
		
		System.out.println("find all reimbursements");
		reimbtDao.findAll().forEach(reimb -> {
			System.out.println(reimb);
		});
		
//		String u = "sg7son";
//		System.out.println("find all reimbs for username");
//		ReimbursementDao.findByUsername(u).forEach(un -> {
//			System.out.println(un);
//		});
		
	}

}
