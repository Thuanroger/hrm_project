package com.hrm.model.beans;

import java.sql.Date;

import com.hrm.controller.setting_controller;

public class principal {
	private int row;
	private int id;
	private int employee_id;
	private String description;
	private String type;
	private Date date_principal;
	private int value_money;
	private Date created_at;
	private int flag;
	private String employee_name;
	private String department_name;
	private String getMoneyString;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;

	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getRow() {
		return row;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate_principal() {
		return date_principal;
	}

	public void setDate_principal(Date date_principal) {
		this.date_principal = date_principal;
	}

	public int getValue_money() {
		return value_money;
	}

	public void setValue_money(int value_money) {
		this.value_money = value_money;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setGetMoneyString(String string) {
		this.getMoneyString = string;
	}

	public String getGetMoneyString() {
		return getMoneyString;
	}

	public principal() {
		// TODO Auto-generated constructor stub
	}

	public principal(int id, int employee_id, String description, String type, Date date_principal, int value_money,
			Date created_at, int flag) {
		this.id = id;
		this.employee_id = employee_id;
		this.description = description;
		this.type = type;
		this.date_principal = date_principal;
		this.value_money = value_money;
		this.created_at = created_at;
		this.flag = flag;
	}
}
