package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hrm.model.beans.department;
import com.hrm.model.beans.position_employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class positionemployeeDAO implements DAO<position_employee> {
	static String sql = "";
	Connection conn = connection_db.getConnection();

	public positionemployeeDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<position_employee> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public ObservableList<position_employee> getList() {
		ObservableList<position_employee> List = FXCollections.observableArrayList();
		// TODO Auto-generated method stub
		try {

			// Step 2
			sql = "SELECT P.id ,P.employee_id,P.position_id,P.description,P.created_at,CONCAT(E.last_name,' ',E.middle_name,' ',E.first_name), PO.position_name,ROW_NUMBER() OVER() \r\n"
					+ "FROM hrm.position_employee AS P \r\n"
					+ "INNER JOIN hrm.employee AS E ON E.id =P.employee_id  \r\n"
					+ "INNER JOIN hrm.`position` AS PO ON PO.id=P.position_id WHERE P.flag=0 ORDER BY P.id";

			// Step 3
			PreparedStatement pst = conn.prepareStatement(sql);

			// Step 4
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				position_employee data = new position_employee();
				data.setId(rs.getInt(1));
				data.setEmployee_id(rs.getInt(2));
				data.setPosition_id(rs.getInt(3));
				data.setDescription(rs.getString(4));
				data.setCreated_at(rs.getDate(5));
				data.setEmployee_name(rs.getString(6));
				data.setPosition_name(rs.getString(7));
				data.setRow(rs.getInt(8));
				List.add(data);

			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return List;
	}

	@Override
	public boolean save(position_employee t) {
		boolean check = false;
		try {

			sql = "INSERT INTO position_employee (employee_id,position_id,created_at,description) VALUES (?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getEmployee_id());
			pst.setInt(2, t.getPosition_id());
			pst.setDate(3, t.getCreated_at());
			pst.setString(4, t.getDescription());
			int rowInsert = pst.executeUpdate();

			if (rowInsert > 0) {
				check = true;
			}
//			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public boolean update(position_employee t) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE hrm.position_employee  AS P SET P.employee_id=?,P.position_id=?,P.created_at=?,P.description=? WHERE P.id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getEmployee_id());
			pst.setInt(2, t.getPosition_id());
			pst.setDate(3, t.getCreated_at());
			pst.setString(4, t.getDescription());
			pst.setInt(5, t.getId());
			int rowUpdate = pst.executeUpdate();
			if (rowUpdate > 0) {
				check = true;
			}
//					conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public boolean delete(position_employee t) {
		// TODO Auto-generated method stub
		return false;
	}

}
