package com.hrm.model.beans;

import java.sql.Date;

import com.mysql.cj.protocol.a.NativeConstants.StringLengthDataType;

public class task {
	private int row;
	private int Id;
	private String title;
	private String description;
	private int assignee;
	private Date deadline;
	private String priority;
	private String status;
	private Date create_at;
	private int flag;
	private String nameString;
	private int quantity_task;
	private int quantity_task_done;
	private String month_by_task;

	public task() {
		// TODO Auto-generated constructor stub
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getMonth_by_task() {
		return month_by_task;
	}

	public int getQuantity_task() {
		return quantity_task;
	}

	public int getQuantity_task_done() {
		return quantity_task_done;
	}

	public void setMonth_by_task(String month_by_task) {
		this.month_by_task = month_by_task;
	}

	public void setQuantity_task(int quantity_task) {
		this.quantity_task = quantity_task;
	}

	public void setQuantity_task_done(int quantity_task_done) {
		this.quantity_task_done = quantity_task_done;
	}

	public void setAssignee(int assignee) {
		this.assignee = assignee;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void setId(int id) {
		Id = id;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAssignee() {
		return assignee;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public Date getDeadline() {
		return deadline;
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

	public String getPriority() {
		return priority;
	}

	public String getStatus() {
		return status;
	}

	public String getTitle() {
		return title;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public String getNameString() {
		return nameString;
	}

}
