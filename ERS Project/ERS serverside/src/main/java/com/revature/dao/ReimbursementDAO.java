package com.revature.dao;

import java.util.ArrayList;

import com.revature.beans.ERS_Reimbursement;

/**
 * Reimbursement DAO interface.
 * @author ricky
 *
 */
public interface ReimbursementDAO {
	void save();
	void update(int rId, int status_id , int uId);
	ArrayList<ERS_Reimbursement> getRequests(int authorId);
	ArrayList<ERS_Reimbursement> getRequests_Manager(int authorId);
}
