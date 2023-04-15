package com.hrm.model.business_objects;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import com.hrm.model.usersession;
import com.hrm.model.beans.employee;
import com.hrm.model.beans.position;
import com.hrm.model.data_access_object.DAO;
import com.hrm.model.data_access_object.connection_db;
import com.hrm.model.data_access_object.crud;
import com.hrm.model.data_access_object.employeeDAO;
import com.hrm.model.data_access_object.show_data;

import javafx.collections.ObservableList;

public class bo_employee implements DAO<employee> {
	private static Connection conn = connection_db.getConnection();
	private employeeDAO datEmployee = new employeeDAO();
	static crud<employee> crudEmployee = new crud<employee>(conn) {
	};

	public bo_employee() {
		// TODO Auto-generated constructor stub
	}

	public static boolean login(String username, String password) throws SQLException {

		crud<employee> crudEmployee = new crud<employee>(conn) {
		};

		ObservableList<employee> listEmployee = crudEmployee.getLogin("employee");

		boolean check = false;
		for (employee employee : listEmployee) {
			if (username.equals(employee.getUsername()) && password.equals(employee.getPassword())) {
				usersession.getInstace(employee.getId(), employee.getUsername(), employee.getRole_id(),
						employee.getDepartment_id());

//				System.out.println(employee.getUsername() + " " + employee.getPassword());
				check = true;
				break;
			}

		}
//		System.out.print(check);
		return check;
	}

	public static ArrayList<employee> getProfile(int idUser) throws SQLException {

		show_data<employee> showProfile = new show_data<>(conn);

		ArrayList<employee> listData = showProfile.getProfile(idUser);

		for (employee employee : listData) {
			System.out.println(employee.getFirst_name());
		}

		return listData;

	}

	public static ArrayList<employee> getListEmployee() throws SQLException {
		show_data<employee> showList = new show_data<>(conn);
		ArrayList<employee> listData = showList.getAllEmployee();

		return listData;
	}

	@Override
	public boolean save(employee t) {

		return datEmployee.save(t);
	}

	@Override
	public boolean update(employee t) {
		// TODO Auto-generated method stub
		return datEmployee.update(t);
	}

	@Override
	public boolean delete(employee t) {
		// TODO Auto-generated method stub
		boolean check = false;
		crud<position> depCrud = new crud<position>(conn) {
		};
		try {
			check = depCrud.Delete("employee", t.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;
	}

	public boolean checkPass(int id, String st) {
		return datEmployee.CheckPass(id, st);
	}

	public boolean updatePass(int id, String st) {
		return datEmployee.updatePass(id, st);
	}

	public boolean updateProfile(employee t) {
		return datEmployee.updateProfile(t);
	}

	@Override
	public ObservableList<employee> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public employee getEmployeeID(int id) {
		return datEmployee.getEmployeeID(id);
	}
}