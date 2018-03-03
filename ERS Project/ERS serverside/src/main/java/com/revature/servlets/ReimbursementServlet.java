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
 * Servlet that handles reimbursement logic.
 * @author ricky
 *
 */
public class ReimbursementServlet extends DefaultServlet {
	private static final long serialVersionUID = 1L;
	private ReimbursementDaoJDBC ticketService = new ReimbursementDaoJDBC();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		resp.setContentType("application/json");
	}

	/**
	 * Retrieving the history of requests.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ArrayList<ERS_Reimbursement> ar = new ArrayList<>();
		ar = ticketService.getRequests(LoginServlet.u.getuId());
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(ar);
		response.getWriter().write(json);
	}

	/**
	 * Inserting a new request in the database
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String json = request.getReader().lines().reduce((acc, cur) -> acc + cur).get();

		ObjectMapper om = new ObjectMapper();
		ERS_Reimbursement newRequest = (ERS_Reimbursement) om.readValue(json, ERS_Reimbursement.class);
		ticketService.save(newRequest.getAmount(), newRequest.getDescription(), LoginServlet.u.getuId(),
				Integer.valueOf(newRequest.getType_id()));

		if (LoginServlet.u != null && newRequest.getAmount() > 0) {
			HttpSession sess = request.getSession();
			sess.setAttribute("user", LoginServlet.u);
			String respjson = om.writeValueAsString(LoginServlet.u);

			response.getWriter().write(respjson);
		} else {
			response.setStatus(401);
		}

	}
}
