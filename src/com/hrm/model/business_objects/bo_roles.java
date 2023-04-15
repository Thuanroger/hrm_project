package com.hrm.model.business_objects;

import com.hrm.model.beans.role;
import com.hrm.model.data_access_object.DAO;
import com.hrm.model.data_access_object.roleDAO;

import javafx.collections.ObservableList;

public class bo_roles implements DAO<role> {
	static roleDAO dataDao = new roleDAO();

	public bo_roles() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<role> getAll() {
		// TODO Auto-generated method stub
		return dataDao.getAll();
	}

	@Override
	public boolean save(role t) {
		// TODO Auto-generated method stub
		return dataDao.save(t);
	}

	@Override
	public boolean update(role t) {
		// TODO Auto-generated method stub
		return dataDao.update(t);
	}

	public role getRole(int id) {
		return dataDao.getRoleInt(id);
	}

	@Override
	public boolean delete(role t) {
		// TODO Auto-generated method stub
		return dataDao.delete(t);
	}

}
