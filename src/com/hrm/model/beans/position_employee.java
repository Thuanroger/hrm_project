package com.hrm.model.beans;

import java.sql.Date;

public class position_employee {
	private int row;
	private int Id;
	private int employee_id;
	private int position_id;
	private String description;
	private Date created_at;
	private int flag;
	private String employee_name;
	private String position_name;

	public position_employee() {
		// TODO Auto-generated constructor stub
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getRow() {
		return row;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void setId(int id) {
		Id = id;
	}

	public void setPosition_id(int position_id) {
		this.position_id = position_id;
	}

	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public String getDescription() {
		return description;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public int getFlag() {
		return flag;
	}

	public int getId() {
		return Id;
	}

	public int getPosition_id() {
		return position_id;
	}

	public String getPosition_name() {
		return position_name;
	}

}
