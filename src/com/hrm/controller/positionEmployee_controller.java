package com.hrm.controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import com.hrm.assets.lib.alert;
import com.hrm.assets.lib.check;
import com.hrm.model.beans.employee_search;
import com.hrm.model.beans.position;
import com.hrm.model.beans.position_employee;
import com.hrm.model.business_objects.bo_position;
import com.hrm.model.business_objects.bo_positionemployee;
import com.hrm.model.business_objects.bo_principal;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class positionEmployee_controller implements Initializable {

	@FXML
	private Label posi_label;

	@FXML
	private Label time_label;

	@FXML
	private Label id_label;

	@FXML
	private TableView<position_employee> table_PandE;

	@FXML
	private TableColumn<position_employee, Integer> ID_col;

	@FXML
	private TableColumn<position_employee, String> position_col;

	@FXML
	private TableColumn<position_employee, String> employee_col;

	@FXML
	private TableColumn<position_employee, DatePicker> create_col;

	@FXML
	private TableColumn<position_employee, String> descrip_col;

	@FXML
	private TableColumn<position_employee, String> action_col;

	@FXML
	private Button save_btn;

	@FXML
	private Button print_btn;

	@FXML
	private Button refresh_btn;

	@FXML
	private Pagination pagination;

	@FXML
	private TextArea description_field;

	@FXML
	private TextField employee_field;

	@FXML
	private DatePicker create_at_field;

	@FXML
	private TextField ID_field;

	@FXML
	private TextField position_field;

	@FXML
	private TextField search_field;

	@FXML
	private TableView<employee_search> table_em;

	@FXML
	private TableColumn<employee_search, Integer> id_E_col;

	@FXML
	private TableColumn<employee_search, String> em_col;

	@FXML
	private TableColumn<employee_search, String> depart_col;

	@FXML
	private TableColumn<employee_search, DatePicker> DOB_col;
	@FXML
	private TableColumn<employee_search, String> avatar_col;

	@FXML
	private TableView<position> table_Po;

	@FXML
	private TableColumn<position, Integer> id_P_col;

	@FXML
	private TableColumn<position, String> Pos_col;

	@FXML
	private TableColumn<position, String> whocreate_col;

	@FXML
	private TextField search_P_field;

	@FXML
	private TextField search_E_field;

	private bo_positionemployee dataDao = new bo_positionemployee();
	private bo_principal dPrincipal = new bo_principal();
	private bo_position dPosition = new bo_position();
	private ObservableList<position_employee> masterData = FXCollections.observableArrayList();
	private ObservableList<employee_search> DataSeach = FXCollections.observableArrayList();
	private ObservableList<position> DataSeachPosition = FXCollections.observableArrayList();
	private static int ROWS_PER_PAGE = 8;
	private FilteredList<position_employee> filteredData;
	private FilteredList<employee_search> filteredDataEm;
	private FilteredList<position> filteredDataPos;

	@FXML
	void AddPandE(ActionEvent event) {
		if (ID_field.getText().equals("")) {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Save File");
			alert1.setHeaderText("Are you sure Save " + ID_field.getText() + ".");

			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();
			boolean checkSave = false;
			if (option.get() == ButtonType.OK) {

				position_employee PE = new position_employee();
				if (check.checkFnumber(position_field.getText()) && check.checkFnumber(employee_field.getText())
						&& check.checkdate(String.valueOf(create_at_field.getValue()))) {
					PE.setEmployee_id(Integer.parseInt(employee_field.getText()));
					PE.setPosition_id(Integer.parseInt(position_field.getText()));
					PE.setCreated_at(Date.valueOf(create_at_field.getValue()));
					PE.setDescription(description_field.getText());

					checkSave = dataDao.save(PE);
				}
				if (checkSave == true) {
					alert.Success("Add Position to Employee ");
					clean();
				} else {
					alert.Error();
					if (check.isDateValid(String.valueOf(create_at_field.getValue())) == false) {
						time_label.setText(check.ErrorDate(String.valueOf(create_at_field.getValue())));
						time_label.setVisible(true);
					} else {
						time_label.setVisible(false);
					}
					if (check.checkFnumber(position_field.getText()) == false) {
						posi_label.setText(check.ErrorString(position_field.getText()));
						posi_label.setVisible(true);
					} else {
						posi_label.setVisible(false);
					}
					if (check.checkFnumber(employee_field.getText()) == false) {
						id_label.setText(check.ErrorString(employee_field.getText()));
						id_label.setVisible(true);
					} else {
						id_label.setVisible(false);
					}
				}
			}

		} else {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Update File");
			alert1.setHeaderText("Are you sure Update " + " at " + ID_field.getText());

			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();
			boolean checkSave = false;
			if (option.get() == ButtonType.OK) {
				position_employee PE = new position_employee();
				if (check.checkFnumber(position_field.getText()) && check.checkFnumber(employee_field.getText())
						&& check.checkdate(String.valueOf(create_at_field.getValue()))) {
					PE.setEmployee_id(Integer.parseInt(employee_field.getText()));
					PE.setPosition_id(Integer.parseInt(position_field.getText()));
					PE.setCreated_at(Date.valueOf(create_at_field.getValue()));
					PE.setDescription(description_field.getText());
					PE.setId(Integer.parseInt(ID_field.getText()));

					checkSave = dataDao.update(PE);
				}
				if (checkSave == true) {
					alert.Success("Update Position Employee ");
					clean();
				} else {
					alert.Error();
					if (check.isDateValid(String.valueOf(create_at_field.getValue())) == false) {
						time_label.setText(check.ErrorDate(String.valueOf(create_at_field.getValue())));
						time_label.setVisible(true);
					} else {
						time_label.setVisible(false);
					}
					if (check.checkFnumber(position_field.getText()) == false) {
						posi_label.setText(check.ErrorString(position_field.getText()));
						posi_label.setVisible(true);
					} else {
						posi_label.setVisible(false);
					}
					if (check.checkFnumber(employee_field.getText()) == false) {
						id_label.setText(check.ErrorString(employee_field.getText()));
						id_label.setVisible(true);
					} else {
						id_label.setVisible(false);
					}
				}
			}
		}
	}

	@FXML
	void Print(ActionEvent event) {

	}

	@FXML
	void Refresh(ActionEvent event) {
		clean();
	}

	public void getList() {
		masterData = dataDao.getAll();
		DataSeach = dPrincipal.getSearchs();
		DataSeachPosition = dPosition.getAll();
	}

	private void clean() {
		create_at_field.setValue(LocalDate.now());
		description_field.setText("");
		ID_field.setText("");
		position_field.setText("");
		search_field.setText("");
		search_E_field.setText("");
		search_P_field.setText("");
		employee_field.setText("");
		getList();
		InserTableView();
		tableSearch();
		tableSearchPosition();

	}

	public positionEmployee_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		// format Datepicker to Dale.sql
		String pattern = "yyyy-MM-dd";
		StringConverter converter = new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				} else {
					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else {
					return null;
				}
			}
		};

		create_at_field.setConverter(converter);

		//
		// 1
		getList();
		InserTableView();
		tableSearch();
		tableSearchPosition();

	}

	public void tableSearch() {
		// Search Employee
		filteredDataEm = new FilteredList<>(DataSeach, p -> true);
		// Search
		search_E_field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredDataEm.setPredicate(Em -> Em.getDepartment().toLowerCase().contains(newValue.toLowerCase())
					|| Em.getFullname().toLowerCase().contains(newValue.toLowerCase()));
			changeTableSearch(0, DataSeach.size());
		});
		id_E_col.setCellValueFactory(new PropertyValueFactory<>("id"));
		em_col.setCellValueFactory(new PropertyValueFactory<>("fullname"));
		depart_col.setCellValueFactory(new PropertyValueFactory<>("department"));
		DOB_col.setCellValueFactory(new PropertyValueFactory<>("DOB"));
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

	public void tableSearchPosition() {
		// Search Employee
		filteredDataPos = new FilteredList<>(DataSeachPosition, p -> true);
		// Search
		search_P_field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredDataPos.setPredicate(Po -> Po.getPosition_name().toLowerCase().contains(newValue.toLowerCase()));
//					|| Po.getWho_create().toLowerCase().contains(newValue.toLowerCase()));
			changeTableSearch1(0, DataSeachPosition.size());
		});
		id_P_col.setCellValueFactory(new PropertyValueFactory<>("id"));
		Pos_col.setCellValueFactory(new PropertyValueFactory<>("position_name"));
		whocreate_col.setCellValueFactory(new PropertyValueFactory<>("who_create"));
		changeTableSearch1(0, DataSeachPosition.size());
	}

	public void InserTableView() {
		filteredData = new FilteredList<>(masterData, p -> true);
		// Search
		search_field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(POD -> POD.getPosition_name().toLowerCase().contains(newValue.toLowerCase())
					|| POD.getEmployee_name().toLowerCase().contains(newValue.toLowerCase()));
			changeTableView(0, masterData.size());
			changeTableView(0, masterData.size());
		});
		// add value into cell
		ID_col.setCellValueFactory(new PropertyValueFactory<>("row"));
		position_col.setCellValueFactory(new PropertyValueFactory<>("position_name"));
		employee_col.setCellValueFactory(new PropertyValueFactory<>("employee_name"));
		create_col.setCellValueFactory(new PropertyValueFactory<>("created_at"));
		descrip_col.setCellValueFactory(new PropertyValueFactory<>("description"));
		// add cell of button edit

		Callback<TableColumn<position_employee, String>, TableCell<position_employee, String>> cellFoctory = (
				TableColumn<position_employee, String> param) -> {
			// make cell containing buttons
			final TableCell<position_employee, String> cell = new TableCell<position_employee, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					// that cell created only on non-empty rows
					if (empty) {
						setGraphic(null);
						setText(null);

					} else {

						Label deleteIcon = GlyphsDude.createIconLabel(FontAwesomeIcons.TRASH, "", "25px", "25px",
								ContentDisplay.LEFT);

						Label editIcon = GlyphsDude.createIconLabel(FontAwesomeIcons.PENCIL_SQUARE, "", "25px", "25px",
								ContentDisplay.LEFT);
						deleteIcon.getStyleClass().add("delete-label");
						editIcon.getStyleClass().add("update-label");
						// delete event
						deleteIcon.setOnMouseClicked((MouseEvent event) -> {
							// Alert delete
							Alert alert1 = new Alert(AlertType.CONFIRMATION);
							alert1.setTitle("Delete File");
							alert1.setHeaderText("Are you sure want to move this file to the Recycle Bin?");

							// option != null.
							Optional<ButtonType> option = alert1.showAndWait();

							if (option.get() == ButtonType.OK) {
								position_employee PE = table_PandE.getSelectionModel().getSelectedItem();
								boolean checkDelete = dataDao.delete(PE);

								if (checkDelete == true) {
									alert.Success("Delete at  " + PE.getId() + " ");
									clean();
								}
							}

						});

						// update event

						editIcon.setOnMouseClicked((MouseEvent event) -> {

							position_employee PanE = table_PandE.getSelectionModel().getSelectedItem();

							ID_field.setText(String.valueOf(PanE.getId()));
							employee_field.setText(String.valueOf(PanE.getEmployee_id()));
							position_field.setText(String.valueOf(PanE.getPosition_id()));
							create_at_field.setValue(formatDate(String.valueOf(PanE.getCreated_at())));
							description_field.setText(PanE.getDescription());

						});
						HBox managebtn = new HBox(editIcon, deleteIcon);
						managebtn.setStyle("-fx-alignment:center");
						HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
						HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

						setGraphic(managebtn);

						setText(null);

					}
				}

			};

			return cell;
		};

		action_col.setCellFactory(cellFoctory);
		// set page in table view
		int totalPage = (int) (Math.ceil(masterData.size() * 1.0 / ROWS_PER_PAGE));
		pagination.setCurrentPageIndex(0);
		pagination.setPageCount(totalPage);
		changeTableView(0, ROWS_PER_PAGE);
		pagination.currentPageIndexProperty()
				.addListener((observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));

	}

	// change table view Method
	private void changeTableView(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, masterData.size());
		int minIndex = Math.min(toIndex, filteredData.size());
		SortedList<position_employee> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_PandE.comparatorProperty());
		table_PandE.setItems(sortedData);
	}

	// change table search employee
	private void changeTableSearch(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, DataSeach.size());
		int minIndex = Math.min(toIndex, filteredDataEm.size());
		SortedList<employee_search> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredDataEm.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_em.comparatorProperty());
		table_em.setItems(sortedData);
	}

	// change table search position
	private void changeTableSearch1(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, DataSeachPosition.size());
		int minIndex = Math.min(toIndex, filteredDataPos.size());
		SortedList<position> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredDataPos.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_Po.comparatorProperty());
		table_Po.setItems(sortedData);
	}

	// format date time
	private LocalDate formatDate(String string) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(string, formatter);
		return localDate;
	}

}
