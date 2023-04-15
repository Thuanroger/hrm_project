package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hrm.model.beans.employee;
import com.hrm.model.beans.employee_search;
import com.hrm.model.beans.position;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class positionDAO implements DAO<position> {

	public positionDAO() {
		// TODO Auto-generated constructor stub
	}

	static String sql = "";
	static Connection conn = connection_db.getConnection();

	@Override
	public ObservableList<position> getAll() {

		ObservableList<position> em_listEmployees = FXCollections.observableArrayList();
		try {

			// Step 2
			sql = "SELECT E.id,E.position_name,E.created_at,E.who_create,E.description,ROW_NUMBER() OVER() FROM  hrm.`position` AS E WHERE E.flag=0\r\n"
					+ "				 ORDER BY E.id DESC;  ";
			// Step 3
			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				position empsSearch = new position();
				empsSearch.setId(rs.getInt(1));
				empsSearch.setPosition_name(rs.getString(2));
				empsSearch.setCreated_at(rs.getDate(3));
				empsSearch.setWho_create(rs.getString(4));
				empsSearch.setDescription(rs.getString(5));
				empsSearch.setRow(rs.getInt(6));

				em_listEmployees.add(empsSearch);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return em_listEmployees;
	}

	public ObservableList<position> getDepartment(int id) {
		return null;
	}

	@Override
	public boolean save(position t) {

		boolean check = false;
		try {

			sql = "INSERT INTO position (`position_name`,`who_create`,`created_at`,`description`) VALUES (?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getPosition_name());
			pst.setString(2, t.getWho_create());
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
	public boolean update(position t) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE hrm.position AS P SET P.position_name=?,P.description=?,P.created_at=?,P.who_create=? WHERE P.id= ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getPosition_name());
			pst.setString(2, t.getDescription());
			pst.setDate(3, t.getCreated_at());
			pst.setString(4, t.getWho_create());
			pst.setInt(5, t.getId());
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
	public boolean delete(position t) {

		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE hrm.position AS D SET D.flag=1 WHERE D.id=?";
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

}
