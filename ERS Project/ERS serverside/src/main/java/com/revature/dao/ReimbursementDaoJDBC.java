package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;
import com.revature.beans.ERS_Reimbursement;
import com.revature.util.ConnectionUtil;


/**
 * Reimbursement JDBC.
 * @author ricky
 *
 */
public class ReimbursementDaoJDBC implements ReimbursementDAO {

	private Logger log = Logger.getRootLogger();
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	/**
	 * Method to insert a test entry in the DB.
	 */
	@Override
	public void save() {
		Date date = new Date();
		log.trace("Method called to create a reimbursement ticket.");
		log.trace("Attempting to get connection to db.");
		try (Connection conn = connUtil.getConnection()) {
			log.trace("Connection established with db, creating prepared statement.");
			PreparedStatement ps = conn.prepareStatement(
					"Insert into ers_reimbursement(REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID)"
							+ "values(?,?,?,?,?,?)");
			ps.setDouble(1, 30);
			ps.setTimestamp(2, new Timestamp(date.getTime()));
			ps.setString(3, "plane ticket and travel expenses");
			ps.setInt(4, 9);
			ps.setInt(5, 1);
			ps.setInt(6, 2);
			int rowsInserted = ps.executeUpdate();
			log.debug("this query inserted: " + rowsInserted + "row in the db.");
		} catch (SQLException e) {
			log.warn("Failed to create a ticket.");
			e.printStackTrace();
		}
	}

	/**
	 * The actual save method that the view calls to store new reimbursement requests.
	 * @param amount
	 * @param description
	 * @param author_id
	 * @param type_id
	 */
	public void save(double amount, String description, int author_id, int type_id) {
		Date date = new Date();
		log.trace("Method called to create a reimbursement ticket.");
		log.trace("Attempting to get connection to db.");
		try (Connection conn = connUtil.getConnection()) {
			log.trace("Connection established with db, creating prepared statement.");
			PreparedStatement ps = conn.prepareStatement(
					"Insert into ers_reimbursement(REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID)"
							+ "values(?,?,?,?,?,?)");
			ps.setDouble(1, amount);
			ps.setTimestamp(2, new Timestamp(date.getTime()));
			ps.setString(3, description);
			ps.setInt(4, author_id);
			ps.setInt(5, 1);
			ps.setInt(6, type_id);
			int rowsInserted = ps.executeUpdate();
			log.debug("this query inserted: " + rowsInserted + "row in the db.");
		} catch (SQLException e) {
			log.warn("Failed to create a ticket. You have been logged out");
			// LoginServlet.u=null;
			e.printStackTrace();
		}

	}

	/**
	 * Find requests of an user by the author Id.
	 */
	@Override
	public ArrayList<ERS_Reimbursement> getRequests(int authorId) {
		ArrayList<ERS_Reimbursement> ar = new ArrayList<>();

		log.trace("Method called to retreive requests.");
		log.trace("Attempting to get connection to db.");
		try (Connection conn = connUtil.getConnection()) {
			log.trace("Connection established with db, creating prepared statement.");
			PreparedStatement ps = conn.prepareStatement("Select * from ers_reimbursement where REIMB_AUTHOR =?");
			ps.setInt(1, authorId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ERS_Reimbursement tick = new ERS_Reimbursement(rs.getInt("REIMB_ID"), rs.getDouble("REIMB_AMOUNT"),
						rs.getTimestamp("REIMB_SUBMITTED"), rs.getTimestamp("REIMB_RESOLVED"),
						rs.getString("REIMB_DESCRIPTION"), rs.getInt("REIMB_AUTHOR"), rs.getInt("REIMB_RESOLVER"),
						rs.getInt("REIMB_STATUS_ID"), rs.getInt("REIMB_TYPE_ID"));
				ar.add(tick);

			}
		} catch (SQLException e) {
			log.warn("Failed to retrieve tickets.");
			e.printStackTrace();
		}
		log.trace("Tickets have been retrieved");
		return ar;
	}

	/**
	 * Get pending requests for managers. Not including their own
	 */
	@Override
	public ArrayList<ERS_Reimbursement> getRequests_Manager(int authorId) {
		ArrayList<ERS_Reimbursement> ar = new ArrayList<>();

		log.trace("Method called to retreive requests.");
		log.trace("Attempting to get connection to db.");
		try (Connection conn = connUtil.getConnection()) {
			log.trace("Connection established with db, creating prepared statement.");
			PreparedStatement ps = conn
					.prepareStatement("Select * from ers_reimbursement where REIMB_AUTHOR !=? and REIMB_STATUS_ID=?");
			ps.setInt(1, authorId);
			ps.setInt(2, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ERS_Reimbursement tick = new ERS_Reimbursement(rs.getInt("REIMB_ID"), rs.getDouble("REIMB_AMOUNT"),
						rs.getTimestamp("REIMB_SUBMITTED"), rs.getTimestamp("REIMB_RESOLVED"),
						rs.getString("REIMB_DESCRIPTION"), rs.getInt("REIMB_AUTHOR"), rs.getInt("REIMB_RESOLVER"),
						rs.getInt("REIMB_STATUS_ID"), rs.getInt("REIMB_TYPE_ID"));
				ar.add(tick);

			}
		} catch (SQLException e) {
			log.warn("Failed to retrieve tickets.");
			e.printStackTrace();
		}
		log.trace("Tickets have been retrieved");
		return ar;
	}

	/**
	 * Approving or denying a request. It also sets the time and the resolver Id.
	 */
	@Override
	public void update(int rId, int status_id, int uId) {
		Date date = new Date();
		log.trace("Method called to retreive requests.");
		log.trace("Attempting to get connection to db.");
		try (Connection conn = connUtil.getConnection()) {
			log.trace("Connection established with db, creating prepared statement.");
			PreparedStatement ps = conn.prepareStatement("Update ers_reimbursement "
					+ "set REIMB_STATUS_ID=?, REIMB_RESOLVED=?, REIMB_RESOLVER=? " + "where reimb_id=?");
			ps.setInt(1, status_id);
			ps.setTimestamp(2, new Timestamp(date.getTime()));
			ps.setInt(3, uId);
			ps.setInt(4, rId);
			int rowsInserted = ps.executeUpdate();
			log.debug("this query updated: " + rowsInserted + "row in the db.");
		} catch (SQLException e) {
			log.warn("Failed to update tickets.");
			e.printStackTrace();
		}
		log.trace("Status has been updated");

	}

}
