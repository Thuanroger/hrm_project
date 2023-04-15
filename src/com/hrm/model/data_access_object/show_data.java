package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.hrm.model.beans.department;
import com.hrm.model.beans.employee;
import com.hrm.model.beans.position;
import com.hrm.model.beans.principal;
import com.hrm.model.beans.salary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class show_data<T> {
	private Connection connection;

	public show_data(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<T> getProfile(int idUser) throws SQLException {

		ArrayList<T> result = new ArrayList<>();
		String query = "SELECT employee.id,employee.gender, employee.last_name,employee.middle_name,employee.first_name,employee.username, employee.dob, employee.email, employee.telephone, employee.address, employee.on_leave, employee.description,employee.`status`, employee.hire_date,\r\n"
				+ "					 department.department_name,	 all_position_name.position_name,\r\n"
				+ "						 salary.value_money,employee.`avatar`,\r\n"
				+ "						 value_money_principal.value_money_update AS 'principal',ROW_NUMBER() OVER() AS 'row' FROM   employee \r\n"
				+ "				LEFT JOIN position_employee `N`  ON `N`.employee_id = employee.id\r\n"
				+ "				LEFT JOIN department  ON department.id = employee.department_id\r\n"
				+ "				LEFT JOIN all_position_name ON all_position_name.employee_id = employee.id\r\n"
				+ "				LEFT JOIN salary  ON salary.employee_id  = employee.id\r\n"
				+ "				LEFT JOIN value_money_principal ON value_money_principal.employee_id = employee.id\r\n"
				+ "				WHERE employee.id = ?\r\n" + "				GROUP BY employee.id;";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, idUser);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			T item = parseResultSet(rs);
			result.add(item);
		}
		return result;
	}

	public ArrayList<T> getAllEmployee() throws SQLException {
		ArrayList<T> result = new ArrayList<>();
		String query = "SELECT employee.id ,employee.gender,employee.last_name,employee.middle_name,employee.first_name,employee.username ,employee.dob, employee.email, employee.telephone, employee.address, employee.on_leave, employee.description,employee.`status`, employee.hire_date,\r\n"
				+ "						 department.department_name,		 all_position_name.position_name,\r\n"
				+ "						 salary.value_money,employee.`status`,employee.`avatar`,\r\n"
				+ "						 value_money_principal.value_money_update AS 'principal',ROW_NUMBER() OVER() AS 'row' FROM employee \r\n"
				+ "			LEFT JOIN  position_employee `N` ON `N`.employee_id = employee.id\r\n"
				+ "			LEFT JOIN department  ON department.id = employee.department_id\r\n"
				+ "			LEFT JOIN all_position_name ON all_position_name.employee_id = employee.id\r\n"
				+ "			LEFT JOIN salary  ON salary.employee_id  = employee.id\r\n"
				+ "			LEFT JOIN value_money_principal ON value_money_principal.employee_id = employee.id\r\n"
				+ "			WHERE  employee.flag=0 \r\n"
				+ "			GROUP BY employee.id ORDER BY employee.id DESC ;;";

		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			T item = parseResultSet(rs);
			result.add(item);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	protected T parseResultSet(ResultSet rs) throws SQLException {
		T item = null;

		if (rs.getMetaData().getTableName(1).equalsIgnoreCase("employee")) {
			employee employee = new employee();
			employee.setId(rs.getInt("id"));
			employee.setOn_leave(rs.getInt("on_leave"));
			employee.setRow(rs.getInt("row"));
			employee.setGender(rs.getString("gender"));
			employee.setLast_name(rs.getString("last_name"));
			employee.setMiddle_name(rs.getString("middle_name"));
			employee.setFirst_name(rs.getString("first_name"));
			employee.setEmail(rs.getString("email"));
			employee.setAddress(rs.getString("address"));
			employee.setTelephone(rs.getString("telephone"));
			employee.setAvatar(rs.getString("avatar"));
			employee.setDescription(rs.getString("description"));
			employee.setDob(rs.getDate("dob"));
			employee.setHire_date(rs.getDate("hire_date"));
			employee.setStatus(rs.getInt("status"));
			employee.setUsername(rs.getString("username"));
			department department = new department();
			department.setDepartment_name(rs.getString("department_name"));
			employee.setDepartment(department);
			position position = new position();
			position.setPosition_name(rs.getString("position_name"));
			employee.setPosition(position);
			salary salary = new salary();
			salary.setValue_money(rs.getInt("value_money"));
			employee.setSalary(salary);

			principal principal = new principal();
			principal.setValue_money(rs.getInt("principal"));
			employee.setPrincipal(principal);

			item = (T) employee;
		} else if (rs.getMetaData().getTableName(1).equalsIgnoreCase("salary")) {
			salary salary = new salary();
			salary.setId(rs.getInt("id"));
			salary.setValue_money(rs.getInt("value_money"));
			salary.setTime_to_pay(rs.getDate("time_to_pay"));
			salary.setWho_pay(rs.getString("who_pay"));
			salary.setValue_money_reward(rs.getInt("value_money_reward"));
			salary.setWorking_day(rs.getInt("working_day"));
			salary.setCreated_at(rs.getDate("created_at"));
//			salary.setFlag(rs.getInt("flag"));

			employee employee = new employee();
			employee.setLast_name(rs.getString("last_name"));
			employee.setMiddle_name(rs.getString("middle_name"));
			employee.setFirst_name(rs.getString("first_name"));

			item = (T) salary;
		}

		return item;
	}

}