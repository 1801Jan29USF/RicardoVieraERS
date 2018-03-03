package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlets.DefaultServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.ERS_Reimbursement;
import com.revature.dao.ReimbursementDaoJDBC;

/**
 * Servlet that handles updating the status of a request.
 * @author ricky
 *
 */
public class UpdateServlet extends DefaultServlet {

	private static final long serialVersionUID = 1L;
	private ReimbursementDaoJDBC ticketService = new ReimbursementDaoJDBC();
	
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
	 * Get request for manager. This retrieves pending request that do not include their own.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ArrayList<ERS_Reimbursement> ar = new ArrayList<>();
		ar = ticketService.getRequests_Manager(LoginServlet.u.getuId());
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(ar);
		response.getWriter().write(json);
		
	}
	
	/**
	 * Calls the DAO method to update the status of a request.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String json = request.getReader().lines().reduce((acc, cur) -> acc + cur).get();
		ObjectMapper om = new ObjectMapper();
		ERS_Reimbursement updateRequest = (ERS_Reimbursement) om.readValue(json, ERS_Reimbursement.class);
		System.out.println(updateRequest.getrId()+" "+updateRequest.getStatus_id());
		ticketService.update(updateRequest.getrId(), updateRequest.getStatus_id(),LoginServlet.u.getuId());

		if (LoginServlet.u != null) {
			HttpSession sess = request.getSession();
			sess.setAttribute("user", LoginServlet.u);
			String respjson = om.writeValueAsString(LoginServlet.u);

			response.getWriter().write(respjson);
		} else {
			response.setStatus(401);
		}
		
	}
	
	

}
