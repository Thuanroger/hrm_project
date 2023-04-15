package com.hrm.model.business_objects;

import java.sql.Connection;
import java.sql.SQLException;

import com.hrm.model.beans.position;
import com.hrm.model.data_access_object.DAO;
import com.hrm.model.data_access_object.connection_db;
import com.hrm.model.data_access_object.crud;
import com.hrm.model.data_access_object.positionDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class bo_position implements DAO<position> {

	private static Connection conn = connection_db.getConnection();
	static String sql = "";

	public bo_position() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<position> getAll() {
		positionDAO dpDao = new positionDAO();
		// TODO Auto-generated method stub

		return dpDao.getAll();
	}

	@Override
	public boolean save(position t) {
		positionDAO dpDao = new positionDAO();
		return dpDao.save(t);
	}

	@Override
	public boolean update(position t) {
		// TODO Auto-generated method stub

		positionDAO dpDao = new positionDAO();
		return dpDao.update(t);

	}

	@Override
	public boolean delete(position t) {

		// TODO Auto-generated method stub
		boolean check = false;
		crud<position> depCrud = new crud<position>(conn) {
		};
		try {
			check = depCrud.Delete("position", t.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;

	}

}
