package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hrm.model.beans.module;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class moduleDAO implements DAO<module> {
	static Connection conn = connection_db.getConnection();
	static String sql = "";

	public moduleDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<module> getAll() {
		ObservableList<module> moduleList = FXCollections.observableArrayList();
		// TODO Auto-generated method stub
		try {
			Connection conn = connection_db.getConnection();
			// Step 2
			sql = "SELECT id, `module_name`, `description`,ROW_NUMBER() OVER()  FROM `hrm`.`module` WHERE  `flag`=0";
			// Step 3
			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				module Module = new module();
				Module.setId(rs.getInt(1));
				Module.setModule_name(rs.getString(2));
				Module.setDescription(rs.getString(3));
				Module.setRow(rs.getInt(4));
				moduleList.add(Module);

			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return moduleList;
	}

	public module getModue(String st) {

		module Module = new module();
		// TODO Auto-generated method stub
		try {
			Connection conn = connection_db.getConnection();
			// Step 2
			sql = "SELECT id, module_name FROM module WHERE flag=0 and module_name=? ;";
			// Step 3
			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4
			pst.setString(1, st);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				Module.setId(rs.getInt(1));
				Module.setModule_name(rs.getString(2));
				return Module;
			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Module;
	}

	@Override
	public boolean save(module t) {
		boolean check = false;
		// TODO Auto-generated method stub
		try {

			sql = "INSERT INTO `hrm`.`module` (`module_name`, `description`) VALUES (?, ?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getModule_name());
			pst.setString(2, t.getDescription());

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
	public boolean update(module t) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE `hrm`.`module` SET `module_name`=?, `description`=? WHERE  `id`=?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getModule_name());
			pst.setString(2, t.getDescription());
			pst.setInt(3, t.getId());

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
	public boolean delete(module t) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE `hrm`.`module` SET `flag`='1' WHERE  `id`=?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getId());

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

	public static ObservableList<module> getModuleName(int role_id) {
		ObservableList<module> moduleNameList = FXCollections.observableArrayList();
		// TODO Auto-generated method stub
		try {
			Connection conn = connection_db.getConnection();

			sql = "SELECT module.module_name\r\n" + "FROM module_role\r\n"
					+ "INNER JOIN role ON role.id = module_role.role_id\r\n"
					+ "INNER JOIN module ON module.id = module_role.module_id\r\n"
					+ "INNER JOIN employee ON employee.role_id = role.id\r\n" + "WHERE employee.role_id = ?\r\n"
					+ "GROUP BY module.module_name\r\n" + "ORDER BY module.module_name";

			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, role_id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				module module = new module();
				module.setModule_name(rs.getString("module_name"));
				moduleNameList.add(module);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return moduleNameList;
	}

}
