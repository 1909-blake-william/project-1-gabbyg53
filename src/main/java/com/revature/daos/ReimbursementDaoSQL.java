package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

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
		log.debug("attempting to save reimbursements by credentials from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO ERS_REIMBURSEMENT (reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) "
					+ "VALUES (ERS_REIMBURSEMENT_SEQ.nextval, ?, CURRENT_TIMESTAMP, null, ?, ?, null, ?, ?)"; //current timestamp
			
			PreparedStatement ps = c.prepareStatement(sql);
			//ps.setInt(1, reimb.getId());
			ps.setDouble(1, reimb.getAmount());
			//ps.setTimestamp(3, reimb.getDateSubmitted());
			//ps.setTimestamp(3, reimb.getResolveDate());
			ps.setString(2, reimb.getDescription());
			ps.setInt(3, reimb.getAuthor());
			//ps.setInt(6, reimb.getResolver());
			ps.setInt(4, reimb.getStatus());
			ps.setInt(5, reimb.getType());
			
			return ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Reimbursement> findAll() {
		log.debug("attempting to find all reimbursements from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENT";

			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbs = new ArrayList<>();
			while (rs.next()) {
				reimbs.add(extractReimbursement(rs));
			}
			return reimbs;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Reimbursement findbyId(int id) {
		log.debug("attempting to find a reimbursement by id from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENT " + "WHERE reimb_id = ? ";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractReimbursement(rs);
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Reimbursement> findByUsername(String username) {
		log.debug("attempting to find reimbursement by username from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENT "
				+ "LEFT JOIN ERS_USERS ON (ERS_REIMBURSEMENT.reimb_author = ERS_USERS.ers_user_id) "
				+ "WHERE ERS_USERS.ers_username = ?";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();
			List<Reimbursement> r = new ArrayList<>();
			while (rs.next()) {
				r.add(extractReimbursement(rs));
			}
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public List<Reimbursement> findByStatus(String status) {
		log.debug("attempting to find transaction by user id from DB");
		try (Connection c = ConnectionUtil.getConnection()) {
System.out.println("hi from here");
System.out.println(status);
			int s = -1;
			if (status.equals("pending")) {
				System.out.println("pending if statement");
				s = 2;
			} else if (status.equals("approved")) {
				s = 1;
			}
			else if (status.contentEquals("denied")) {
				s = 0;
			}
			System.out.println("s = "+s);
			
//			String sql = "SELECT * FROM ERS_REIMBURSEMENT "
//				+ "LEFT JOIN ERS_USERS ON (ERS_REIMBURSEMENT.reimb_author = ERS_USERS.ers_user_id) "
//				+ "WHERE ERS_USERS.ers_user_id = ?";
			
			String sql = "SELECT * FROM ERS_REIMBURSEMENT " 
					+ "LEFT JOIN ERS_REIMBURSEMENT_STATUS USING (reimb_status_id) "
					+ "WHERE reimb_status_id = ?";		
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, s);
			//ps.setString(1, status);

			ResultSet rs = ps.executeQuery();
			List<Reimbursement> t = new ArrayList<>();
			while (rs.next()) {
				t.add(extractReimbursement(rs));
			}
			System.out.println(t);
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Reimbursement> findByUsernameAndPassword(String username, String password) {
		log.debug("attempting to find reimbursements by credentials from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENT " 
					+ "LEFT JOIN ERS_USERS ON (ERS_REIMBURSEMENT.reimb_author = ERS_USERS.ers_user_id) "
					+ "WHERE ers_username = ? AND ers_password = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<Reimbursement> r = new ArrayList<>();
			while (rs.next()) {
				r.add(extractReimbursement(rs));
			}
			return r;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Reimbursement> findByStatus(int statusId) {
		log.debug("attempting to find reimbursements with pending status by credentials from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			//if (UserDao.currentIplementation.)
			
			String sql = "SELECT * FROM ERS_REIMBURSEMENT " 
					+ "LEFT JOIN ERS_REIMBURSEMENT_STATUS USING (reimb_status_id) "
					+ "WHERE reimb_status_id = ?";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, statusId);

			ResultSet rs = ps.executeQuery();
			List<Reimbursement> r = new ArrayList<>();
			while (rs.next()) {
				r.add(extractReimbursement(rs));
			}
			return r;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}
	

	@Override
	public void managerUpdateStatus(int statusId, int resolverId, int reimbId) {
		log.debug("attempting to update reimbursements to be either approved or deny");
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "UPDATE ERS_REIMBURSEMENT "
					+ "SET reimb_status_id = ?, reimb_resolver = ?, reimb_resolved = CURRENT_TIMESTAMP "
					+ "WHERE reimb_id = ?";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, statusId);
			ps.setInt(2, resolverId);
			ps.setInt(3, reimbId);

			ps.executeQuery();		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
