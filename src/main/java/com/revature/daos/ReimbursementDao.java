package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDao {
	ReimbursementDao currentIplementation = new ReimbursementDaoSQL();
	UserDao currentImplementation = new UserDaoSQL();
	
	int save(Reimbursement reimb);
	
	List<Reimbursement> findAll();
	
	Reimbursement findbyId(int id);
	
//	List<Reimbursement> findByUserId(int userId);
	List<Reimbursement> findByUsername(String username);
	
	List<Reimbursement> findByStatus(int statusId);
	List<Reimbursement> findByStatus(String status);
	
	//find by userRole
	List<Reimbursement> findByUsernameAndPassword(String username, String password);

	//List<Reimbursement> viewPendingStatus(String s);

	void managerUpdateStatus(int statusId, int resolverId, int reimbId);
}
