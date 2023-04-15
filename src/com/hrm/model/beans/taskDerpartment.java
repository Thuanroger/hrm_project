package com.hrm.model.beans;

public class taskDerpartment {
	private int row;
	private int Id;
	private int department_id;
	private int task_id;
	private String taskString;
	private String departmentString;
	private int flag;

	public taskDerpartment() {
		// TODO Auto-generated constructor stub
	}

	public String getDepartmentString() {
		return departmentString;
	}

	public void setDepartmentString(String departmentString) {
		this.departmentString = departmentString;
	}

	public void setId(int id) {
		Id = id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public void setTaskString(String taskString) {
		this.taskString = taskString;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getId() {
		return Id;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public int getTask_id() {
		return task_id;
	}

	public String getTaskString() {
		return taskString;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getFlag() {
		return flag;
	}

}
