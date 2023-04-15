package com.hrm.model.business_objects;

import com.hrm.model.beans.task;
import com.hrm.model.data_access_object.DAO;
import com.hrm.model.data_access_object.taskDAO;

import javafx.collections.ObservableList;

public class bo_task implements DAO<task> {
	private taskDAO dataDao = new taskDAO();

	public bo_task() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<task> getAll() {
		// TODO Auto-generated method stub
		return dataDao.getAll();
	}

	@Override
	public boolean save(task t) {
		// TODO Auto-generated method stub
		return dataDao.save(t);
	}

	@Override
	public boolean update(task t) {
		// TODO Auto-generated method stub
		return dataDao.update(t);
	}

	@Override
	public boolean delete(task t) {
		// TODO Auto-generated method stub
		return dataDao.delete(t);
	}

}
