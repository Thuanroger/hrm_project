package com.hrm.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.hrm.model.beans.position;
import com.hrm.model.business_objects.bo_position;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class searchPosition_controller implements Initializable {

	@FXML
	private TableView<position> tablse_s;

	@FXML
	private TableColumn<position, Integer> id_col;

	@FXML
	private TableColumn<position, String> name_col;

	@FXML
	private TableColumn<position, String> whocreate_col;

	@FXML
	private TextField search_fiield;

	@FXML
	private Button clear_btn;
	private bo_position dPosition = new bo_position();
	private ObservableList<position> DataSeachPosition = FXCollections.observableArrayList();
	private FilteredList<position> filteredDataPos;

	@FXML
	void Refresh(ActionEvent event) {
		clean();
	}

	public void getList() {

		DataSeachPosition = dPosition.getAll();
	}

	private void clean() {
		getList();
		tableSearchPosition();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		getList();
		tableSearchPosition();

	}

	public void tableSearchPosition() {
		// Search Employee
		filteredDataPos = new FilteredList<>(DataSeachPosition, p -> true);
		// Search
		search_fiield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredDataPos.setPredicate(Po -> Po.getPosition_name().toLowerCase().contains(newValue.toLowerCase()));
//					|| Po.getWho_create().toLowerCase().contains(newValue.toLowerCase()));
			changeTableSearch1(0, DataSeachPosition.size());
		});
		id_col.setCellValueFactory(new PropertyValueFactory<>("row"));
		name_col.setCellValueFactory(new PropertyValueFactory<>("position_name"));
		whocreate_col.setCellValueFactory(new PropertyValueFactory<>("who_create"));
		changeTableSearch1(0, DataSeachPosition.size());
	}

	// change table search position
	private void changeTableSearch1(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, DataSeachPosition.size());
		int minIndex = Math.min(toIndex, filteredDataPos.size());
		SortedList<position> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredDataPos.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(tablse_s.comparatorProperty());
		tablse_s.setItems(sortedData);
	}

}
