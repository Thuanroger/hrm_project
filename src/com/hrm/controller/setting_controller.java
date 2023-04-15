package com.hrm.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class setting_controller implements Initializable {

	public setting_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// Role.....
		FxmlLoader oblectFxmlLoader = new FxmlLoader();
		AnchorPane viewAnchorPane = oblectFxmlLoader.getPane("role");
		setting_pane2.setCenter(viewAnchorPane);
		role_btn.setStyle("-fx-background-color:#1976D2;");
		rolemodule_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389)");
		module_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389)");

	}

	@FXML
	private Button role_btn;

	@FXML
	private Button module_btn;

	@FXML
	private Button rolemodule_btn;

	@FXML
	private BorderPane setting_pane2;

	@FXML
	void ModuleView(ActionEvent event) {
		FxmlLoader oblectFxmlLoader = new FxmlLoader();
		AnchorPane viewAnchorPane = oblectFxmlLoader.getPane("module");
		setting_pane2.setCenter(viewAnchorPane);
		module_btn.setStyle("-fx-background-color:#1976D2;");
		role_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389)");
		rolemodule_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389)");
	}

	@FXML
	void RoleModuleView(ActionEvent event) {
		FxmlLoader oblectFxmlLoader = new FxmlLoader();
		AnchorPane viewAnchorPane = oblectFxmlLoader.getPane("role-module");
		setting_pane2.setCenter(viewAnchorPane);
		rolemodule_btn.setStyle("-fx-background-color:#1976D2;");
		role_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389)");
		module_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389)");
	}

	@FXML
	void RoleView(ActionEvent event) {
		FxmlLoader oblectFxmlLoader = new FxmlLoader();
		AnchorPane viewAnchorPane = oblectFxmlLoader.getPane("role");
		setting_pane2.setCenter(viewAnchorPane);
		role_btn.setStyle("-fx-background-color:#1976D2;");
		rolemodule_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389)");
		module_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389)");

	}

}
