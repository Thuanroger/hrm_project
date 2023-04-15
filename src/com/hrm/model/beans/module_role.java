package com.hrm.model.beans;

public class module_role {
	int row;
	private int Id;
	private int module_id;
	private int role_id;
	private String description;
	private int flag;
	private String module_name;
	private String role_name;

	public module_role() {
		// TODO Auto-generated constructor stub
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getRow() {
		return row;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void setId(int id) {
		Id = id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getDescription() {
		return description;
	}

	public int getFlag() {
		return flag;
	}

	public int getId() {
		return Id;
	}

	public int getModule_id() {
		return module_id;
	}

	public String getModule_name() {
		return module_name;
	}

	public int getRole_id() {
		return role_id;
	}

	public String getRole_name() {
		return role_name;
	}

}
