package com.hrm.model.business_objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.hrm.model.beans.department;
import com.hrm.model.data_access_object.DAO;
import com.hrm.model.data_access_object.connection_db;
import com.hrm.model.data_access_object.crud;
import com.hrm.model.data_access_object.departmentDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class bo_department implements DAO<department> {
	private static Connection conn = connection_db.getConnection();
	static String sql = "";

	public bo_department() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<department> getAll() {
		departmentDAO dpDao = new departmentDAO();

		return dpDao.getAll();
	}

	public department getDepartment(int id) {
		departmentDAO dpDao = new departmentDAO();
		return dpDao.getDepartment(id);
	}

	@Override
	public boolean save(department t) {
		crud<department> depCrud = new crud<department>(conn) {
		};

		boolean check = false;
		try {
			check = depCrud.add("department", t);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

		return check;
	}

	@Override
	public boolean update(department t) {
		// TODO Auto-generated method stub

		departmentDAO dpDao = new departmentDAO();
		return dpDao.update(t);

	}

	@Override
	public boolean delete(department t) {

		// TODO Auto-generated method stub
		boolean check = false;
		crud<department> depCrud = new crud<department>(conn) {
		};
		try {
			check = depCrud.Delete("department", t.getDepartment_Id());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;

	}

}
