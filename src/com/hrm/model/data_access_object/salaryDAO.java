package com.hrm.model.data_access_object;

import java.io.ObjectInputFilter.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;

import com.hrm.model.beans.salary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class salaryDAO implements DAO<salary> {
	static String sql = "";
	static Connection conn = connection_db.getConnection();

	public salaryDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<salary> getAll() {
		// TODO Auto-generated method stub
		ObservableList<salary> List = FXCollections.observableArrayList();
		// TODO Auto-generated method stub
		try {
			Connection conn = connection_db.getConnection();
			// Step 2
			sql = "SELECT S.id,S.employee_id,CONCAT(E.last_name,' ',E.middle_name,' ',E.first_name),CONCAT(S.value_money,'$'),S.time_to_pay,S.who_pay,CONCAT(S.value_money_reward,'$'),S.working_day,S.created_at,S.description,S.flag,S.`status`,ROW_NUMBER() OVER() FROM hrm.salary AS S "
					+ "INNER JOIN hrm.employee AS E ON E.id=S.employee_id WHERE S.flag=0 ORDER  BY time_to_pay DESC"; // Step
																														// 3
			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				salary sal = new salary();
				sal.setId(rs.getInt(1));
				sal.setEmployee_id(rs.getInt(2));
				sal.setEmployee(rs.getString(3));
				sal.setMoneyString(rs.getString(4));
//				sal.setValue_money(rs.getInt(4));
				sal.setTime_to_pay(rs.getDate(5));
				sal.setWho_pay(rs.getString(6));
				sal.setRewardString(rs.getString(7));
				;
//				sal.setValue_money_reward(rs.getInt(7));
				sal.setWorking_day(rs.getInt(8));
				sal.setCreated_at(rs.getDate(9));
				sal.setDescription(rs.getString(10));
				sal.setFlag(rs.getInt(11));
				sal.setStatus(rs.getString(12));
				sal.setRow(rs.getInt(13));
				List.add(sal);
			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return List;
	}

	public ObservableList<salary> getAllDepartment(int id) {
		// TODO Auto-generated method stub
		ObservableList<salary> List = FXCollections.observableArrayList();
		// TODO Auto-generated method stub
		try {
			Connection conn = connection_db.getConnection();
			// Step 2
			sql = "SELECT S.id,S.employee_id,CONCAT(E.last_name,' ',E.middle_name,' ',E.first_name),CONCAT(S.value_money,'$'),S.time_to_pay,S.who_pay,CONCAT(S.value_money_reward,'$'),S.working_day,S.created_at,S.description,S.flag,S.`status`,ROW_NUMBER() OVER() FROM hrm.salary AS S \r\n"
					+ "					INNER JOIN hrm.employee AS E ON E.id=S.employee_id  "
					+ "INNER JOIN hrm.department AS D ON E.department_id=D.id WHERE S.flag=0 AND D.id=?  ORDER  BY time_to_pay DESC"; // Step
																																		// 3
			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				salary sal = new salary();
				sal.setId(rs.getInt(1));
				sal.setEmployee_id(rs.getInt(2));
				sal.setEmployee(rs.getString(3));
				sal.setMoneyString(rs.getString(4));
//				sal.setValue_money(rs.getInt(4));
				sal.setTime_to_pay(rs.getDate(5));
				sal.setWho_pay(rs.getString(6));
				sal.setRewardString(rs.getString(7));
				;
//				sal.setValue_money_reward(rs.getInt(7));
				sal.setWorking_day(rs.getInt(8));
				sal.setCreated_at(rs.getDate(9));
				sal.setDescription(rs.getString(10));
				sal.setFlag(rs.getInt(11));
				sal.setStatus(rs.getString(12));
				sal.setRow(rs.getInt(13));
				List.add(sal);
			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return List;
	}

	public ObservableList<salary> getSalaryid(int id, String year) {
		// TODO Auto-generated method stub
		ObservableList<salary> List = FXCollections.observableArrayList();
		// TODO Auto-generated method stub
		try {
			Connection conn = connection_db.getConnection();
			// Step 2
			sql = "SELECT S.id,S.employee_id,CONCAT(E.last_name,' ',E.middle_name,' ',E.first_name),CONCAT(S.value_money,'$'),S.time_to_pay,S.who_pay,CONCAT(S.value_money_reward,'$'),S.working_day,S.created_at,S.description,S.flag,YEAR(S.time_to_pay),S.`status`,E.avatar FROM  hrm.salary AS S \r\n"
					+ "					INNER JOIN hrm.employee AS E ON E.id=S.employee_id    JOIN hrm.department AS D ON E.department_id=D.id WHERE S.flag=0 AND S.employee_id=? AND YEAR(S.time_to_pay) = ?   ORDER  BY time_to_pay DESC"; // Step
			// 3
			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4
			pst.setInt(1, id);
			pst.setString(2, year);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				salary sal = new salary();
				sal.setId(rs.getInt(1));
				sal.setEmployee_id(rs.getInt(2));
				sal.setEmployee(rs.getString(3));
				sal.setMoneyString(rs.getString(4));
//				sal.setValue_money(rs.getInt(4));
				sal.setTime_to_pay(rs.getDate(5));
				sal.setWho_pay(rs.getString(6));
				sal.setRewardString(rs.getString(7));
				;
//				sal.setValue_money_reward(rs.getInt(7));
				sal.setWorking_day(rs.getInt(8));
				sal.setCreated_at(rs.getDate(9));
				sal.setDescription(rs.getString(10));
				sal.setFlag(rs.getInt(11));
				sal.setYear(rs.getString(12));
				sal.setStatus(rs.getString(13));
				sal.setAvatar(rs.getString(14));
				List.add(sal);
			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return List;
	}

	public ObservableList<String> getyear() {
		// TODO Auto-generated method stub
		ObservableList<String> List = FXCollections.observableArrayList();
		// TODO Auto-generated method stub
		try {
			Connection conn = connection_db.getConnection();
			// Step 2
			sql = "SELECT year(salary.time_to_pay)  \r\n" + "FROM salary GROUP BY Year(salary.time_to_pay)";
			// Step 3

			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				List.add(rs.getString(1));
			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return List;
	}

	public String getmoney(int id, String year) {
		String moneyString = "";
		try {
			Connection conn = connection_db.getConnection();
			// Step 2
			sql = "SELECT CONCAT(SUM(value_money),'$') \r\n" + "FROM salary\r\n"
					+ "INNER JOIN employee ON employee.id = salary.employee_id\r\n"
					+ "WHERE salary.employee_id = ? AND year(salary.time_to_pay) = ? \r\n"
					+ "GROUP BY salary.employee_id"; //
														// 3
			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4
			pst.setInt(1, id);
			pst.setString(2, year);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				moneyString = rs.getString(1);
			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return moneyString;
	}

	public static ObservableList<salary> getData() {
		ObservableList<salary> list = FXCollections.observableArrayList();
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT (SUM(value_money) + SUM(value_money_reward)) AS 'Money', MONTH(created_at) AS 'Month'\r\n"
					+ "FROM salary\r\n" + "WHERE YEAR(created_at) =\"2023\"\r\n" + "GROUP BY MONTH(created_at)\r\n"
					+ "ORDER BY  MONTH(created_at)";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				salary salary = new salary();
				salary.setTotal_salary(rs.getInt("Money"));
				salary.setMonth_to_pay(rs.getString("Month"));
				list.add(salary);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean save(salary t) {
		boolean check = false;
		// TODO Auto-generated method stub
		try {

			sql = "INSERT INTO `hrm`.`salary` (`employee_id`, `value_money`, `time_to_pay`, `value_money_reward`, `working_day`, `created_at`,`who_pay`,`description`,`status`) VALUES (?, ?, ?, ?, ?, ?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getEmployee_id());
			pst.setInt(2, t.getValue_money());
			pst.setDate(3, t.getTime_to_pay());
			pst.setInt(4, t.getValue_money_reward());
			pst.setInt(5, t.getWorking_day());
			pst.setDate(6, t.getCreated_at());
			pst.setString(7, t.getWho_pay());
			pst.setString(8, t.getDescription());
			pst.setString(9, t.getStatus());
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
	public boolean update(salary t) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE `hrm`.`salary` SET `employee_id`=?, `value_money`=?, `value_money_reward`=?,`time_to_pay`=?, `working_day`=?, `created_at`=?,`description`=?,`who_pay`=? WHERE  `id`=?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getEmployee_id());
			pst.setInt(2, t.getValue_money());
			pst.setDate(4, t.getTime_to_pay());
			pst.setInt(3, t.getValue_money_reward());
			pst.setInt(5, t.getWorking_day());
			pst.setDate(6, t.getCreated_at());
			pst.setString(8, t.getWho_pay());
			pst.setString(7, t.getDescription());
			pst.setInt(9, t.getId());
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
	public boolean delete(salary t) {

		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE hrm.salary AS D SET D.flag=1 WHERE D.id=?";
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
