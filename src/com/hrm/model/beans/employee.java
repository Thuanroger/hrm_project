package com.hrm.model.beans;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class employee {
	private int row;
	private int id;
	private int role_id;
	private String username;
	private String password;
	private int on_leave;
	private String last_name;
	private String middle_name;
	private String first_name;
	private String email;
	private String address;
	private String telephone;
	private String avatar;
	private String description;
	private Date dob;
	private int status;
	private Date hire_date;
	private int flag;
	private position_employee position_employee;
	private department department;
	private position position;
	private salary salary;
	private principal principal;
	private role role;
	private int department_id;
	private String hire_month;
	private String termination_month;
	private int quantity_employee;
	private String Gender;

	public String getGender() {
		return Gender;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public position_employee getPosition_employee() {
		return position_employee;
	}

	public void setPosition_employee(position_employee position_employee) {
		this.position_employee = position_employee;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public department getDepartment() {
		return department;
	}

	public void setDepartment(department department) {
		this.department = department;
	}

	public position getPosition() {
		return position;
	}

	public void setPosition(position position) {
		this.position = position;
	}

	public salary getSalary() {
		return salary;
	}

	public void setSalary(salary salary) {
		this.salary = salary;
	}

	public principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(principal principal) {
		this.principal = principal;
	}

	public role getRole() {
		return role;
	}

	public void setRole(role role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getOn_leave() {
		return on_leave;
	}

	public void setOn_leave(int on_leave) {
		this.on_leave = on_leave;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDob() {
		DateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		formatDate.format(dob);
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	public String getHire_month() {
		return hire_month;
	}

	public int getQuantity_employee() {
		return quantity_employee;
	}

	public String getTermination_month() {
		return termination_month;
	}

	public void setHire_month(String hire_month) {
		this.hire_month = hire_month;
	}

	public void setQuantity_employee(int quantity_employee) {
		this.quantity_employee = quantity_employee;
	}

	public void setTermination_month(String termination_month) {
		this.termination_month = termination_month;
	}

	public employee() {
		// TODO Auto-generated constructor stub
	}

}