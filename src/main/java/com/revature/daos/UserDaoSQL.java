package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDaoSQL implements UserDao {
	private Logger log = Logger.getRootLogger();
	
	User extractUser(ResultSet rs) throws SQLException {
		int id = rs.getInt("ers_user_id");
		String username = rs.getString("ers_username");
		String password = rs.getString("ers_password");
		String fName = rs.getString("user_first_name");
		String lName = rs.getString("user_last_name");
		String email = rs.getString("user_email");
		int role = rs.getInt("user_role_id");
		
		return new User(id, username, password, fName, lName, email, role);
	}

	@Override
	public int save(User u) {
		log.debug("attempting to find user by credentials from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO ERS_USERS (ers_user_id, ers_username, ers_password,user_first_name, user_last_name, user_email, user_role_id) "
					+ "VALUES (ERS_USERS_SEQ.nextval,?,?,?,?,?,?)";
			
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setString(5, u.getEmail());
			ps.setInt(6, u.getRole());
			
			return ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<User> findAll() {
		log.debug("attempting to find all users from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ERS_USERS";

			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<User>();
			while (rs.next()) {
				users.add(extractUser(rs));
			}
			return users;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findById(int id) {
		log.debug("attempting to find user by id from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ERS_USERS "
					+ "WHERE ers_user_id = ?";
		
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByUsername(String username) {
		log.debug("attempting to find user by its username from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM  ERS_USERS " + "WHERE ers_username = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		log.debug("attempting to find user by credentials from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ERS_USERS " + "WHERE ers_username = ? AND ers_password = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findRole(String role) {
		log.debug("attempting to find user by their role from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ERS_USERS "
					+ "LEFT JOIN ERS_USER_ROLES ON (ERS_USERS.user_role_id = ERS_USER_ROLES.ers_user_role_id)"
					+ "WHERE ERS_USER_ROLES.user_role = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, role);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
