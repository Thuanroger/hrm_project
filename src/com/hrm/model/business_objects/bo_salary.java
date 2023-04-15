package com.hrm.model.business_objects;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hrm.model.beans.employee;
import com.hrm.model.beans.salary;
import com.hrm.model.data_access_object.DAO;
import com.hrm.model.data_access_object.connection_db;
import com.hrm.model.data_access_object.crud;
import com.hrm.model.data_access_object.salaryDAO;
import com.hrm.model.data_access_object.show_data;

import javafx.collections.ObservableList;

public class bo_salary implements DAO<salary> {
	static salaryDAO dataDao = new salaryDAO();

	public bo_salary() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean save(salary salary) {
		// TODO Auto-generated method stub
		return dataDao.save(salary);
	}

	@Override
	public boolean update(salary salary) {
		// TODO Auto-generated method stub
		return dataDao.update(salary);
	}

	public ObservableList<salary> getDerpartment(int id) {
		return dataDao.getAllDepartment(id);
	}

	public ObservableList<salary> getSalaryID(int id, String year) {
		return dataDao.getSalaryid(id, year);
	}

	public String getMoneyId(int id, String year) {
		return dataDao.getmoney(id, year);
	}

	public ObservableList<String> getYear() {
		return dataDao.getyear();
	}

	@Override
	public boolean delete(salary salary) {
		// TODO Auto-generated method stub
		return dataDao.delete(salary);
	}

	@Override
	public ObservableList<salary> getAll() {
		// TODO Auto-generated method stub
		return dataDao.getAll();
	}

}