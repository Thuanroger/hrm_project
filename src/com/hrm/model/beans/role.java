package com.hrm.model.beans;

import java.lang.invoke.StringConcatFactory;
import java.sql.Date;

public class role {
	int row;
	private int id;
	private String role_name;
	private String description;
	private Date create_at;
	private int flag;

	public role() {
		// TODO Auto-generated constructor stub
	}

	public void setCreate_at(Date create_atDate) {
		this.create_at = create_atDate;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setDescription(String descriptionString) {
		this.description = descriptionString;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public String getDescription() {
		return description;
	}

	public int getFlag() {
		return flag;
	}

	public int getId() {
		return id;
	}

	public String getRole_name() {
		return role_name;
	}

}
