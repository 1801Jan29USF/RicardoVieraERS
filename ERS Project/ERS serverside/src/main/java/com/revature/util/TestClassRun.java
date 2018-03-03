package com.revature.util;

import java.sql.ResultSet;

import com.revature.dao.ReimbursementDaoJDBC;
import com.revature.dao.UserDaoJDBC;

/**
 * Test class that was used.
 * @author ricky
 *
 */
public class TestClassRun {
	public static void main(String[] args) throws Exception {
		UserDaoJDBC ud =new UserDaoJDBC();
		ReimbursementDaoJDBC rd = new ReimbursementDaoJDBC();
		rd.save();
		ud.save();
		
		ResultSet rs=(ResultSet) ud.login("rick", "pass");
		if(rs==null) {
			System.out.println("Failed to log in");
		}
		else {
			System.out.println("Logged in!");
		}
		
	}
}
