package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hrm.model.beans.department;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class departmentDAO implements DAO<department> {

	static String sql = "";
	static Connection conn = connection_db.getConnection();

	public departmentDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<department> getAll() {
		ObservableList<department> departmentList = FXCollections.observableArrayList();
		// TODO Auto-generated method stub
		try {
			Connection conn = connection_db.getConnection();
			// Step 2
			sql = "SELECT D.id,D.department_name,D.description,D.created_at,ROW_NUMBER() OVER()  FROM hrm.department  AS D WHERE D.flag=0 ORDER BY D.id DESC";
			// Step 3
			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				department depar = new department();
				depar.setDepartment_Id(rs.getInt(1));
				depar.setDepartment_name(rs.getString(2));
				depar.setDescription(rs.getString(3));
				depar.setCreated_at(rs.getDate(4));
				depar.setRow(rs.getInt(5));
				departmentList.add(depar);

			}
			// Step 5
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return departmentList;
	}

	public department getDepartment(int id) {
		department depar = new department();
		// TODO Auto-generated method stub
		try {

			// Step 2
			sql = "SELECT D.id,D.department_name,D.description,D.created_at FROM hrm.department  AS D WHERE D.id=?";

			// Step 3
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			// Step 4
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				depar.setDepartment_Id(rs.getInt(1));
				depar.setDepartment_name(rs.getString(2));
				depar.setDescription(rs.getString(3));
				depar.setCreated_at(rs.getDate(4));

			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return depar;

	}

	@Override
	public boolean save(department t) {
		boolean check = false;
		// TODO Auto-generated method stub
		try {

			sql = "INSERT INTO `hrm`.`department` (`department_name`, `description`, `created_at`) VALUES (?, ?, ?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getDepartment_name());
			pst.setString(2, t.getDescription());
			pst.setDate(3, t.getCreated_at());

			int rowInsert = pst.executeUpdate();
			if (rowInsert > 0) {
				check = true;
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public boolean update(department t) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE hrm.department AS D SET D.department_name=?,D.description=?,D.created_at=? WHERE D.id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getDepartment_name());
			pst.setString(2, t.getDescription());
			pst.setDate(3, t.getCreated_at());
			pst.setInt(4, t.getDepartment_Id());
			int rowInsert = pst.executeUpdate();

			if (rowInsert > 0) {
				check = true;
			}
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;

	}

	@Override
	public boolean delete(department t) {

		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE hrm.department AS D SET D.flag=1 WHERE D.id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getDepartment_Id());

			int rowInsert = pst.executeUpdate();

			if (rowInsert > 0) {
				check = true;
			}
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;

	}

//	@Override
//	public void refresh() {
//		// TODO Auto-generated method stub
//		try {
//			ObservableList<department> studentList = FXCollections.observableArrayList();
//
//			studentList.clear();
//
//			Connection conn = connection.getConnection();
//			// Step 2
//			sql = "SELECT *FROM department";
//			// Step 3
//			PreparedStatement pst = conn.prepareStatement(sql);
//			// Step 4
//			ResultSet rs = pst.executeQuery();
//
//			while (rs.next()) {
//				department depar = new department();
//				depar.setDepartment_Id(rs.getInt(1));
//				depar.setDepartment_name(rs.getString(2));
//				depar.setDescription(rs.getString(3));
//				depar.setCreated_at(rs.getDate(4));
//				studentList.add(depar);
//
//			}
//			// Step 5
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

}
