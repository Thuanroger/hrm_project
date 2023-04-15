package com.hrm.model.business_objects;

import java.sql.Connection;
import java.sql.SQLException;

import com.hrm.model.beans.employee_search;
import com.hrm.model.beans.principal;
import com.hrm.model.data_access_object.DAO;
import com.hrm.model.data_access_object.connection_db;
import com.hrm.model.data_access_object.crud;
import com.hrm.model.data_access_object.positionDAO;
import com.hrm.model.data_access_object.principalDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class bo_principal implements DAO<principal> {
	private static Connection conn = connection_db.getConnection();

	public bo_principal() {
		// TODO Auto-generated constructor stub
	}

	public ObservableList<employee_search> getSearchs() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		principalDAO dpDao = new principalDAO();
		return dpDao.getSearchs();

	}

	@Override
	public boolean save(principal t) {
		// TODO Auto-generated method stub
		principalDAO dpDao = new principalDAO();
		return dpDao.save(t);
	}

	@Override
	public boolean update(principal t) {
		// TODO Auto-generated method stub
		principalDAO dpDao = new principalDAO();
		return dpDao.update(t);
	}

	@Override
	public boolean delete(principal t) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		boolean check = false;
		crud<principal> depCrud = new crud<principal>(conn) {
		};
		try {
			check = depCrud.Delete("principal", t.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;

	}

	public ObservableList<principal> getList() {
		principalDAO dbDao = new principalDAO();
		return dbDao.getPrincipals();

	}

	@Override
	public ObservableList<principal> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
