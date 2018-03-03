package com.revature.beans;


import java.sql.Timestamp;

/**
 * Reimbursement bean.
 * @author ricky
 *
 */
public class ERS_Reimbursement {
	private int rId;
	private double amount;
	private Timestamp dOf_submitted;
	private Timestamp dOf_resolved;
	private String description;
	private int author_id;
	private int resolver_id;
	private int status_id;
	private int type_id;
	
	public ERS_Reimbursement() {
		super();
		
	}

	public ERS_Reimbursement(int rId, double amount, Timestamp dOf_submitted, Timestamp dOf_resolved, String description,
			 int author_id, int resolver_id, int status_id, int type_id) {
		super();
		this.rId = rId;
		this.amount = amount;
		this.dOf_submitted = dOf_submitted;
		this.dOf_resolved = dOf_resolved;
		this.description = description;
		//this.b = b;
		this.author_id = author_id;
		this.resolver_id = resolver_id;
		this.status_id = status_id;
		this.type_id = type_id;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getdOf_submitted() {
		return dOf_submitted;
	}

	public void setdOf_submitted(Timestamp dOf_submitted) {
		this.dOf_submitted = dOf_submitted;
	}

	public Timestamp getdOf_resolved() {
		return dOf_resolved;
	}

	public void setdOf_resolved(Timestamp dOf_resolved) {
		this.dOf_resolved = dOf_resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public Blob getB() {
//		return b;
//	}
//
//	public void setB(Blob b) {
//		this.b = b;
//	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public int getResolver_id() {
		return resolver_id;
	}

	public void setResolver_id(int resolver_id) {
		this.resolver_id = resolver_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	@Override
	public String toString() {
		return "ERS_Reimbursement [rId=" + rId + ", amount=" + amount + ", dOf_submitted=" + dOf_submitted
				+ ", dOf_resolved=" + dOf_resolved + ", description=" + description + ", author_id="
				+ author_id + ", resolver_id=" + resolver_id + ", status_id=" + status_id + ", type_id=" + type_id
				+ "]";
	}
	
	
}
