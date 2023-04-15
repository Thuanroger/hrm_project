package com.hrm.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.Year;
import java.util.ResourceBundle;

import com.hrm.model.beans.department;
import com.hrm.model.beans.position;
import com.hrm.model.beans.salary;
import com.hrm.model.business_objects.bo_salary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.util.StringConverter;

public class showsalary_controller implements Initializable {

	public showsalary_controller() {
		// TODO Auto-generated constructor stub
	}

	@FXML
	private TableView<salary> table_money;

	@FXML
	private TableColumn<salary, String> money_col;

	@FXML
	private TableColumn<salary, String> reward_col;

	@FXML
	private TableColumn<salary, DatePicker> time_col;

	@FXML
	private TableColumn<salary, DatePicker> create_col;

	@FXML
	private Label money;

	@FXML
	private Label year;

	@FXML
	private ImageView avatar;

	@FXML
	private Label name;

	@FXML
	private ComboBox<String> year_box;

	@FXML
	private Button search_btn;

	@FXML
	private Button refresh_btn;
	static int getID;

	private bo_salary dataSalary = new bo_salary();
	private ObservableList<salary> salarylistArray = FXCollections.observableArrayList();

	@FXML
	void Refresh(ActionEvent event) {

	}

	@FXML
	void Search(ActionEvent event) {
		if (year_box.getValue() != null) {
			salarylistArray = dataSalary.getSalaryID(getID, year_box.getValue());
			money.setText(dataSalary.getMoneyId(getID, year_box.getValue()));
			year.setText(year_box.getValue());
			money_col.setCellValueFactory(new PropertyValueFactory<>("moneyString"));
			reward_col.setCellValueFactory(new PropertyValueFactory<>("rewardString"));
			time_col.setCellValueFactory(new PropertyValueFactory<>("time_to_pay"));
			create_col.setCellValueFactory(new PropertyValueFactory<>("created_at"));
			table_money.setItems(salarylistArray);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// set color table salary
		table_money.setRowFactory(tv -> new TableRow<salary>() {
			@Override
			protected void updateItem(salary item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || item.getStatus() == null)
					setStyle("");
				else if (item.getStatus().equals("New"))
					setStyle("");
				else if (item.getStatus().equals("Update"))
					setStyle("-fx-background-color: #FFF59D;");
				else
					setStyle("");

			}
		});

	}

	public void setUpdate(boolean b) {
		// TODO Auto-generated method stub

	}

	public void getprofile(int id) {
		// TODO Auto-generated method stub
		salarylistArray = dataSalary.getSalaryID(id, String.valueOf(LocalDate.now().getYear()));
		year_box.setItems(dataSalary.getYear());
		name.setText(salarylistArray.get(0).getEmployee());
		money.setText(dataSalary.getMoneyId(id, String.valueOf(LocalDate.now().getYear())));
		year.setText(String.valueOf(LocalDate.now().getYear()));

		if (salarylistArray.get(0).getAvatar() == null || (salarylistArray.get(0).getAvatar().equals(""))) {
			Image image = new Image("./com/hrm/assets/avatar/avatarnul.png");
			avatar.setImage(image);
			avatar.setFitWidth(100);
			avatar.setFitHeight(110);
			avatar.scaleXProperty();
			avatar.scaleYProperty();
			avatar.setSmooth(true);
			avatar.setCache(true);
		} else {

			Image image1 = new Image(salarylistArray.get(0).getAvatar());
			avatar.setImage(image1);
			avatar.setFitWidth(100);
			avatar.setFitHeight(110);
			avatar.scaleXProperty();
			avatar.scaleYProperty();
			avatar.setSmooth(true);
			avatar.setCache(true);
		}
		money_col.setCellValueFactory(new PropertyValueFactory<>("moneyString"));
		reward_col.setCellValueFactory(new PropertyValueFactory<>("rewardString"));
		time_col.setCellValueFactory(new PropertyValueFactory<>("time_to_pay"));
		create_col.setCellValueFactory(new PropertyValueFactory<>("created_at"));
		table_money.setItems(salarylistArray);
		getID = id;
	}

}
