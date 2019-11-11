package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDao {
	ReimbursementDao currentIplementation = new ReimbursementDaoSQL();
	
	int save(Reimbursement reimb);
	
	List<Reimbursement> findAll();
	
	Reimbursement findbyId(int id);
	
//	List<Reimbursement> findByUserId(int userId);
	List<Reimbursement> findByUsername(String username);
	
	//List<Reimbursement> findByStatus(int statusId);
	List<Reimbursement> findByStatus(String status);
	//List<Reimbursement> findByAuthorAndStatus(String username, int statusId);
	
	//patchput reimbursements
	//void viewPendingStatus
	//void
	

}
