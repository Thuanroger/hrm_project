package com.hrm.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import com.hrm.assets.lib.alert;
import com.hrm.assets.lib.tooltip_get;
import com.hrm.model.beans.employee;
import com.hrm.model.business_objects.bo_employee;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class employee_controller implements Initializable {

	ObservableList<employee> emloyee_table_obList = FXCollections.observableArrayList();

	public employee_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {

			loadTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static int ROWS_PER_PAGE = 13;
	private FilteredList<employee> filteredData;
	private bo_employee dataDAO = new bo_employee();
	@FXML
	private AnchorPane employee;

	@FXML
	private TextField search_field;

	@FXML
	private Button search_btn;

	@FXML
	private Pagination pagination;

	@FXML
	private Button refresh_btn;

	@FXML
	private Button addemployee_btn;

	@FXML
	private Button print_btn;

	@FXML
	private TableView<employee> table_employee;

	@FXML
	private TableColumn<employee, Integer> ID_col;
	@FXML
	private TableColumn<employee, String> avatar_col;
	@FXML
	private TableColumn<employee, String> name_col;

	@FXML
	private TableColumn<employee, Date> DOB_col;

	@FXML
	private TableColumn<employee, String> department_col;

	@FXML
	private TableColumn<employee, String> positon_col;

	@FXML
	private TableColumn<employee, String> status_col;

	@FXML
	private TableColumn<employee, Integer> salary_col;
	@FXML
	private TableColumn<employee, String> gender_col;

	@FXML
	private TableColumn<employee, String> action_col;

	@FXML
	void AddEmployee(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getResource("../view/createEmployee.fxml"));
		Stage stage = new Stage();
		stage.setTitle("");
		Image icon = new Image("./com/hrm/assets/logo/logo.jpg");
		stage.getIcons().add(icon);
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML
	void Print(ActionEvent event) throws IOException {
		Workbook workbook = new HSSFWorkbook();
		Sheet spreadsheet = workbook.createSheet("sample");
		int rowNum = 0;
		Row firstRow = spreadsheet.createRow(rowNum++);
		Row row = spreadsheet.createRow(0);
		Cell firstCell = firstRow.createCell(0);
		firstCell.setCellValue("List of Customer");
		for (int j = 1; j < table_employee.getColumns().size(); j++) {
			row.createCell(j).setCellValue(table_employee.getColumns().get(j).getText());
		}

		for (int i = 0; i < table_employee.getItems().size(); i++) {
			row = spreadsheet.createRow(i + 1);
			for (int j = 0; j < table_employee.getColumns().size(); j++) {
				if (table_employee.getColumns().get(j).getCellData(i) != null) {
					row.createCell(j).setCellValue(table_employee.getColumns().get(j).getCellData(i).toString());
				} else {
					row.createCell(j).setCellValue("");
				}
			}
		}

		FileOutputStream fileOut = new FileOutputStream("Employee" + String.valueOf(LocalDate.now()) + ".xls");
		workbook.write(fileOut);
		fileOut.close();

	}

	@FXML
	void Refresh(ActionEvent event) throws SQLException {
		clean();
	}

	@FXML
	void SearchEmployee(ActionEvent event) {

	}

	public void loadTable() throws SQLException {
		refreshTable();

		filteredData = new FilteredList<>(emloyee_table_obList, p -> true);
		// Search
		search_field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Employee -> Employee.getFirst_name().toLowerCase().contains(
					newValue.toLowerCase()) || Employee.getMiddle_name().toLowerCase().contains(newValue.toLowerCase())
					|| Employee.getLast_name().toLowerCase().contains(newValue.toLowerCase())
					|| Employee.getDepartment().getDepartment_name().toLowerCase().contains(newValue.toLowerCase())
					|| Employee.getPosition().getPosition_name().toLowerCase().contains(newValue.toLowerCase()));
			changeTableView(0, emloyee_table_obList.size());
		});

		// id
		ID_col.setCellValueFactory(new PropertyValueFactory<>("row"));
		// fullname
		name_col.setCellValueFactory(cellData -> {
			String fullname = cellData.getValue().getLast_name() + " " + cellData.getValue().getMiddle_name() + " "
					+ cellData.getValue().getFirst_name();
			return new SimpleStringProperty(fullname);
		});
		// department
		department_col.setCellValueFactory(cellData -> {
			String department_name = cellData.getValue().getDepartment().getDepartment_name();
			return new SimpleStringProperty(department_name);
		});
		// position
		positon_col.setCellValueFactory(cellData -> {
			String position_name = cellData.getValue().getPosition().getPosition_name();
			return new SimpleStringProperty(position_name);
		});
		status_col.setCellValueFactory(cellData -> {
			int status = cellData.getValue().getStatus();
			String st = "";
			if (status == 0) {
				st = "Enable";
			} else {
				st = "Disable ";
			}
			return new SimpleStringProperty(st);
		});
		// dob
		DOB_col.setCellValueFactory(new Callback<CellDataFeatures<employee, Date>, ObservableValue<Date>>() {
			@Override
			public ObservableValue<Date> call(CellDataFeatures<employee, Date> cellDataFeatures) {
				return new SimpleObjectProperty<>(cellDataFeatures.getValue().getDob());
			}
		});

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DOB_col.setCellFactory(column -> new TableCell<employee, Date>() {
			@Override
			protected void updateItem(Date date, boolean empty) {
				super.updateItem(date, empty);
				if (empty || date == null) {
					setText(null);
				} else {
					setText(dateFormatter.format(date.toLocalDate()));
				}
			}
		});

		// salary
		salary_col.setCellValueFactory(cellData -> {
			Integer salary = cellData.getValue().getSalary().getValue_money();
			return new SimpleIntegerProperty(salary).asObject();
		});
		// Gender
		gender_col.setCellValueFactory(cellData -> {
			String gender = cellData.getValue().getGender();
			return new SimpleStringProperty(gender);
		});
		// Avatar
		avatar_col.setCellValueFactory(cellData -> {
			String avaString = cellData.getValue().getAvatar();
			return new SimpleStringProperty(avaString);
		});
		// color
		// gender callback
		Callback<TableColumn<employee, String>, TableCell<employee, String>> cellFoctory11 = (
				TableColumn<employee, String> param) -> {
			// make cell containing buttons
			final TableCell<employee, String> cell = new TableCell<employee, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					// that cell created only on non-empty rows
					if (empty) {
						setText(null);
						setGraphic(null);

					} else {

						setText(item);
						setStyle("-fx-font-weight: bold;");
						if (item.equals("Male")) {
							setText(null);
							Label Male = GlyphsDude.createIconLabel(FontAwesomeIcons.MALE, "", "25px", "25px",
									ContentDisplay.LEFT);
							HBox box = new HBox(Male);
							box.setStyle("-fx-alignment:center");
							Male.getStyleClass().add("update-label");
							setGraphic(box);

						} else {
							setText(null);
							Label Female = GlyphsDude.createIconLabel(FontAwesomeIcons.FEMALE, "", "25px", "25px",
									ContentDisplay.LEFT);
							HBox box = new HBox(Female);
							box.setStyle("-fx-alignment:center");
							Female.getStyleClass().add("delete-label");
							setGraphic(box);
						}
					}
				}
			};

			return cell;
		};
		gender_col.setCellFactory(cellFoctory11);
		// avatar call back

		Callback<TableColumn<employee, String>, TableCell<employee, String>> cellFoctory12 = (
				TableColumn<employee, String> param) -> {
			// make cell containing buttons
			final TableCell<employee, String> cell = new TableCell<employee, String>() {

				ImageView imageview = new ImageView();

				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					// that cell created only on non-empty rows
					if (item == null || item.equals("")) {
						setGraphic(null);
						setText(null);
//						setText("no data");
//						HBox box = new HBox();
//						box.setStyle("-fx-alignment:center");
//						Image image = new Image("./com/hrm/assets/avatar/avatarnul.png");
//						imageview.setFitHeight(50);
//						imageview.setFitWidth(50);
//						imageview.setImage(image);
//
//						box.getChildren().addAll(imageview);
//						// SETTING ALL THE GRAPHICS COMPONENT FOR CELL
//						setGraphic(box);

					} else {
						HBox box = new HBox();
						box.setStyle("-fx-alignment:center");
						Image image = new Image(item);
						imageview.setFitHeight(50);
						imageview.setFitWidth(42);
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

		Callback<TableColumn<employee, String>, TableCell<employee, String>> cellFoctory1 = (
				TableColumn<employee, String> param) -> {
			// make cell containing buttons
			final TableCell<employee, String> cell = new TableCell<employee, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					// that cell created only on non-empty rows
					if (empty) {
						setGraphic(null);
						setText(null);

					} else {

						setText(item);
						setStyle("-fx-font-weight: bold;");
						if (item.equals("Enable")) {
							setTextFill(Color.GREEN);

						} else {
							setTextFill(Color.CHOCOLATE);
						}
					}
				}
			};

			return cell;
		};
		status_col.setCellFactory(cellFoctory1);
		//
		// action
		Callback<TableColumn<employee, String>, TableCell<employee, String>> cellFoctory = (
				TableColumn<employee, String> param) -> {
			// make cell containing buttons
			final TableCell<employee, String> cell = new TableCell<employee, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					// that cell created only on non-empty rows
					if (empty) {
						setGraphic(null);
						setText(null);

					} else {

						Label eyeIcon = GlyphsDude.createIconLabel(FontAwesomeIcons.EYE, "", "25px", "25px",
								ContentDisplay.LEFT);

						Label deleteIcon = GlyphsDude.createIconLabel(FontAwesomeIcons.TRASH, "", "25px", "25px",
								ContentDisplay.LEFT);

						Label editIcon = GlyphsDude.createIconLabel(FontAwesomeIcons.PENCIL_SQUARE, "", "25px", "25px",
								ContentDisplay.LEFT);
						deleteIcon.getStyleClass().add("delete-label");
						editIcon.getStyleClass().add("update-label");
						eyeIcon.getStyleClass().add("eye-label");

						// delete event
						deleteIcon.setOnMouseClicked((MouseEvent event) -> {
							// Alert delete
							Alert alert1 = new Alert(AlertType.CONFIRMATION);
							alert1.setTitle("Delete File");
							alert1.setHeaderText("Are you sure want to move this file to the Recycle Bin?");

							// option != null.
							Optional<ButtonType> option = alert1.showAndWait();

							if (option.get() == ButtonType.OK) {
								employee employee = table_employee.getSelectionModel().getSelectedItem();
								boolean checkDelete = true;
								bo_employee Bo_Em = new bo_employee();
								checkDelete = Bo_Em.delete(employee);
								if (checkDelete == true) {
									alert.Success("Delete employee " + employee.getId() + " ");
									try {
										clean();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						});
						eyeIcon.setOnMouseClicked((MouseEvent event) -> {
							employee employee = table_employee.getSelectionModel().getSelectedItem();
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(getClass().getResource("../view/profileshow.fxml"));
							try {
								loader.load();
							} catch (IOException ex) {
								Logger.getLogger(profileshow_controller.class.getName()).log(Level.SEVERE, null, ex);
							}

							profileshow_controller Controller = loader.getController();
							Controller.setUpdate(true);

							Controller.getprofile(employee.getId());
							Parent parent = loader.getRoot();
							Stage stage = new Stage();
							stage.setScene(new Scene(parent));
							stage.initStyle(StageStyle.UTILITY);
							stage.show();

						});

						// update event

						editIcon.setOnMouseClicked((MouseEvent event) -> {
							employee employee = table_employee.getSelectionModel().getSelectedItem();
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(getClass().getResource("../view/editEmployee.fxml"));
							try {
								loader.load();
							} catch (IOException ex) {
								Logger.getLogger(editEmployee_controller.class.getName()).log(Level.SEVERE, null, ex);
							}

//                          
							editEmployee_controller Controller = loader.getController();
							Controller.setUpdate(true);

							Controller.setTextField(dataDAO.getEmployeeID(employee.getId()));
							Parent parent = loader.getRoot();
							Stage stage = new Stage();
							stage.setScene(new Scene(parent));
							stage.initStyle(StageStyle.UTILITY);
							stage.show();

						});
						HBox managebtn = new HBox(eyeIcon, editIcon, deleteIcon);
						managebtn.setStyle("-fx-alignment:center");
						HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
						HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
						HBox.setMargin(deleteIcon, new Insets(2, 3, 2, 2));

						setGraphic(managebtn);

						setText(null);

					}
				}

			};

			return cell;
		};

		action_col.setCellFactory(cellFoctory);
		// set page in table view
		int totalPage = (int) (Math.ceil(emloyee_table_obList.size() * 1.0 / ROWS_PER_PAGE));
		pagination.setCurrentPageIndex(0);
		pagination.setPageCount(totalPage);
		changeTableView(0, ROWS_PER_PAGE);
		pagination.currentPageIndexProperty()
				.addListener((observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));
	}

	// change table view Method
	private void changeTableView(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, emloyee_table_obList.size());
		int minIndex = Math.min(toIndex, filteredData.size());
		SortedList<employee> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_employee.comparatorProperty());
		table_employee.setItems(sortedData);
	}

	public void clean() throws SQLException {
		loadTable();
		search_field.setText("");
	}

	public void refreshTable() throws SQLException {
		emloyee_table_obList.clear();
		ArrayList<employee> listEmployee = bo_employee.getListEmployee();
		for (employee employee : listEmployee) {
			emloyee_table_obList.add(employee);
			table_employee.setItems(emloyee_table_obList);
		}
	}
}