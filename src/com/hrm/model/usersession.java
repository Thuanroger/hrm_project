package com.hrm.model;

import java.util.HashSet;
import java.util.Set;

public final class usersession {

	private static usersession instance;
	private static int id;
	private static String userName;
	private static int role_id;
	private static int department_id;

	private usersession(int id, String userName, int role_id, int department_id) {
		this.userName = userName;
		this.id = id;
		this.role_id = role_id;
		this.department_id = department_id;
	}

	public static usersession getInstace(int id, String userName, int role_id, int department_id) {
		if (instance == null) {
			instance = new usersession(id, userName, role_id, department_id);
		}
		return instance;
	}

	public String getUserName() {
		return userName;
	}

	public static int getId() {
		return id;
	}

	public static int getRole_id() {
		return role_id;
	}

	public static int getDepartment_id() {
		return department_id;
	}

	public static void cleanUserSession() {
		instance = null;// or null
//        privileges = new HashSet<>();// or null
	}

	@Override
	public String toString() {
		return "UserSession{" + "userName='" + userName + '\'' + ", privileges=" + '}';
	}
}