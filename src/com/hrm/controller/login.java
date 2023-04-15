package com.hrm.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.hrm.assets.lib.VisiblePasswordFieldSkin;
import com.hrm.model.usersession;
import com.hrm.model.business_objects.bo_employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class login implements Initializable {

	@FXML
	public TextField tfUsername;
	@FXML
	public BorderPane borderPaneLogin;

	@FXML
	public PasswordField tfPassword;
	@FXML
	public TextField pass_field;

	@FXML
	public Hyperlink btnForgotPassword;

	@FXML
	public Button btnLogin;

	@FXML
	public Label messageLogin;
	@FXML
	private CheckBox show_box;
	private Stage stage;
	private Scene scene;
	private Pane homePage;

	public login() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		tfPassword.setSkin(new VisiblePasswordFieldSkin(tfPassword));

	}

	public void btnLoginOnAction(ActionEvent event) throws IOException, SQLException {
		String username = tfUsername.getText();
		String password = tfPassword.getText();

		if (username.isBlank() == true || password.isBlank() == true) {
			messageLogin.setText("Please enter username or password");
		} else if (bo_employee.login(username, password)) {
			homePage = (Pane) FXMLLoader.load(getClass().getResource("../view/home.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(homePage);
			scene.getStylesheets().add(getClass().getResource("../assets/css/home.css").toExternalForm());
			stage.setScene(scene);
			stage.show();

		} else {
			messageLogin.setText("Invalid info. Please try again");
		}
	}

	public void btnForgotPasswordOnAction(ActionEvent event) throws IOException {
		homePage = (Pane) FXMLLoader.load(getClass().getResource("../view/forgotpassword.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(homePage);
//		scene.getStylesheets().add(getClass().getResource("../assets/css/home.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void showPassword(ActionEvent event) {
//    	 if (show_box.isSelected()) {
//    	        pass_field.setText(tfPassword.getText());
//    	        pass_field.setVisible(true);
//    	        tfPassword.setVisible(false);
//    	        return;
//    	    }
//    	    tfPassword.setText(pass_field.getText());
//    	    tfPassword.setVisible(true);
//    	    pass_field.setVisible(false);
	}
}