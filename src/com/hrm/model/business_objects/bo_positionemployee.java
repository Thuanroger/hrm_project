package com.hrm.model.business_objects;

import java.sql.Connection;
import java.sql.SQLException;

import com.hrm.model.beans.position;
import com.hrm.model.beans.position_employee;
import com.hrm.model.data_access_object.DAO;
import com.hrm.model.data_access_object.connection_db;
import com.hrm.model.data_access_object.crud;
import com.hrm.model.data_access_object.positionDAO;
import com.hrm.model.data_access_object.positionemployeeDAO;
import com.hrm.model.data_access_object.principalDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class bo_positionemployee implements DAO<position_employee> {
	static Connection conn = connection_db.getConnection();
	static positionemployeeDAO dpDao = new positionemployeeDAO();

	public bo_positionemployee() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<position_employee> getAll() {
		// TODO Auto-generated method stub

		return dpDao.getList();
	}

	@Override
	public boolean save(position_employee t) {
		// TODO Auto-generated method stub

		return dpDao.save(t);
	}

	@Override
	public boolean update(position_employee t) {
		// TODO Auto-generated method stub

		return dpDao.update(t);
	}

	@Override
	public boolean delete(position_employee t) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		boolean check = false;
		crud<position_employee> depCrud = new crud<position_employee>(conn) {
		};
		try {
			check = depCrud.Delete("position_employee", t.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;
	}

}
