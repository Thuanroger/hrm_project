package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import com.hrm.model.beans.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class taskDAO implements DAO<task> {
	static Connection conn = connection_db.getConnection();
	static String sql = "";

	public taskDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<task> getAll() {
		// TODO Auto-generated method stub
		ObservableList<task> List = FXCollections.observableArrayList();
		// TODO Auto-generated method stub
		try {
			Connection conn = connection_db.getConnection();
			// Step 2
			sql = "SELECT T.id,T.title,T.description,T.assignee,T.deadline,T.priority,T.`status`,"
					+ "T.created_at,CONCAT(E.last_name,' ',E.middle_name,' ',E.first_name),ROW_NUMBER() OVER()  FROM  hrm.task AS T "
					+ "LEFT JOIN hrm.employee AS E ON E.id=T.assignee WHERE T.flag=0 ORDER  BY T.created_at DESC ";
			// Step 3
			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				task T = new task();
				T.setId(rs.getInt(1));
				T.setTitle(rs.getString(2));
				T.setDescription(rs.getString(3));
				T.setAssignee(rs.getInt(4));
				T.setDeadline(rs.getDate(5));
				T.setPriority(rs.getString(6));
				T.setStatus(rs.getString(7));
				T.setCreate_at(rs.getDate(8));
				T.setNameString(rs.getString(9));
				T.setRow(rs.getInt(10));
				List.add(T);

			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return List;
	}

	@Override
	public boolean save(task t) {
		boolean check = false;
		// TODO Auto-generated method stub
		try {

			sql = "INSERT INTO `hrm`.`task` (`title`, `description`, `assignee`, `deadline`, `priority`, `status`, `created_at`)"
					+ " VALUES (?,?,?,?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getTitle());
			pst.setString(2, t.getDescription());
			pst.setInt(3, t.getAssignee());
			pst.setDate(4, t.getDeadline());
			pst.setString(5, t.getPriority());
			pst.setString(6, t.getStatus());
			pst.setDate(7, t.getCreate_at());

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
	public boolean update(task t) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE `hrm`.`task` SET `title`=?, `description`=?, `assignee`=?, `deadline`=?, `priority`=?, `status`=?,`created_at`=? WHERE  `id`=?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getTitle());
			pst.setString(2, t.getDescription());
			pst.setInt(3, t.getAssignee());
			pst.setDate(4, t.getDeadline());
			pst.setString(5, t.getPriority());
			pst.setString(6, t.getStatus());
			pst.setDate(7, t.getCreate_at());
			pst.setInt(8, t.getId());

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

	public static ArrayList<task> getTaskByDate(int date, int month, int department_id) {
		ArrayList<task> list = new ArrayList<>();

		try {
			Connection conn = connection_db.getConnection();
			sql =  "SELECT task.created_at, task.title, task.`status`\r\n"
					+ "FROM task\r\n"
					+ "INNER JOIN task_department ON task_department.task_id = task.id\r\n"
					+ "INNER JOIN department ON task_department.department_id = department.id\r\n"
					+ "INNER JOIN employee ON employee.id = task.assignee\r\n"
					+ "WHERE DAY(task.created_at) = ? AND MONTH(task.created_at) = ? AND  department.id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, date);
			pst.setInt(2, month);
			pst.setInt(3, department_id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				task task = new task();
				task.setCreate_at(rs.getDate("created_at"));
				task.setStatus(rs.getString("status"));
				task.setTitle(rs.getString("title"));
				list.add(task);
			}
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean delete(task t) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE `hrm`.`task` SET `flag`='1' WHERE  `id`=?;";
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

	public static ObservableList<task> getMonthByTask() {
		// TODO Auto-generated method stub
		ObservableList<task> list = FXCollections.observableArrayList();
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT COUNT(task.title)AS 'title task', task.`status`, MONTH(task.created_at) AS 'month'\r\n"
					+ "FROM task \r\n" + "GROUP BY  MONTH(task.created_at), task.`status`\r\n"
					+ "ORDER BY  MONTH(task.created_at), task.`status`";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				task task = new task();
				task.setQuantity_task(rs.getInt("title task"));
				task.setMonth_by_task(rs.getString("month"));
				task.setStatus(rs.getString("status"));
				list.add(task);
			}
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static ObservableList<task> getMonthByTaskStaff(int id) {
		ObservableList<task> list = FXCollections.observableArrayList();
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT COUNT(task.title)AS 'title task', task.`status`, MONTH(task.created_at) AS 'month' \r\n"
					+ "					FROM task \r\n"
					+ "					WHERE task.assignee=? AND YEAR(task.created_at)=?\r\n"
					+ "					GROUP BY  MONTH(task.created_at), task.`status`";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, LocalDate.now().getYear());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				task task = new task();
				task.setQuantity_task(rs.getInt("title task"));
				task.setMonth_by_task(rs.getString("month"));
				task.setStatus(rs.getString("status"));
				list.add(task);
			}
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
