package com.hrm.model.business_objects;

import com.hrm.model.beans.taskDerpartment;
import com.hrm.model.data_access_object.DAO;
import com.hrm.model.data_access_object.taskDerpartmentDAO;

import javafx.collections.ObservableList;

public class bo_taskDerpartment implements DAO<taskDerpartment> {
	private taskDerpartmentDAO dataDAO = new taskDerpartmentDAO();

	public bo_taskDerpartment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<taskDerpartment> getAll() {
		// TODO Auto-generated method stub
		return dataDAO.getAll();
	}

	@Override
	public boolean save(taskDerpartment t) {
		// TODO Auto-generated method stub
		return dataDAO.save(t);
	}

	@Override
	public boolean update(taskDerpartment t) {
		// TODO Auto-generated method stub
		return dataDAO.update(t);
	}

	@Override
	public boolean delete(taskDerpartment t) {
		// TODO Auto-generated method stub
		return dataDAO.delete(t);
	}

}
