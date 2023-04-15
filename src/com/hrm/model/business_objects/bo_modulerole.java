package com.hrm.model.business_objects;

import com.hrm.model.beans.module;
import com.hrm.model.beans.module_role;
import com.hrm.model.beans.role;
import com.hrm.model.data_access_object.DAO;
import com.hrm.model.data_access_object.moduleDAO;
import com.hrm.model.data_access_object.module_roleDAO;
import com.hrm.model.data_access_object.roleDAO;

import javafx.collections.ObservableList;

public class bo_modulerole implements DAO<module_role> {
	module_roleDAO dataDao = new module_roleDAO();
	roleDAO roleDAo = new roleDAO();
	moduleDAO moduleDAo = new moduleDAO();

	public bo_modulerole() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<module_role> getAll() {
		// TODO Auto-generated method stub
		return dataDao.getAll();
	}

	@Override
	public boolean save(module_role t) {
		// TODO Auto-generated method stub
		return dataDao.save(t);
	}

	@Override
	public boolean update(module_role t) {
		// TODO Auto-generated method stub
		return dataDao.update(t);
	}

	@Override
	public boolean delete(module_role t) {
		// TODO Auto-generated method stub
		return dataDao.delete(t);
	}

	public role getRole(String st) {
		return roleDAo.getRole(st);
	}

	public module getModule(String st) {
		return moduleDAo.getModue(st);
	}

}
