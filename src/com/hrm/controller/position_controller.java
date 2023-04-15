package com.hrm.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import com.hrm.assets.lib.alert;
import com.hrm.assets.lib.check;
import com.hrm.model.beans.position;
import com.hrm.model.business_objects.bo_position;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class position_controller implements Initializable {

	public position_controller() {
		// TODO Auto-generated constructor stub
	}

	@FXML
	private TableView<position> table_position;

	@FXML
	private TableColumn<position, Integer> ID_col;

	@FXML
	private TableColumn<position, String> position_col;

	@FXML
	private TableColumn<position, DatePicker> create_coll;

	@FXML
	private TableColumn<position, String> who_col;

	@FXML
	private TableColumn<position, String> descrip_col;

	@FXML
	private TableColumn<position, String> action_col;

	@FXML
	private Button save_btn;

	@FXML
	private Button print_btn;

	@FXML
	private Button refresh_btn;

	@FXML
	private Pagination pagination;

	@FXML
	private TextArea descrip_field;

	@FXML
	private TextField position_field;

	@FXML
	private DatePicker createat_field;

	@FXML
	private Label pomane_label;

	@FXML
	private Label who_label;

	@FXML
	private Label time_label;

	@FXML
	private TextField whocreate_field;

	@FXML
	private TextField iD_field;

	@FXML
	private TextField search_field;

	@FXML
	private Button search_btn;

	@FXML
	private Button create_user_btn;

	private bo_position dataDao = new bo_position();

	private ObservableList<position> masterData = FXCollections.observableArrayList();

	private static int ROWS_PER_PAGE = 8;;

	private FilteredList<position> filteredData;

	@FXML
	void PositionEmpoyee(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getResource("../view/position_employee.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		Image icon = new Image("./com/hrm/assets/logo/logo.jpg");
		stage.getIcons().add(icon);
		stage.setTitle("Search Emloyee");
		stage.show();
	}

	@FXML
	void Print(ActionEvent event) {

	}

	@FXML
	void Refresh(ActionEvent event) {
		clean();
	}

	@FXML
	void SavePosition(ActionEvent event) {
		if (iD_field.getText().equals("")) {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Save File");
			alert1.setHeaderText("Are you sure Save " + position_field.getText() + ".");

			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();
			boolean checkSave = false;
			if (option.get() == ButtonType.OK) {
				position Position = new position();
				if (check.checkFtring(position_field.getText())
						&& check.checkdate(String.valueOf(createat_field.getValue()))) {
					Position.setPosition_name(position_field.getText());
					Position.setDescription(descrip_field.getText());
					Position.setCreated_at(Date.valueOf(createat_field.getValue()));
					Position.setWho_create(whocreate_field.getText());
					checkSave = dataDao.save(Position);
				}
				if (checkSave == true) {
					alert.Success("Add Department ");
					clean();
				} else {
					alert.Error();
					if (check.isDateValid(String.valueOf(createat_field.getValue())) == false) {
						time_label.setText(check.ErrorDate(String.valueOf(createat_field.getValue())));
						time_label.setVisible(true);
					} else {
						time_label.setVisible(false);
					}
					if (check.checkFtring(position_field.getText()) == false) {
						pomane_label.setText(check.ErrorString(position_field.getText()));
						pomane_label.setVisible(true);
					} else {
						pomane_label.setVisible(false);
					}
				}
			}

		} else {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Update File");
			alert1.setHeaderText("Are you sure Update " + position_field.getText() + " at " + iD_field.getText());

			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();
			boolean checkSave = false;
			if (option.get() == ButtonType.OK) {
				position Position = new position();
				if (check.checkFtring(position_field.getText())
						&& check.checkdate(String.valueOf(createat_field.getValue()))) {
					Position.setId(Integer.parseInt(iD_field.getText()));
					Position.setPosition_name(position_field.getText());
					Position.setDescription(descrip_field.getText());
					Position.setCreated_at(Date.valueOf(createat_field.getValue()));
					Position.setWho_create(whocreate_field.getText());
					checkSave = dataDao.update(Position);
				}
				if (checkSave == true) {
					alert.Success("Update Position ");
					clean();
				} else {
					alert.Error();
					if (check.isDateValid(String.valueOf(createat_field.getValue())) == false) {
						time_label.setText(check.ErrorDate(String.valueOf(createat_field.getValue())));
						time_label.setVisible(true);
					} else {
						time_label.setVisible(false);
					}
					if (check.checkFtring(position_field.getText()) == false) {
						pomane_label.setText(check.ErrorString(position_field.getText()));
						pomane_label.setVisible(true);
					} else {
						pomane_label.setVisible(false);
					}
				}
			}

		}
	}

	@FXML
	void SearchPosition(ActionEvent event) {

	}

	private void clean() {
		createat_field.setValue(LocalDate.now());
		descrip_field.setText("");
		position_field.setText("");
		iD_field.setText("");
		whocreate_field.setText("");
		getList();
		InserTableView();
	}

	public void getList() {
		masterData = dataDao.getAll();
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
		createat_field.setConverter(converter);
		//
		// 1
		getList();
		InserTableView();
	}

	// change table view Method
	private void changeTableView(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, masterData.size());
		int minIndex = Math.min(toIndex, filteredData.size());
		SortedList<position> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_position.comparatorProperty());
		table_position.setItems(sortedData);
	}

	private LocalDate fomateDate(String string) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(string, formatter);
		return localDate;
	}

	public void InserTableView() {
		filteredData = new FilteredList<>(masterData, p -> true);
		// Search
		search_field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData
					.setPredicate(Position -> Position.getPosition_name().toLowerCase().contains(newValue.toLowerCase())
							|| Position.getWho_create().toLowerCase().contains(newValue.toLowerCase()));
			changeTableView(0, masterData.size());
			changeTableView(0, masterData.size());
		});
		// add value into cell

		ID_col.setCellValueFactory(new PropertyValueFactory<>("row"));
		position_col.setCellValueFactory(new PropertyValueFactory<>("position_name"));
		create_coll.setCellValueFactory(new PropertyValueFactory<>("created_at"));
		descrip_col.setCellValueFactory(new PropertyValueFactory<>("description"));
		who_col.setCellValueFactory(new PropertyValueFactory<>("who_create"));

		// add cell of button edit

		Callback<TableColumn<position, String>, TableCell<position, String>> cellFoctory = (
				TableColumn<position, String> param) -> {
			// make cell containing buttons
			final TableCell<position, String> cell = new TableCell<position, String>() {
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
								position Position = table_position.getSelectionModel().getSelectedItem();
								boolean checkDelete = dataDao.delete(Position);

								if (checkDelete == true) {
									alert.Success("Delete Department " + Position + " ");
									clean();
								}
							}

						});

						// update event

						editIcon.setOnMouseClicked((MouseEvent event) -> {

							position Position = table_position.getSelectionModel().getSelectedItem();

							iD_field.setText(String.valueOf(Position.getId()));
							position_field.setText(Position.getPosition_name());
							whocreate_field.setText(Position.getWho_create());
							// setdatepciker fomat date sql
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
							LocalDate localDate = LocalDate.parse(String.valueOf(Position.getCreated_at()), formatter);

							createat_field.setValue(fomateDate(String.valueOf(Position.getCreated_at())));
							descrip_field.setText(Position.getDescription());

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

}
