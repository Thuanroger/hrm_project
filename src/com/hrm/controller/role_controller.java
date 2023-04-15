package com.hrm.controller;

import java.awt.Robot;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import com.hrm.assets.lib.alert;
import com.hrm.assets.lib.check;
import com.hrm.model.beans.role;
import com.hrm.model.business_objects.bo_roles;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class role_controller implements Initializable {

	public role_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
		getAll();
		InsertTableview();
	}

	@FXML
	private Label role_label;

	@FXML
	private Label time_label;

	@FXML
	private TextField search_field;

	@FXML
	private TableView<role> table_role;

	@FXML
	private TableColumn<role, Integer> ID_col;

	@FXML
	private TableColumn<role, String> role_col;

	@FXML
	private TableColumn<role, DatePicker> creatat_col;

	@FXML
	private TableColumn<role, String> descrip_col;

	@FXML
	private TableColumn<role, String> action_col;

	@FXML
	private TextArea descip_field;

	@FXML
	private TextField role_field;

	@FXML
	private TextField ID_filed;

	@FXML
	private DatePicker create_at_field;

	@FXML
	private Pagination pagination;

	@FXML
	private Button save_btn;

	@FXML
	private Button print_btn;

	@FXML
	private Button refresh_btn;

	private bo_roles dataDao = new bo_roles();
	private ObservableList<role> masterData = FXCollections.observableArrayList();
	private static int ROWS_PER_PAGE = 8;
	private FilteredList<role> filteredData;

	private void clear() {
		descip_field.setText("");
		role_field.setText("");
		ID_filed.setText("");
		create_at_field.setValue(LocalDate.now());
		getAll();
		InsertTableview();
	}

	private void getAll() {
		masterData = dataDao.getAll();
	}

	private void InsertTableview() {
		filteredData = new FilteredList<>(masterData, p -> true);
		// Search
		search_field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Role -> Role.getRole_name().toLowerCase().contains(newValue.toLowerCase()));
			changeTableView(0, masterData.size());
		});
		// add value into cell
		ID_col.setCellValueFactory(new PropertyValueFactory<>("row"));
		role_col.setCellValueFactory(new PropertyValueFactory<>("role_name"));
		creatat_col.setCellValueFactory(new PropertyValueFactory<>("create_at"));
		descrip_col.setCellValueFactory(new PropertyValueFactory<>("description"));

		// add cell of button edit
		Callback<TableColumn<role, String>, TableCell<role, String>> cellFoctory = (
				TableColumn<role, String> param) -> {
			// make cell containing buttons
			final TableCell<role, String> cell = new TableCell<role, String>() {
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
								role Role = table_role.getSelectionModel().getSelectedItem();
								boolean checkDelete = dataDao.delete(Role);

								if (checkDelete == true) {
									alert.Success("Delete role " + Role.getId() + " ");
									clear();
								}
							}
						});
						// update event
						editIcon.setOnMouseClicked((MouseEvent event) -> {
							role Role = table_role.getSelectionModel().getSelectedItem();
							ID_filed.setText(String.valueOf(Role.getId()));
							role_field.setText(Role.getRole_name());
							descip_field.setText(Role.getDescription());
							// setdatepciker fomat date sql
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
							LocalDate localDate = LocalDate.parse(String.valueOf(Role.getCreate_at()), formatter);
							create_at_field.setValue(formatDate(String.valueOf(Role.getCreate_at())));

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

	@FXML
	void Print(ActionEvent event) {
	}

	@FXML
	void Refresh(ActionEvent event) {
		clear();
	}

	@FXML
	void SaveRole(ActionEvent event) {
		if (ID_filed.getText().equals("")) {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Save File");
			alert1.setHeaderText("Are you sure Save " + role_field.getText() + ".");
			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();
			boolean checkSave = false;
			if (option.get() == ButtonType.OK) {
				role Role = new role();
				if (check.checkFtring(role_field.getText())
						&& check.checkdate(String.valueOf(create_at_field.getValue()))) {
					Role.setRole_name(role_field.getText());
					Role.setCreate_at(Date.valueOf(create_at_field.getValue()));
					Role.setDescription(descip_field.getText());
					checkSave = dataDao.save(Role);
				}
				if (checkSave == true) {
					alert.Success("Add Department ");
					clear();
				} else {
					alert.Error();
					if (check.isDateValid(String.valueOf(create_at_field.getValue())) == false) {
						time_label.setText(check.ErrorDate(String.valueOf(create_at_field.getValue())));
						time_label.setVisible(true);
					} else {
						time_label.setVisible(false);
					}
					if (check.checkFtring(role_field.getText()) == false) {
						role_label.setText(check.ErrorString(role_field.getText()));
						role_label.setVisible(true);
					} else {
						role_label.setVisible(false);
					}
				}
			}
		} else {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Update File");
			alert1.setHeaderText("Are you sure Update " + role_field.getText() + " at " + ID_filed.getText());

			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();
			boolean checkSave = false;
			if (option.get() == ButtonType.OK) {
				role Role = new role();
				if (check.checkFtring(role_field.getText())
						&& check.checkdate(String.valueOf(create_at_field.getValue()))) {
					Role.setRole_name(role_field.getText());
					Role.setCreate_at(Date.valueOf(create_at_field.getValue()));
					Role.setDescription(descip_field.getText());
					Role.setId(Integer.parseInt(ID_filed.getText()));
					checkSave = dataDao.update(Role);
				}
				if (checkSave == true) {
					alert.Success("Update Department ");
					clear();
				} else {
					alert.Error();
					if (check.isDateValid(String.valueOf(create_at_field.getValue())) == false) {
						time_label.setText(check.ErrorDate(String.valueOf(create_at_field.getValue())));
						time_label.setVisible(true);
					} else {
						time_label.setVisible(false);
					}
					if (check.checkFtring(role_field.getText()) == false) {
						role_label.setText(check.ErrorString(role_field.getText()));
						role_label.setVisible(true);
					} else {
						role_label.setVisible(false);
					}
				}
			}
		}
	}

	// change table view Method
	private void changeTableView(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, masterData.size());
		int minIndex = Math.min(toIndex, filteredData.size());
		SortedList<role> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_role.comparatorProperty());
		table_role.setItems(sortedData);
	}

	private LocalDate formatDate(String string) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(string, formatter);
		return localDate;
	}

}
