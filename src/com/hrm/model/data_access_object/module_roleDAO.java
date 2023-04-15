package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hrm.model.beans.module_role;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class module_roleDAO implements DAO<module_role> {
	static Connection conn = connection_db.getConnection();
	static String sql = "";

	public module_roleDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<module_role> getAll() {
		ObservableList<module_role> module_roleList = FXCollections.observableArrayList();
		// TODO Auto-generated method stub
		try {

			// Step 2
			sql = "SELECT R.id,MR.module_name ,RO.role_name,R.description,R.module_id,R.role_id,ROW_NUMBER() OVER()  FROM  hrm.module_role AS R \r\n"
					+ "INNER JOIN hrm.module  AS MR ON  MR.id=R.module_id  \r\n"
					+ "INNER Join hrm.role AS RO ON RO.id=R.role_id  WHERE R.flag=0;";

			// Step 3
			PreparedStatement pst = conn.prepareStatement(sql);

			// Step 4
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				module_role MD = new module_role();
				MD.setId(rs.getInt(1));
				MD.setModule_name(rs.getString(2));
				MD.setRole_name(rs.getString(3));
				MD.setDescription(rs.getString(4));
				MD.setModule_id(rs.getInt(5));
				MD.setRole_id(rs.getInt(6));
				MD.setRow(rs.getInt(7));
				module_roleList.add(MD);

			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return module_roleList;
	}

	@Override
	public boolean save(module_role t) {
		boolean check = false;
		// TODO Auto-generated method stub
		try {

			sql = "INSERT INTO `hrm`.`module_role` (`module_id`, `role_id`, `description`) VALUES (?,?,?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getModule_id());
			pst.setInt(2, t.getRole_id());
			pst.setString(3, t.getDescription());

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
	public boolean update(module_role t) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE `hrm`.`module_role` SET `module_id`=?,`role_id`=?, `description`=? WHERE  `id`=?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getModule_id());
			pst.setInt(2, t.getRole_id());
			pst.setString(3, t.getDescription());
			pst.setInt(4, t.getId());
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
	public boolean delete(module_role t) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE `hrm`.`module_role` SET `flag`='1' WHERE  `id`=?;";
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
