package com.hrm.controller;

import java.net.URL;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import com.hrm.assets.lib.alert;
import com.hrm.assets.lib.check;
import com.hrm.model.beans.module;
import com.hrm.model.beans.module_role;
import com.hrm.model.beans.role;

import com.hrm.model.business_objects.bo_module;
import com.hrm.model.business_objects.bo_modulerole;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;

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

public class role_module_controller implements Initializable {

	public role_module_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		module_field.setConverter(new StringConverter<module>() {
			@Override
			public String toString(module object) {
				return object.getModule_name();
			}

			@Override
			public module fromString(String string) {
				return null;
			}
		});
		role_field.setConverter(new StringConverter<role>() {
			@Override
			public String toString(role object) {
				return object.getRole_name();
			}

			@Override
			public role fromString(String string) {
				return null;
			}
		});

		// add data combo box module and role
		module_field.setItems(dataModule.getAll());
		role_field.setItems(dataRoles.getAll());

		module_field.valueProperty().addListener((obs, oldVal, newVal) -> {
			int selectionText = newVal.getId();
			moduletext = selectionText;
		});
		role_field.valueProperty().addListener((obs, oldVal, newVal) -> {
			int selectionText = newVal.getId();
			roletext = selectionText;
		});
		//
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
//			createat_field.setConverter(converter);
		//
		// 1
		getList();
		InserTableView();
	}

	@FXML
	private Label module_label;

	@FXML
	private Label role_label;

	@FXML
	private TextField search_field;

	@FXML
	private Button search_btn;

	@FXML
	private TableView<module_role> table_module_role;

	@FXML
	private TableColumn<module_role, Integer> ID_col;

	@FXML
	private TableColumn<module_role, String> modulename_col;

	@FXML
	private TableColumn<module_role, String> role_col;

	@FXML
	private TableColumn<module_role, String> descip_col;

	@FXML
	private TableColumn<module_role, String> action_col;

	@FXML
	private TextArea descip_field;

	@FXML
	private TextField ID_field;

	@FXML
	private ComboBox<module> module_field;

	@FXML
	private ComboBox<role> role_field;

	@FXML
	private Pagination pagination;

	@FXML
	private Button save_btn;

	@FXML
	private Button print_btn;

	@FXML
	private Button refresh_btn;

	@FXML
	private Button edit_btn;

	private bo_module dataModule = new bo_module();
	private bo_roles dataRoles = new bo_roles();
	private bo_modulerole dataDao = new bo_modulerole();

	private ObservableList<module_role> masterData = FXCollections.observableArrayList();

	private static int ROWS_PER_PAGE = 8;;

	private FilteredList<module_role> filteredData;
	static int roletext = 0;
	static int moduletext = 0;

	@FXML
	void EditModuleRole(ActionEvent event) {

	}

	@FXML
	void Print(ActionEvent event) {

	}

	@FXML
	void Refresh(ActionEvent event) {
		clean();
	}

	private void clean() {
		ID_field.setText("");
//		role_field.setValue(null);
//		module_field.setValue(null);
		descip_field.setText("");
		search_field.setText("");
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
			filteredData.setPredicate(MR -> MR.getModule_name().toLowerCase().contains(newValue.toLowerCase())
					|| MR.getRole_name().toLowerCase().contains(newValue.toLowerCase()));
			changeTableView(0, masterData.size());
			changeTableView(0, masterData.size());
		});
		// add value into cell

		ID_col.setCellValueFactory(new PropertyValueFactory<>("row"));
		modulename_col.setCellValueFactory(new PropertyValueFactory<>("module_name"));
		role_col.setCellValueFactory(new PropertyValueFactory<>("role_name"));
		descip_col.setCellValueFactory(new PropertyValueFactory<>("description"));

		// add cell of button edit

		Callback<TableColumn<module_role, String>, TableCell<module_role, String>> cellFoctory = (
				TableColumn<module_role, String> param) -> {
			// make cell containing buttons
			final TableCell<module_role, String> cell = new TableCell<module_role, String>() {
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
								module_role MR = table_module_role.getSelectionModel().getSelectedItem();
								boolean checkDelete = dataDao.delete(MR);

								if (checkDelete == true) {
									alert.Success("Delete Module " + MR.getModule_name() + " of " + MR.getRole_name());
									clean();
								}
							}

						});

						// update event

						editIcon.setOnMouseClicked((MouseEvent event) -> {

							module_role MR = table_module_role.getSelectionModel().getSelectedItem();

							ID_field.setText(String.valueOf(MR.getId()));
							module_field.setValue(dataDao.getModule(MR.getModule_name()));
							role_field.setValue(dataDao.getRole(MR.getRole_name()));
							descip_field.setText(MR.getDescription());

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
	void Save(ActionEvent event) {
		if (ID_field.getText().equals("")) {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Save File");
			alert1.setHeaderText("Are you sure Save " + ID_field.getText() + ".");
			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();
			boolean checkSave = false;
			if (option.get() == ButtonType.OK) {
				module_role MR = new module_role();
				if (moduletext != 0 && roletext != 0) {
					MR.setModule_id(moduletext);
					MR.setRole_id(roletext);
					MR.setDescription(descip_field.getText());
					checkSave = dataDao.save(MR);
				}
				if (checkSave == true) {
					alert.Success("Add ");
					clean();
				} else {
					alert.Error();
					if (moduletext == 0) {
						module_label.setText("Required");
						module_label.setVisible(true);
					} else {
						module_label.setVisible(false);
					}
					if (roletext == 0) {
						role_label.setText("Required");
						role_label.setVisible(true);
					} else {
						role_label.setVisible(false);
					}
				}
			}
		} else {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Update File");
			alert1.setHeaderText("Are you sure Update " + ID_field.getText());
			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();
			boolean checkSave = false;
			if (option.get() == ButtonType.OK) {
				module_role MR = new module_role();
				if (moduletext != 0 && roletext != 0) {
					MR.setModule_id(moduletext);
					MR.setRole_id(roletext);
					MR.setDescription(descip_field.getText());
					MR.setId(Integer.parseInt(ID_field.getText()));
					checkSave = dataDao.update(MR);
				}
				if (checkSave == true) {
					alert.Success("Update  ");
					clean();
				} else {
					alert.Error();
					if (moduletext == 0) {
						module_label.setText("Required");
						module_label.setVisible(true);
					} else {
						module_label.setVisible(false);
					}
					if (roletext == 0) {
						role_label.setText("Required");
						role_label.setVisible(true);
					} else {
						role_label.setVisible(false);
					}
				}
			}
		}
	}

	@FXML
	void Search(ActionEvent event) {
	}

	// change table view Method
	private void changeTableView(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, masterData.size());
		int minIndex = Math.min(toIndex, filteredData.size());
		SortedList<module_role> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_module_role.comparatorProperty());
		table_module_role.setItems(sortedData);
	}

	private LocalDate fomateDate(String string) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(string, formatter);
		return localDate;
	}

}
