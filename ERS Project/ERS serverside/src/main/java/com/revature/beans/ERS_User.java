package com.revature.beans;

/**
 * User bean.
 * @author ricky
 *
 */
public class ERS_User {
	private int uId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int roleId;
	
	public ERS_User() {
		super();
		
	}
	
	

	public ERS_User(int uId, String username, String password, String firstName, String lastName, String email,
			int roleId) {
		super();
		this.uId = uId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roleId = roleId;
	}



	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}



	@Override
	public String toString() {
		return "ERS_User [uId=" + uId + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", roleId=" + roleId + "]";
	}
	
	
}
