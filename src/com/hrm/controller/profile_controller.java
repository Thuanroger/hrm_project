package com.hrm.controller;

import java.io.IOException;
import java.lang.ref.Cleaner.Cleanable;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.hrm.model.usersession;
import com.hrm.model.beans.employee;
import com.hrm.model.business_objects.bo_employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class profile_controller implements Initializable {

	private boolean update;

	public profile_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lean();
		getprofile();

	}

	private static ArrayList<employee> getProfile = null;
	@FXML
	BorderPane image_pane;
	@FXML
	private MenuButton setting;

	@FXML
	private MenuItem password_item;

	@FXML
	private MenuItem edit_item;

	@FXML
	private TextArea descrip_text;

	@FXML
	private Text department_text;

	@FXML
	private Text hire_date_text;

	@FXML
	private TextArea position_text;

	@FXML
	private Text principal_text;

	@FXML
	private Text salary_text;

	@FXML
	private Text onleave_text;

	@FXML
	private ImageView avatar;

	@FXML
	private Text DOB_text;

	@FXML
	private TextArea address_text;

	@FXML
	private Text gmail_text;

	@FXML
	private Text phone_text;

	@FXML
	private Text fullname_txt;

	@FXML
	private Button refresh_btn;

	@FXML
	void EditPassword(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getResource("../view/createNewPassword.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML
	void EditProfile(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getResource("../view/edit_user.fxml"));
		Stage stage = new Stage();
		Image icon = new Image("./com/hrm/assets/logo/logo.jpg");
		stage.getIcons().add(icon);
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML
	void Refresh(ActionEvent event) {
		lean();
		getprofile();
	}

	private void getprofile() {
		DateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		for (employee employee : getProfile) {

			// demo addimage
			if (employee.getAvatar() == null || employee.getAvatar().equals("")) {
				ImageView imgImageView1 = new ImageView();

				// demo addimage
				Image image1 = new Image("./com/hrm/assets/avatar/avatarnul.png");
				imgImageView1.setImage(image1);
				imgImageView1.setFitWidth(150);
				imgImageView1.setFitHeight(150);
				imgImageView1.scaleXProperty();
				imgImageView1.scaleYProperty();
				imgImageView1.setSmooth(true);
				imgImageView1.setCache(true);
				image_pane.setCenter(imgImageView1);
			} else {
				ImageView imgImageView1 = new ImageView();

				// demo addimage
				Image image1 = new Image(employee.getAvatar());
				imgImageView1.setImage(image1);
				imgImageView1.setFitWidth(150);
				imgImageView1.setFitHeight(150);
				imgImageView1.scaleXProperty();
				imgImageView1.scaleYProperty();
				imgImageView1.setSmooth(true);
				imgImageView1.setCache(true);
				image_pane.setCenter(imgImageView1);
			}
			fullname_txt.setText((String) (employee.getLast_name() + " " + employee.getMiddle_name() + " "
					+ employee.getFirst_name()));
			DOB_text.setText(formatDate.format(employee.getDob()));
			gmail_text.setText((String) employee.getEmail());
			phone_text.setText((String) employee.getTelephone());
			;
			address_text.setText((String) employee.getAddress());
			department_text.setText((String) employee.getDepartment().getDepartment_name());
			position_text.setText((String) employee.getPosition().getPosition_name());

			salary_text.setText(((Integer) employee.getSalary().getValue_money()).toString() + "$");
			principal_text.setText(((Integer) employee.getPrincipal().getValue_money()).toString() + "$");
			onleave_text.setText(((Integer) employee.getOn_leave()).toString());
			descrip_text.setText(employee.getDescription());

			fullname_txt.setText((String) (employee.getLast_name() + " " + employee.getMiddle_name() + " "
					+ employee.getFirst_name()));

			DOB_text.setText(formatDate.format(employee.getDob()));
			gmail_text.setText((String) employee.getEmail());
			phone_text.setText((String) employee.getTelephone());
			;
			address_text.setText((String) employee.getAddress());
			department_text.setText((String) employee.getDepartment().getDepartment_name());
			position_text.setText((String) employee.getPosition().getPosition_name());
			salary_text.setText(((Integer) employee.getSalary().getValue_money()).toString() + "$");
			principal_text.setText(((Integer) employee.getPrincipal().getValue_money()).toString() + "$");
			onleave_text.setText(((Integer) employee.getOn_leave()).toString());
			descrip_text.setText(employee.getDescription());

		}
	}

	private void lean() {
		try {
			getProfile = bo_employee.getProfile(usersession.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}