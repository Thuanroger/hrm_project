package com.hrm.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.hrm.assets.lib.VisiblePasswordFieldSkin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;

public class createPassword_controller implements Initializable {

	public createPassword_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		oldpassword.setSkin(new VisiblePasswordFieldSkin(oldpassword));
		newpassword.setSkin(new VisiblePasswordFieldSkin(newpassword));
		confirm.setSkin(new VisiblePasswordFieldSkin(confirm));
	}

	@FXML
	private PasswordField oldpassword;

	@FXML
	private PasswordField confirm;

	@FXML
	private PasswordField newpassword;

	@FXML
	private Button change_btn;

	@FXML
	private Hyperlink login_btn;

	@FXML
	void ChangePassword(ActionEvent event) {

	}

	@FXML
	void Login(ActionEvent event) {

	}

}
