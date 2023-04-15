package com.hrm.model.beans;

import java.sql.Date;

public class employee_search {
	private int row;
	private String fullname;
	private String avatar;
	private String department;
	private Date DOB;
	private int id;

	public employee_search() {
		// TODO Auto-generated constructor stub
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getDepartment() {
		return department;
	}

	public Date getDOB() {
		return DOB;
	}

	public String getFullname() {
		return fullname;
	}

	public int getId() {
		return id;
	}
}
