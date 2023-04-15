package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.management.relation.Role;

import com.hrm.model.beans.module;
import com.hrm.model.beans.role;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class roleDAO implements DAO<role> {
	static Connection conn = connection_db.getConnection();
	static String sql = "";

	public roleDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<role> getAll() {
		ObservableList<role> roletList = FXCollections.observableArrayList();
		// TODO Auto-generated method stub
		try {
			Connection conn = connection_db.getConnection();
			// Step 2
			sql = "SELECT R.id,R.role_name,R.created_at,R.description,ROW_NUMBER() OVER() FROM role AS R WHERE R.flag=0";
			// Step 3
			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				role Role = new role();
				Role.setId(rs.getInt(1));
				Role.setRole_name(rs.getString(2));
				Role.setCreate_at(rs.getDate(3));
				Role.setDescription(rs.getString(4));
				Role.setRow(rs.getInt(5));
				roletList.add(Role);
			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roletList;
	}

	public role getRole(String st) {
		role Role = new role();
		// TODO Auto-generated method stub
		try {
			Connection conn = connection_db.getConnection();
			// Step 2
			sql = "SELECT id, role_name FROM role WHERE flag=0 and role_name=? ;";
			// Step 3
			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4
			pst.setString(1, st);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Role.setId(rs.getInt(1));
				Role.setRole_name(rs.getString(2));

				return Role;
			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Role;
	}

	public role getRoleInt(int st) {
		role Role = new role();
		// TODO Auto-generated method stub
		try {
			Connection conn = connection_db.getConnection();
			// Step 2
			sql = "SELECT id, role_name FROM role WHERE flag=0 and id=? ;";
			// Step 3
			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4
			pst.setInt(1, st);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Role.setId(rs.getInt(1));
				Role.setRole_name(rs.getString(2));

				return Role;
			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Role;
	}

	@Override
	public boolean save(role t) {
		boolean check = false;
		// TODO Auto-generated method stub
		try {

			sql = "INSERT INTO hrm.role (role_name,created_at,description) " + "VALUES (?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getRole_name());
			pst.setString(3, t.getDescription());
			pst.setDate(2, t.getCreate_at());

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
	public boolean update(role t) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE hrm.role AS R SET R.role_name= ?,R.created_at=?,R.description=? WHERE R.id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getRole_name());
			pst.setString(3, t.getDescription());
			pst.setDate(2, t.getCreate_at());
			pst.setInt(4, t.getId());
			int rowInsert = pst.executeUpdate();

			if (rowInsert > 0) {
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
	public boolean delete(role t) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE hrm.role AS D SET D.flag= 1 WHERE D.id=?";
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
