package com.revature.daos;

import java.util.List;
import com.revature.models.User;

public interface UserDao {
	UserDao currentIplementation = new UserDaoSQL();
	
	int save(User u);
	
	List<User> findAll();
	
	User findById(int id);
	
	User findByUsername(String username);
	
	User findByUsernameAndPassword(String username, String password);
	
	//void authorizeReimbursementRequest(Reimbursement reimb, int id); //if id = admin can change reimb

}
