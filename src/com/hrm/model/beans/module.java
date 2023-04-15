package com.hrm.model.beans;

public class module {
	private int row;
	private int id;
	private String module_name;
	private String description;
	private int flag;

	public module() {
		// TODO Auto-generated constructor stub
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getRow() {
		return row;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String descriptionString) {
		this.description = descriptionString;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
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

	public String getModule_name() {
		return module_name;
	}

}
