package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlets.DefaultServlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.ERS_User;

import com.revature.dao.UserDaoJDBC;

/**
 * The servlet that handles login in of employees.
 * @author ricky
 *
 */
public class LoginServlet extends DefaultServlet {

	private static final long serialVersionUID = 1L;
	private UserDaoJDBC userService = new UserDaoJDBC();
	public static ERS_User u = new ERS_User();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		resp.setContentType("application/json");
	}

	/**
	 * Returing the user role id to the view for handling of information.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		

		 ObjectMapper om = new ObjectMapper();
		 String json = om.writeValueAsString(u.getRoleId());
		 response.getWriter().write(json);
	}

	/**
	 * Login method.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String json = request.getReader().lines().reduce((acc, cur) -> acc + cur).get();
		ObjectMapper om = new ObjectMapper();
		ERS_User credentials = (ERS_User) om.readValue(json, ERS_User.class);
		u = (ERS_User) userService.login(credentials.getUsername(), credentials.getPassword());
		
		if (u != null) {
			HttpSession sess = request.getSession();
			sess.setAttribute("user", u);
			
			String respjson = om.writeValueAsString(u);
			response.getWriter().write(respjson);
		} else {
			response.setStatus(401);
		}
	}
}
