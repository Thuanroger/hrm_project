package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.DriverManager;

public class connection_db {

	public connection_db() {
		// TODO Auto-generated constructor stub
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(getdburl(), getusername(), getpassword());
			// System.out.println("Successfully!");
		} catch (Exception ex) {
			System.out.println("Error");
			ex.printStackTrace();
		}
		return conn;
	}

	public static String getserver() {
		return "localhost";
	}

	public static String getport() {
		return "3306";
	}

	public static String getdatabase() {
		return "hrm";
	}

	public static String getusername() {
		return "Thuan";
	}

	public static String getpassword() {
		return "Thuan";
	}

	public static String getdburl() {
		return "jdbc:mysql://" + getserver() + ":" + getport() + "/" + getdatabase();
	}

}
