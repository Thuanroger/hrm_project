package com.hrm.assets.lib;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import org.apache.tools.ant.property.NullReturn;

import com.hrm.model.business_objects.bo_employee;

public class check {

	public check() {
		// TODO Auto-generated constructor stub
	}

	final static String DATE_FORMAT = "yyyy-MM-dd";

	public static boolean checknumber(String tr) {
		Pattern pattern = Pattern.compile(".*[^0-9].*");
		return !pattern.matcher(tr).matches();
	}

	public static boolean checkstring(String tr) {
		Pattern ptr = Pattern.compile("^[a-zA-Z][\\']?[a-zA-Z\\s]+$");
		return ptr.matcher(tr).matches();

	}

	public static boolean checkemail(String tr) {
		Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}+$");
		return pattern.matcher(tr).matches();

	}

	public static boolean checkusername(String tr) {
		Pattern pattern = Pattern.compile("[a-z0-9_-]{3,16}$");
		return pattern.matcher(tr).matches();
	}

	public static boolean checkpassword(String tr) {
		Pattern pattern = Pattern
				.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}+$");
		return pattern.matcher(tr).matches();
	}

	public static boolean isDateValid(String date) {
		try {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static boolean checkFnumber(String tr) {
		return (checknumber(tr) && (tr != null && !tr.equals(""))) ? true : false;

	}

	public static boolean checkFgmail(String tr) {
		return (checkemail(tr) && (tr != null || !tr.equals(""))) ? true : false;

	}

	public static boolean checkFpass(String tr) {
		return (checkpassword(tr) && (tr != null || !tr.equals(""))) ? true : false;

	}

	public static boolean checkFtring(String tr) {
		return (checkstring(tr) && (tr != null || !tr.equals(""))) ? true : false;

	}

	public static boolean checkFuser(String tr) {
		return (checkusername(tr) && (tr != null || !tr.equals(""))) ? true : false;

	}

	public static boolean checknull(String tr) {
		return (tr != null) ? true : false;

	}

	public static boolean checkdate(String tr) {
		return (isDateValid(tr) && (tr != null || !tr.equals(""))) ? true : false;

	}

	public static String ErrorUser(String tr) {
		String check = "";
		if (tr == null || tr.equals("")) {
			check = "Required";
		} else if (checkusername(tr) == false) {
			check = "Username must be between\n3 and 16 characters";
		}
		return check;
	}

	public static String ErrorEmail(String tr) {
		String check = "";
		if (tr == null || tr.equals("")) {
			check = "Required";
		} else if (checkemail(tr) == false) {
			check = "Enter a valid email\nin the joe@abc.com format.";

		}
		return check;
	}

	public static String ErrorNumber(String tr) {
		String check = "";
		if (tr == null || tr.equals("")) {
			check = "Required";
		} else if (checknumber(tr) == false) {
			check = "Invalid number format";
		}
		return check;
	}

	public static String ErrorString(String tr) {
		String check = "";
		if (tr == null || tr.equals("")) {
			check = "Required";
		} else if (checkstring(tr) == false) {
			check = "Invalid text format";
		}

		return check;
	}

	public static String Errorbox(String tr) {
		String check = "";
		if (tr == null || tr.equals("")) {
			check = "Required";
		}
		return check;
	}

	public static String ErrorDate(String tr) {
		String check = "";

		if (isDateValid(tr) == false) {
			check = "Invalid (yyyy-MM-dd)";
		}

		return check;
	}

	public static String ErrorPass(String tr) {
		String check = "";
		if (tr.equals("") || tr.equals(null)) {
			check = "Required";
		} else if (checkpassword(tr) == false) {
			check = "Please enter the password again";
		}
		return check;
	}

	public static String ErrorPassid(int id, String tr) {
		String check = "";
		bo_employee dataBo = new bo_employee();
		if (tr.equals("") || tr.equals(null)) {
			check = "Required";
		} else if (dataBo.checkPass(id, tr) == false) {
			check = "Not found.";
		}
		return check;
	}

}
