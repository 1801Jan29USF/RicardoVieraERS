package com.revature.dao;



import com.revature.beans.ERS_User;

/**
 * User DAO interface.
 * @author ricky
 *
 */
public interface UserDAO {
	
	void save();
	void update();
	ERS_User login(String username, String password);
}
