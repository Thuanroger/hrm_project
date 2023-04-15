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
import com.hrm.model.beans.module;
import com.hrm.model.beans.role;
import com.hrm.model.business_objects.bo_module;
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

public class module_controller implements Initializable {

	public module_controller() {
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

//				createat_field.setConverter(converter);
		//
		// 1
		getList();
		InserTableView();

	}

	@FXML
	private TextField search_field;

	@FXML
	private TableView<module> table_module;

	@FXML
	private TableColumn<module, Integer> ID_col;

	@FXML
	private TableColumn<module, Integer> module_col;

	@FXML
	private TableColumn<module, String> descrip_col;

	@FXML
	private TableColumn<module, String> action_col;

	@FXML
	private TextArea descrip_fiield;

	@FXML
	private TextField module_field;

	@FXML
	private TextField ID_field;

	@FXML
	private Pagination pagination;

	@FXML
	private Button save_btn;

	@FXML
	private Button print_btn;

	@FXML
	private Button refresh_btn;

	@FXML
	private Label module_label;
	private bo_module dataDao = new bo_module();
	private ObservableList<module> masterData = FXCollections.observableArrayList();
	private static int ROWS_PER_PAGE = 8;;
	private FilteredList<module> filteredData;

	@FXML
	void AddModule(ActionEvent event) {
		if (ID_field.getText().equals("")) {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Save file");
			alert1.setHeaderText("Are you sure Save " + module_field.getText() + ".");
			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();
			boolean checkSave = false;
			if (option.get() == ButtonType.OK) {
				module MO = new module();
				if (check.checkFtring(module_field.getText())) {
					MO.setModule_name(module_field.getText());
					MO.setDescription(descrip_fiield.getText());
					checkSave = dataDao.save(MO);
				}
				if (checkSave == true) {
					alert.Success("Add Module ");
					clean();
				} else {
					alert.Error();
					if (check.checkFtring(module_field.getText()) == false) {
						module_label.setText(check.ErrorString(module_field.getText()));
						module_label.setVisible(true);
					} else {
						module_label.setVisible(false);
					}
				}
			}
		} else {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Update File");
			alert1.setHeaderText("Are you sure Update " + module_field.getText() + " at " + ID_field.getText());
			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();
			boolean checkSave = false;
			if (option.get() == ButtonType.OK) {
				module MO = new module();
				if (check.checkFtring(module_field.getText())) {
					MO.setModule_name(module_field.getText());
					MO.setDescription(descrip_fiield.getText());
					MO.setId(Integer.parseInt(ID_field.getText()));
					checkSave = dataDao.update(MO);
				}
				if (checkSave == true) {
					alert.Success("Update Module ");
					clean();
				} else {
					alert.Error();
					if (check.checkFtring(module_field.getText()) == false) {
						module_label.setText(check.ErrorString(module_field.getText()));
						module_label.setVisible(true);
					} else {
						module_label.setVisible(false);
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

	private void clean() {

		descrip_fiield.setText("");
		module_field.setText("");
		ID_field.setText("");
		getList();
		InserTableView();

	}

	public void getList() {
		masterData = dataDao.getAll();
	}

	public void InserTableView() {
		filteredData = new FilteredList<>(masterData, p -> true);
		// Search
		search_field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Module -> Module.getModule_name().toLowerCase().contains(newValue.toLowerCase())
					|| Module.getDescription().toLowerCase().contains(newValue.toLowerCase()));
			changeTableView(0, masterData.size());

		});
		// add value into cell
		ID_col.setCellValueFactory(new PropertyValueFactory<>("row"));
		module_col.setCellValueFactory(new PropertyValueFactory<>("module_name"));
		descrip_col.setCellValueFactory(new PropertyValueFactory<>("description"));

		// add cell of button edit

		Callback<TableColumn<module, String>, TableCell<module, String>> cellFoctory = (
				TableColumn<module, String> param) -> {
			// make cell containing buttons
			final TableCell<module, String> cell = new TableCell<module, String>() {
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
								module Module = table_module.getSelectionModel().getSelectedItem();
								boolean checkDelete = dataDao.delete(Module);

								if (checkDelete == true) {
									alert.Success("Delete Module " + Module.getModule_name() + " ");
									clean();
								}
							}

						});

						// update event

						editIcon.setOnMouseClicked((MouseEvent event) -> {

							module Module = table_module.getSelectionModel().getSelectedItem();

							ID_field.setText(String.valueOf(Module.getId()));
							module_field.setText(Module.getModule_name());
							descrip_fiield.setText(Module.getDescription());
							// setdatepciker fomat date sql
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
		SortedList<module> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_module.comparatorProperty());
		table_module.setItems(sortedData);
	}

	private LocalDate formatDate(String string) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(string, formatter);
		return localDate;
	}

}
