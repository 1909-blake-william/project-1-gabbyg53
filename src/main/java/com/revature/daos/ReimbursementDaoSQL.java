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
					+ "VALUES (ERS_REIMBURSEMENT_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, reimb.getId());
			ps.setDouble(2, reimb.getAmount());
			ps.setTimestamp(3, reimb.getDateSubmitted());
			ps.setTimestamp(4, reimb.getResolveDate());
			ps.setString(5, reimb.getDescription());
			ps.setInt(6, reimb.getAuthor());
			ps.setInt(7, reimb.getResolver());
			ps.setInt(8, reimb.getStatus());
			ps.setInt(9, reimb.getType());
			
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

//			String sql = "SELECT * FROM ERS_REIMBURSEMENT "
//				+ "LEFT JOIN ERS_USERS ON (ERS_REIMBURSEMENT.reimb_author = ERS_USERS.ers_user_id) "
//				+ "WHERE ERS_USERS.ers_user_id = ?";
			
			String sql = "SELECT * FROM ERS_REIMBURSEMENT "
					+ "LEFT JOIN ERS_USERS ON (ERS_REIMBURSEMENT.reimb_author = ERS_USERS.ers_user_id) "
					+ "LEFT JOIN ERS_REIMBURSEMENT_STATUS ON (ERS_REIMBURSEMENT.reimb_status_id = ERS_REIMBURSEMENT_STATUS.reimb_status_id) "
					+ "WHERE ERS_REIMBURSEMENT_STATUS.reimb_status = ?";		
			
			PreparedStatement ps = c.prepareStatement(sql);
//			ps.setInt(1, statusId);
			ps.setString(1, status);

			ResultSet rs = ps.executeQuery();
			List<Reimbursement> t = new ArrayList<>();
			while (rs.next()) {
				t.add(extractReimbursement(rs));
			}
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
