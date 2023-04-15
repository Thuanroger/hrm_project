package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.text.DefaultEditorKit.PasteAction;

import com.hrm.model.beans.taskDerpartment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class taskDerpartmentDAO implements DAO<taskDerpartment> {
	static Connection conn = connection_db.getConnection();
	static String sql = "";

	public taskDerpartmentDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<taskDerpartment> getAll() {
		ObservableList<taskDerpartment> List = FXCollections.observableArrayList();
		// TODO Auto-generated method stub
		try {
			Connection conn = connection_db.getConnection();
			// Step 2
			sql = "SELECT TP.id,TP.department_id,TP.task_id,T.title,D.department_name,ROW_NUMBER() OVER() FROM hrm.task_department AS TP LEFT JOIN hrm.department AS D ON D.id=TP.department_id LEFT JOIN hrm.task AS T ON T.id=TP.task_id\r\n"
					+ " WHERE TP.flag=0 ORDER  BY TP.id DESC";
			// Step 3
			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				taskDerpartment TP = new taskDerpartment();
				TP.setId(rs.getInt(1));
				TP.setDepartment_id(rs.getInt(2));
				TP.setTask_id(rs.getInt(3));
				TP.setTaskString(rs.getString(4));
				TP.setDepartmentString(rs.getString(5));
				TP.setRow(rs.getInt(6));
				List.add(TP);

			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return List;
	}

	@Override
	public boolean save(taskDerpartment t) {
		boolean check = false;
		// TODO Auto-generated method stub
		try {

			sql = "INSERT INTO `hrm`.`task_department` (`task_id`, `department_id`) VALUES (?, ?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(2, t.getDepartment_id());
			pst.setInt(1, t.getTask_id());

			int rowInsert = pst.executeUpdate();
			if (rowInsert > 0) {
				check = true;
			}
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public boolean update(taskDerpartment t) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE `hrm`.`task_department`  SET `department_id`=?, `task_id`=? WHERE  `id`=?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getDepartment_id());
			pst.setInt(2, t.getTask_id());
			pst.setInt(3, t.getId());

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
	public boolean delete(taskDerpartment t) {
		/// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE `hrm`.`task_department`  SET `flag`='1' WHERE  `id`=?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getId());

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

}
