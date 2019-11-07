package com.revature.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;

public class ReimbursementDaoSQL implements ReimbursementDao {
	private Logger log = Logger.getRootLogger();
	
	Reimbursement extractReimbursement(ResultSet rs) throws SQLException {
		int id = rs.getInt("reimb_id");
		double amount = rs.getDouble("reimb_amount");
		Timestamp submit = rs.getTimestamp("reimb_submitted");
		Timestamp resolved = rs.getTimestamp("reimb_resolved");
		String memo = rs.getString("reimb_description");
		int author = rs.getInt("reimb_author");
		int resolver = rs.getInt("reimb_resolver");
		int status = rs.getInt("reimb_status_id");
		int type = rs.getInt("reimb_type_id");
		return new Reimbursement(id, amount, submit, resolved, memo, author, resolver, status, type);
	}
	
	@Override
	public int save(Reimbursement reimb) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Reimbursement> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement findbyId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> findByStatus(int statusId) {
		// TODO Auto-generated method stub
		return null;
	}

}
