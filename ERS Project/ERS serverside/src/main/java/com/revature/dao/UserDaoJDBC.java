package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.beans.ERS_User;
import com.revature.util.ConnectionUtil;

/**
 * User JDBC.
 * @author ricky
 *
 */
public class UserDaoJDBC implements UserDAO {

	private Logger log = Logger.getRootLogger();
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	/**
	 * Test method to insert data in the database.
	 */
	@Override
	public void save() {
		log.trace("Method called to insert new ERS user.");
		log.trace("Attempting to get connection to db.");
		try (Connection conn = connUtil.getConnection()) {
			log.trace("Connection established with db, creating prepared statement.");
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO ERS_USERS(ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID) VALUES(?,?,?,?,?,?)");
			ps.setString(1, "second");
			ps.setString(2, "worker");
			ps.setString(3, "Dante");
			ps.setString(4, "Inferno");
			ps.setString(5, "dante@ersemployee.com");
			ps.setInt(6, 1);
			int rowsInserted = ps.executeUpdate();
			log.debug("This query inserted: " + rowsInserted + "row in the db.");
		} catch (SQLException e) {
			log.warn("Failed to insert user.");
			e.printStackTrace();
		}

	}

	/**
	 * Not implemented.
	 */
	@Override
	public void update() {
		

	}

	/**
	 * Finding the right user and returning their info.
	 */
	@Override
	public ERS_User login(String username, String password) {

		log.trace("Method called to log in.");
		log.trace("Attempting to get connection to db.");
		try (Connection conn = connUtil.getConnection()) {
			log.trace("Connection established with db, creating prepared statement.");
			PreparedStatement ps = conn
					.prepareStatement("Select * from ers_users where ERS_USERNAME=? and ERS_PASSWORD=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				;
				ERS_User u = new ERS_User(rs.getInt("ERS_USERS_ID"), rs.getString("ERS_USERNAME"),
						rs.getString("ERS_PASSWORD"), rs.getString("USER_FIRST_NAME"), rs.getString("USER_LAST_NAME"),
						rs.getString("USER_EMAIL"), rs.getInt("USER_ROLE_ID"));
				log.trace("Logged in!");
				return u;
			}
		} catch (SQLException e) {
			log.warn("Failed to reach the database.");

		}
		log.warn("Failed to log in!");
		return null;
	}

}
