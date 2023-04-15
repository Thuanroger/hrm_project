package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hrm.model.beans.department;
import com.hrm.model.beans.employee;
import com.hrm.model.beans.employee_search;
import com.hrm.model.beans.principal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class principalDAO implements DAO<principal> {
	private static Connection conn = connection_db.getConnection();
	static String sql = "";

	public principalDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<principal> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(principal t) {
		// TODO Auto-generated method stub
		boolean check = false;
		// TODO Auto-generated method stub
		try {

			sql = "INSERT INTO hrm.`principal` (`employee_id`,`type`,`date_principal`,`value_money`,`created_at`,`description`) VALUES"
					+ "(?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getEmployee_id());
			pst.setString(2, t.getType());
			pst.setDate(3, t.getDate_principal());
			pst.setInt(4, t.getValue_money());
			pst.setDate(5, t.getCreated_at());
			pst.setString(6, t.getDescription());

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
	public boolean update(principal t) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE principal AS P SET P.employee_id=?, P.value_money=?,P.`type`=?,P.date_principal=?,P.created_at=?,P.description=? WHERE P.id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getEmployee_id());
			pst.setInt(2, t.getValue_money());
			pst.setString(3, t.getType());
			pst.setDate(4, t.getDate_principal());
			pst.setDate(5, t.getCreated_at());
			pst.setString(6, t.getDescription());
			pst.setInt(7, t.getId());

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
	public boolean delete(principal t) {
		// TODO Auto-generated method stub
		return false;
	}

	public ObservableList<principal> getPrincipals() {
		ObservableList<principal> Principal_list = FXCollections.observableArrayList();
		// TODO Auto-generated method stub
		try {

			// Step 2
			sql = "SELECT P.id,D.department_name,CONCAT(E.last_name,' ',E.middle_name,' ',E.first_name),CONCAT(P.value_money,'$'),P.`type`,P.date_principal,P.created_at,P.description, P.employee_id,ROW_NUMBER() OVER() FROM hrm.principal AS P \r\n"
					+ "				LEFT JOIN hrm.employee AS E ON P.employee_id=E.id \r\n"
					+ "					LEFT JOIN hrm.department AS D ON D.id=E.department_id  WHERE P.flag=0 ORDER BY P.created_at DESC ";
			// Step 3
			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				principal Prin = new principal();

				Prin.setId(rs.getInt(1));
				Prin.setDepartment_name(rs.getString(2));
				Prin.setEmployee_name(rs.getString(3));

				Prin.setGetMoneyString(rs.getString(4));
				Prin.setType(rs.getString(5));
				Prin.setDate_principal(rs.getDate(6));
				Prin.setCreated_at(rs.getDate(7));
				Prin.setEmployee_id(rs.getInt(9));
				Prin.setDescription(rs.getString(8));
				Prin.setRow(rs.getInt(10));
				Principal_list.add(Prin);

			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Principal_list;
	}

	public ObservableList<employee_search> getSearchs() {
		ObservableList<employee_search> em_listEmployees = FXCollections.observableArrayList();
		try {

			// Step 2
			sql = "SELECT E.avatar, CONCAT(E.last_name,' ', E.middle_name,' ',E.first_name),D.department_name,E.dob,E.id,ROW_NUMBER() OVER() FROM  hrm.employee AS E"
					+ " INNER JOIN hrm.department AS D ON D.id=E.department_id ORDER BY E.id DESC; ";
			// Step 3
			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				employee_search empsSearch = new employee_search();
				empsSearch.setAvatar(rs.getString(1));
				empsSearch.setFullname(rs.getString(2));
				empsSearch.setDepartment(rs.getString(3));
				empsSearch.setDOB(rs.getDate(4));
				empsSearch.setId(rs.getInt(5));
				empsSearch.setRow(rs.getInt(6));

				em_listEmployees.add(empsSearch);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return em_listEmployees;
	}

}
