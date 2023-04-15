package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.text.DefaultEditorKit.PasteAction;

import com.hrm.model.beans.department;
import com.hrm.model.beans.employee;
import com.hrm.model.beans.principal;
import com.mysql.cj.exceptions.RSAException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class employeeDAO implements DAO<employee> {
	static String sql = "";
	static Connection conn = connection_db.getConnection();

	public employeeDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<employee> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(employee t) {
		boolean check = false;
		try {

			sql = "INSERT INTO `hrm`.`employee` (`department_id`, `role_id`, `username`, `password`, `on_leave`, `last_name`, `middle_name`, `first_name`, `telephone`, "
					+ "`email`, `address`, `avatar`, `description`, `dob`, `status`, `hire_date`,`gender`) VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getDepartment_id());
			pst.setInt(2, t.getRole_id());
			pst.setString(3, t.getUsername());
			pst.setString(4, t.getPassword());
			pst.setInt(5, t.getOn_leave());
			pst.setString(6, t.getLast_name());
			pst.setString(7, t.getMiddle_name());
			pst.setString(8, t.getFirst_name());
			pst.setString(9, t.getTelephone());
			pst.setString(10, t.getEmail());
			pst.setString(11, t.getAddress());
			pst.setString(12, t.getAvatar());
			pst.setString(13, t.getDescription());
			pst.setDate(14, t.getDob());
			pst.setInt(15, t.getStatus());
			pst.setDate(16, t.getHire_date());
			pst.setString(17, t.getGender());

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
	public boolean update(employee t) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE `hrm`.`employee` SET `department_id`=?, `role_id`=?,"
					+ " `username`=?, `password`=?, `on_leave`=?, `last_name`=?,"
					+ " `middle_name`=?, `first_name`=?, `telephone`=?, `email`=?, `address`=?, "
					+ "`avatar`=?, `description`=?, `dob`=?, `status`=?, `hire_date`=?,`avatar`=?,`gender`=? WHERE  `id`=?;";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, t.getDepartment_id());
			pst.setInt(2, t.getRole_id());
			pst.setString(3, t.getUsername());
			pst.setString(4, t.getPassword());
			pst.setInt(5, t.getOn_leave());
			pst.setString(6, t.getLast_name());
			pst.setString(7, t.getMiddle_name());
			pst.setString(8, t.getFirst_name());
			pst.setString(9, t.getTelephone());
			pst.setString(10, t.getEmail());
			pst.setString(11, t.getAddress());
			pst.setString(12, t.getAvatar());
			pst.setString(13, t.getDescription());
			pst.setDate(14, t.getDob());
			pst.setInt(15, t.getStatus());
			pst.setDate(16, t.getHire_date());
			pst.setString(17, t.getAvatar());
			pst.setString(18, t.getGender());
			pst.setInt(19, t.getId());
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

	public boolean updateProfile(employee t) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE `hrm`.`employee` SET" + " `username`=?,`last_name`=?,"
					+ " `middle_name`=?, `first_name`=?, `telephone`=?, `email`=?, `address`=? "
					+ ", `description`=?, `dob`=? ,`avatar`=? WHERE  `id`=?;";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setString(1, t.getUsername());
			pst.setString(2, t.getLast_name());
			pst.setString(3, t.getMiddle_name());
			pst.setString(4, t.getFirst_name());
			pst.setString(5, t.getTelephone());
			pst.setString(6, t.getEmail());
			pst.setString(7, t.getAddress());
//					pst.setString(8, t.getAvatar());
			pst.setString(8, t.getDescription());
			pst.setDate(9, t.getDob());
			pst.setString(10, t.getAvatar());
			pst.setInt(11, t.getId());
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

	public boolean updatePass(int id, String st) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {
			sql = "UPDATE `hrm`.`employee` SET  `password`=? WHERE  `id`=?;";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setString(1, st);
			pst.setInt(2, id);
			int checkpass = pst.executeUpdate();

			if (checkpass > 0) {
				check = true;
			}
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;
	}

	public boolean CheckPass(int id, String st) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {
			sql = "SELECT E.password FROM hrm.employee AS E WHERE E.id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				if (rs.getString(1).equals(st)) {
					check = true;
					return true;
				}
			}
//					conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public boolean delete(employee t) {
		// TODO Auto-generated method stub
		return false;
	}

	public employee getEmployeeID(int id) {
		employee EE = new employee();
		// TODO Auto-generated method stub
		try {

			// Step 2
			sql = "SELECT  `id`,  `department_id`,  `role_id`,  `username`,  `password`,  `on_leave`,  `last_name`,  `middle_name`,  `first_name`,"
					+ "  `telephone`,  `email`,  `address`,  `avatar`, `description`,  `dob`,  `status`, "
					+ " `hire_date`,`avatar`,`gender` FROM `hrm`.`employee` WHERE `id`=? AND `flag`=0;";
			// Step 3
			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				EE.setId(rs.getInt(1));
				EE.setDepartment_id(rs.getInt(2));
				EE.setRole_id(rs.getInt(3));
				EE.setUsername(rs.getString(4));
				EE.setPassword(rs.getString(5));
				EE.setOn_leave(rs.getInt(6));
				EE.setLast_name(rs.getString(7));
				EE.setMiddle_name(rs.getString(8));
				EE.setFirst_name(rs.getString(9));
				EE.setTelephone(rs.getString(10));
				EE.setEmail(rs.getString(11));
				EE.setAddress(rs.getString(12));
				EE.setAvatar(rs.getString(13));
				EE.setDescription(rs.getString(14));
				EE.setDob(rs.getDate(15));
				EE.setStatus(rs.getInt(16));
				EE.setHire_date(rs.getDate(17));
				EE.setAvatar(rs.getString(18));
				EE.setGender(rs.getString(19));

			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return EE;
	}

	public ObservableList<employee> getHireDate() {
		ObservableList<employee> list = FXCollections.observableArrayList();
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT MONTH(hire_date) AS \"Month\", COUNT(id) AS \"Quality employee\"\r\n" + "FROM employee\r\n"
					+ "WHERE STATUS = 0 AND YEAR(hire_date) = \"2023\"\r\n" + "GROUP BY MONTH(hire_date)\r\n"
					+ "ORDER BY MONTH(hire_date);";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				employee employee = new employee();
				employee.setHire_month(rs.getString("Month"));
				employee.setQuantity_employee(rs.getInt("Quality employee"));
				list.add(employee);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ObservableList<employee> getTerDate() {
		ObservableList<employee> list = FXCollections.observableArrayList();
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT MONTH(hire_date) AS \"Month\", COUNT(id) AS \"Quality employee\"\r\n" + "FROM employee\r\n"
					+ "WHERE STATUS = 1 AND YEAR(hire_date) = \"2023\"\r\n" + "GROUP BY MONTH(hire_date)\r\n"
					+ "ORDER BY MONTH(hire_date);";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				employee employee = new employee();
				employee.setTermination_month(rs.getString("Month"));
				employee.setQuantity_employee(rs.getInt("Quality employee"));
				list.add(employee);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static ObservableList<employee> getData() {
		ObservableList<employee> list = FXCollections.observableArrayList();
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT COUNT(`E`.department_id)AS 'Quatity employee', `D`.department_name\r\n"
					+ "FROM employee `E`\r\n" + "INNER JOIN department `D` ON `E`.department_id = `D`.id\r\n"
					+ "GROUP BY department_id\r\n" + "ORDER BY department_id;";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				employee employee = new employee();
				employee.setDepartment_id(rs.getInt("Quatity employee"));

				department deparment = new department();
				deparment.setDepartment_name(rs.getString("department_name"));
				employee.setDepartment(deparment);

				list.add(employee);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static String getTotal() {
		String total = "";
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT COUNT(id) AS 'Total'\r\n" + "FROM employee\r\n" + "WHERE flag = 0 AND `status` = 0";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				total = rs.getString("Total");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	public static String getAvgDob() {
		String avg = "";
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT AVG(YEAR(CURDATE())-YEAR(dob)) AS 'avg'\r\n" + "FROM employee\r\n";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				avg = rs.getString("avg");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return avg;
	}

	public static String getTotalMale() {
		String gender = "";
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT COUNT(gender)  AS 'total'\r\n" + "FROM employee\r\n" + "WHERE gender = 'male'";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				gender = rs.getString("total");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gender;
	}

	public static String getTotalFemale() {
		String gender = "";
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT COUNT(gender)  AS 'total'\r\n" + "FROM employee\r\n" + "WHERE gender = 'female'";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				gender = rs.getString("total");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gender;
	}

}
