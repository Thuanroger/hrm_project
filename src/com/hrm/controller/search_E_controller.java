package com.hrm.controller;

import java.net.URL;
import java.util.ResourceBundle;
import com.hrm.model.beans.employee_search;
import com.hrm.model.business_objects.bo_principal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class search_E_controller implements Initializable {

	public search_E_controller() {
		// TODO Auto-generated constructor stub
	}

	@FXML
	private TableView<employee_search> tablse_s;

	@FXML
	private TableColumn<employee_search, Integer> id_col;

	@FXML
	private TableColumn<employee_search, String> name_col;

	@FXML
	private TableColumn<employee_search, String> depa_col;

	@FXML
	private TableColumn<employee_search, DatePicker> dob_col;

	@FXML
	private TableColumn<employee_search, String> avatar_col;

	@FXML
	private Button clear_btn;

	private bo_principal dataDao = new bo_principal();
	@FXML
	private TextField search_fiield;
	private ObservableList<employee_search> DataSeach = FXCollections.observableArrayList();
	private FilteredList<employee_search> filteredDataEm;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		getList();
		tableSearch();
	}

	// change table search employee
	private void changeTableSearch(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, DataSeach.size());
		int minIndex = Math.min(toIndex, filteredDataEm.size());
		SortedList<employee_search> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredDataEm.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(tablse_s.comparatorProperty());
		tablse_s.setItems(sortedData);
	}

	@FXML
	void Refresh(ActionEvent event) {
		clean();
	}

	private void clean() {
		search_fiield.setText("");
		getList();
		tableSearch();
	}

	public void getList() {
		DataSeach = dataDao.getSearchs();
	}

	public void tableSearch() {
//		 Search Employee
		filteredDataEm = new FilteredList<>(DataSeach, p -> true);
		// Search
		search_fiield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredDataEm.setPredicate(Em -> Em.getDepartment().toLowerCase().contains(newValue.toLowerCase())
					|| Em.getFullname().toLowerCase().contains(newValue.toLowerCase()));
			changeTableSearch(0, DataSeach.size());
		});
		id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
		name_col.setCellValueFactory(new PropertyValueFactory<>("fullname"));
		depa_col.setCellValueFactory(new PropertyValueFactory<>("department"));
		dob_col.setCellValueFactory(new PropertyValueFactory<>("DOB"));
		avatar_col.setCellValueFactory(new PropertyValueFactory<>("avatar"));

		Callback<TableColumn<employee_search, String>, TableCell<employee_search, String>> cellFoctory12 = (
				TableColumn<employee_search, String> param) -> {
			// make cell containing buttons
			final TableCell<employee_search, String> cell = new TableCell<employee_search, String>() {

				ImageView imageview = new ImageView();

				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					// that cell created only on non-empty rows
					if (item == null || item.equals("")) {
						HBox box = new HBox();
						box.setSpacing(10);
						box.setStyle("-fx-alignment:center");

						Image image = new Image("./com/hrm/assets/avatar/avatarnul.png");
						imageview.setFitHeight(50);
						imageview.setFitWidth(50);
						imageview.setImage(image);
						box.getChildren().addAll(imageview);
						// SETTING ALL THE GRAPHICS COMPONENT FOR CELL
						setGraphic(box);

					} else {
						HBox box = new HBox();
						box.setStyle("-fx-alignment:center");
						Image image = new Image(item);
						imageview.setFitHeight(50);
						imageview.setFitWidth(50);
						imageview.setImage(image);

						box.getChildren().addAll(imageview);
						// SETTING ALL THE GRAPHICS COMPONENT FOR CELL
						setGraphic(box);
					}
				}
			};

			return cell;
		};

		avatar_col.setCellFactory(cellFoctory12);
		changeTableSearch(0, DataSeach.size());

	}

}
