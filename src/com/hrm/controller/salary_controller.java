package com.hrm.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.Normalizer.Form;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.management.loading.PrivateClassLoader;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hrm.assets.lib.alert;
import com.hrm.assets.lib.check;
import com.hrm.model.beans.department;
import com.hrm.model.beans.employee;
import com.hrm.model.beans.employee_search;
import com.hrm.model.beans.principal;
import com.hrm.model.beans.salary;
import com.hrm.model.business_objects.bo_department;
import com.hrm.model.business_objects.bo_principal;
import com.hrm.model.business_objects.bo_salary;
import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class salary_controller implements Initializable {

	public salary_controller() {
		// TODO Auto-generated constructor stub
	}

	@FXML
	private Label status_label;

	@FXML
	private Label money_label;

	@FXML
	private Label reward_label;

	@FXML
	private Label time_label;

	@FXML
	private Label word_label;

	@FXML
	private Label emid_label;

	@FXML
	private Label create_label;
	private bo_salary dataDAO = new bo_salary();
	private bo_department dataDepartment = new bo_department();
	private static int ROWS_PER_PAGE = 8;
	private ObservableList<salary> masterData = FXCollections.observableArrayList();
	private ObservableList<salary> searchData = FXCollections.observableArrayList();
	private ObservableList<department> depaList = FXCollections.observableArrayList();
	static int derpartment_id;
	private FilteredList<salary> filteredData;

	@FXML
	private TableView<salary> table_salary;

	@FXML
	private TableColumn<salary, Integer> ID_col;

	@FXML
	private TableColumn<salary, String> name_col;

	@FXML
	private TableColumn<salary, String> money_col;

	@FXML
	private TableColumn<salary, String> reward_col;

	@FXML
	private TableColumn<salary, Integer> work_col;

	@FXML
	private TableColumn<salary, DatePicker> timepay_col;

	@FXML
	private TableColumn<salary, String> whopay_col;

	@FXML
	private TableColumn<salary, DatePicker> createat_col;

	@FXML
	private TableColumn<salary, String> descip_col;

	@FXML
	private TableColumn<salary, String> action_col;

	@FXML
	private Button svae_btn;

	@FXML
	private Button print_btn;

	@FXML
	private Button refresh_btn;

	@FXML
	private Pagination pagiaton;

	@FXML
	private TextArea descip_field;

	@FXML
	private TextField work_field;

	@FXML
	private TextField whopay_field;

	@FXML
	private TextField reward_field;

	@FXML
	private TextField money_field;

	@FXML
	private TextField name_field;

	@FXML
	private DatePicker timepay_fileld;

	@FXML
	private DatePicker createat_field;

	@FXML
	private Button search_btn;
	@FXML
	private ComboBox<department> derpartment_box;
	@FXML
	private ComboBox<String> status;
	@FXML
	private TextField ID_field;

	@FXML
	private TextField search_field;

	@FXML
	void Print(ActionEvent event) throws IOException {
		System.out.println("Create file excel");
		Workbook workbook = new HSSFWorkbook();
		Sheet spreadsheet = workbook.createSheet("sample");
		int rowNum = 0;
		Row firstRow = spreadsheet.createRow(rowNum++);
		Row row = spreadsheet.createRow(0);
		Cell firstCell = firstRow.createCell(0);
		firstCell.setCellValue("List of Customer");
		for (int j = 2; j < table_salary.getColumns().size(); j++) {
			row.createCell(j).setCellValue(table_salary.getColumns().get(j).getText());
		}

		for (int i = 2; i < table_salary.getItems().size(); i++) {
			row = spreadsheet.createRow(i + 1);
			for (int j = 0; j < table_salary.getColumns().size(); j++) {
				if (table_salary.getColumns().get(j).getCellData(i) != null) {
					row.createCell(j).setCellValue(table_salary.getColumns().get(j).getCellData(i).toString());
				} else {
					row.createCell(j).setCellValue("");
				}
			}
		}

		FileOutputStream fileOut = new FileOutputStream("Salary" + String.valueOf(LocalDate.now()) + "l.xls");
		workbook.write(fileOut);
		fileOut.close();

		alert.Success("Create file excel");
	}

	@FXML
	void Refresh(ActionEvent event) {
		clean();
	}

	@FXML
	void Save(ActionEvent event) {
		if (ID_field == null) {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Save File");
			alert1.setHeaderText("Are you sure Save .");
			// option != null.
			boolean checkSave = false;
			Optional<ButtonType> option = alert1.showAndWait();

			if (option.get() == ButtonType.OK) {

				salary Salary = new salary();
				if (check.checknull(status.getValue()) && check.checkFnumber(money_field.getText())
						&& check.checkFnumber(reward_field.getText()) && check.checkFnumber(name_field.getText())
						&& check.checkFnumber(work_field.getText())
						&& check.checkdate(String.valueOf(timepay_fileld.getValue()))
						&& check.checkdate(String.valueOf(createat_field.getValue()))) {
					Salary.setEmployee_id(Integer.parseInt(name_field.getText()));
					Salary.setWho_pay(whopay_field.getText());
					Salary.setCreated_at(Date.valueOf(createat_field.getValue()));
					Salary.setTime_to_pay(Date.valueOf(timepay_fileld.getValue()));
					Salary.setDescription(descip_field.getText());
					Salary.setValue_money(Integer.parseInt(money_field.getText()));
					Salary.setValue_money_reward(Integer.parseInt(reward_field.getText()));
					Salary.setWorking_day(Integer.parseInt(work_field.getText()));
					Salary.setStatus(status.getValue());
					checkSave = dataDAO.save(Salary);
				}
				if (checkSave == true) {
					alert.Success("Add Salary ");
					clean();
				} else {
					alert.Error();
					if (check.checknull(status.getValue()) == false) {
						status_label.setText(check.Errorbox(status.getValue()));
						status_label.setVisible(true);
					} else {
						status_label.setVisible(false);
					}
					if (check.checkFnumber(money_field.getText()) == false) {
						money_label.setText(check.ErrorNumber(money_field.getText()));
						money_label.setVisible(true);
					} else {
						money_label.setVisible(false);
					}
					if (check.checkFnumber(name_field.getText()) == false) {
						emid_label.setText(check.ErrorNumber(name_field.getText()));
						emid_label.setVisible(true);
					} else {
						emid_label.setVisible(false);
					}
					if (check.checkFnumber(work_field.getText()) == false) {
						word_label.setText(check.ErrorNumber(work_field.getText()));
						word_label.setVisible(true);
					} else {
						word_label.setVisible(false);
					}
					if (check.checkFnumber(reward_field.getText()) == false) {
						reward_label.setText(check.ErrorNumber(reward_field.getText()));
						reward_label.setVisible(true);
					} else {
						reward_label.setVisible(false);
					}
					if (check.isDateValid(String.valueOf(timepay_fileld.getValue())) == false) {
						time_label.setText(check.ErrorDate(String.valueOf(timepay_fileld.getValue())));
						time_label.setVisible(true);
					} else {
						time_label.setVisible(false);
					}
					if (check.isDateValid(String.valueOf(createat_field.getValue())) == false) {
						create_label.setText(check.ErrorDate(String.valueOf(createat_field.getValue())));
						create_label.setVisible(true);
					} else {
						create_label.setVisible(false);
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
				salary Salary = new salary();
				if (check.checknull(status.getValue()) && check.checkFnumber(money_field.getText())
						&& check.checkFnumber(reward_field.getText()) && check.checkFnumber(name_field.getText())
						&& check.checkFnumber(work_field.getText())
						&& check.checkdate(String.valueOf(timepay_fileld.getValue()))
						&& check.checkdate(String.valueOf(createat_field.getValue()))) {
					Salary.setEmployee_id(Integer.parseInt(name_field.getText()));
					Salary.setWho_pay(whopay_field.getText());
					Salary.setCreated_at(Date.valueOf(createat_field.getValue()));
					Salary.setTime_to_pay(Date.valueOf(timepay_fileld.getValue()));
					Salary.setDescription(descip_field.getText());
					Salary.setValue_money(Integer.parseInt(money_field.getText()));
					Salary.setValue_money_reward(Integer.parseInt(reward_field.getText()));
					Salary.setWorking_day(Integer.parseInt(work_field.getText()));
					Salary.setId(Integer.parseInt(ID_field.getText()));

					checkSave = dataDAO.update(Salary);
				}
				if (checkSave == true) {
					alert.Success("Update Salary ");
					clean();
				} else {
					alert.Error();
					if (check.checknull(status.getValue()) == false) {
						status_label.setText(check.Errorbox(status.getValue()));
						status_label.setVisible(true);
					} else {
						status_label.setVisible(false);
					}
					if (check.checkFnumber(money_field.getText()) == false) {
						money_label.setText(check.ErrorNumber(money_field.getText()));
						money_label.setVisible(true);
					} else {
						money_label.setVisible(false);
					}
					if (check.checkFnumber(name_field.getText()) == false) {
						emid_label.setText(check.ErrorNumber(name_field.getText()));
						emid_label.setVisible(true);
					} else {
						emid_label.setVisible(false);
					}
					if (check.checkFnumber(work_field.getText()) == false) {
						word_label.setText(check.ErrorNumber(work_field.getText()));
						word_label.setVisible(true);
					} else {
						word_label.setVisible(false);
					}
					if (check.checkFnumber(reward_field.getText()) == false) {
						reward_label.setText(check.ErrorNumber(reward_field.getText()));
						reward_label.setVisible(true);
					} else {
						reward_label.setVisible(false);
					}
					if (check.isDateValid(String.valueOf(timepay_fileld.getValue())) == false) {
						time_label.setText(check.ErrorDate(String.valueOf(timepay_fileld.getValue())));
						time_label.setVisible(true);
					} else {
						time_label.setVisible(false);
					}
					if (check.isDateValid(String.valueOf(createat_field.getValue())) == false) {
						create_label.setText(check.ErrorDate(String.valueOf(createat_field.getValue())));
						create_label.setVisible(true);
					} else {
						create_label.setVisible(false);
					}
				}
			}
		}
	}

	@FXML
	void SearchDepartment(ActionEvent event) {
		System.out.print(derpartment_id);
		searchData = dataDAO.getDerpartment(derpartment_id);
		filteredData = new FilteredList<>(searchData, p -> true);
		// Search
		search_field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Salary -> Salary.getEmployee().toLowerCase().contains(newValue.toLowerCase())
					|| Salary.getWho_pay().toLowerCase().contains(newValue.toLowerCase()));
			changeTableView1(0, searchData.size());

		});
		// add value into cell

		ID_col.setCellValueFactory(new PropertyValueFactory<>("id"));
		name_col.setCellValueFactory(new PropertyValueFactory<>("employee"));
		money_col.setCellValueFactory(new PropertyValueFactory<>("moneyString"));
		reward_col.setCellValueFactory(new PropertyValueFactory<>("rewardString"));
		work_col.setCellValueFactory(new PropertyValueFactory<>("working_day"));
		timepay_col.setCellValueFactory(new PropertyValueFactory<>("time_to_pay"));
		whopay_col.setCellValueFactory(new PropertyValueFactory<>("who_pay"));
		createat_col.setCellValueFactory(new PropertyValueFactory<>("created_at"));
		descip_col.setCellValueFactory(new PropertyValueFactory<>("description"));
		Callback<TableColumn<salary, String>, TableCell<salary, String>> cellFoctory = (
				TableColumn<salary, String> param) -> {
			// make cell containing buttons
			final TableCell<salary, String> cell = new TableCell<salary, String>() {
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

						Label eyeIcon = GlyphsDude.createIconLabel(FontAwesomeIcons.EYE, "", "25px", "25px",
								ContentDisplay.LEFT);
						deleteIcon.getStyleClass().add("delete-label");
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
								salary Salary = table_salary.getSelectionModel().getSelectedItem();
								boolean checkDelete = dataDAO.delete(Salary);

								if (checkDelete == true) {
									alert.Success("Delete Salary at  " + Salary.getId() + " ");
									clean();
								}
							}

						});

						// show event

						eyeIcon.setOnMouseClicked((MouseEvent event) -> {

							salary salary = table_salary.getSelectionModel().getSelectedItem();
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(getClass().getResource("../view/showsalary.fxml"));
							try {
								loader.load();
							} catch (IOException ex) {
								Logger.getLogger(showsalary_controller.class.getName()).log(Level.SEVERE, null, ex);
							}

							showsalary_controller Controller = loader.getController();
							Controller.setUpdate(true);

							Controller.getprofile(salary.getEmployee_id());
							Parent parent = loader.getRoot();
							Stage stage = new Stage();
							stage.setScene(new Scene(parent));
							stage.initStyle(StageStyle.UTILITY);
							stage.show();
//							salary Salary = table_salary.getSelectionModel().getSelectedItem();
//
//							int index$ = Salary.getMoneyString().indexOf("$");
//							int index$1 = Salary.getRewardString().indexOf("$");
//
//							ID_field.setText(String.valueOf(Salary.getId()));
//							name_field.setText(String.valueOf(Salary.getEmployee_id()));
//							descip_field.setText(Salary.getDescription());
//							work_field.setText(String.valueOf(Salary.getWorking_day()));
//							timepay_fileld.setValue(formatDate(String.valueOf(Salary.getTime_to_pay())));
//							createat_field.setValue(formatDate(String.valueOf(Salary.getCreated_at())));
//							money_field.setText(Salary.getMoneyString().substring(0, index$));
//							reward_field.setText(Salary.getRewardString().substring(0, index$1));
//							whopay_field.setText(Salary.getWho_pay());

						});
						HBox managebtn = new HBox(eyeIcon, deleteIcon);
						managebtn.setStyle("-fx-alignment:center");
						HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
						HBox.setMargin(eyeIcon, new Insets(2, 3, 0, 2));

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
		pagiaton.setCurrentPageIndex(0);
		pagiaton.setPageCount(totalPage);
		changeTableView(0, ROWS_PER_PAGE);
		pagiaton.currentPageIndexProperty()
				.addListener((observable, oldValue, newValue) -> changeTableView1(newValue.intValue(), ROWS_PER_PAGE));

	}

	@FXML
	void SearchEmployee(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getResource("../view/SearchEMploy.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Search employee.");
		Image icon = new Image("./com/hrm/assets/logo/logo.jpg");
		stage.getIcons().add(icon);
		stage.setScene(new Scene(root, 512, 472));
		stage.show();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO Auto-generated method stub
		getList();
		derpartment_box.setItems(depaList);
		status.setItems(FXCollections.observableArrayList("New", "Update"));
		//
		derpartment_box.setConverter(new StringConverter<department>() {
			@Override
			public String toString(department object) {
				return object.getDepartment_name();
			}

			@Override
			public department fromString(String string) {
				return null;
			}
		});

		derpartment_box.valueProperty().addListener((obs, oldVal, newVal) -> {
			int selectionText = newVal.getDepartment_Id();
			derpartment_id = selectionText;
		});
		// set color table salary
		table_salary.setRowFactory(tv -> new TableRow<salary>() {
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
		timepay_fileld.setConverter(converter);

		InserTableView();
	}

	public void clean() {
		reward_label.setVisible(false);
		word_label.setVisible(false);
		money_label.setVisible(false);
		create_label.setVisible(false);
		time_label.setVisible(false);
		status_label.setVisible(false);
		emid_label.setVisible(false);
		search_field.setText("");
		descip_field.setText(" ");
		whopay_field.setText("");
		reward_field.setText("");
		timepay_fileld.setValue(LocalDate.now());
		money_field.setText("");
		name_field.setText("");
		createat_field.setValue(LocalDate.now());
		work_field.setText("");
		createat_field.setValue(LocalDate.now());
		getList();
		InserTableView();

	}

	public void InserTableView() {
		filteredData = new FilteredList<>(masterData, p -> true);
		// Search
		search_field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Salary -> Salary.getEmployee().toLowerCase().contains(newValue.toLowerCase())
					|| Salary.getWho_pay().toLowerCase().contains(newValue.toLowerCase()));
			changeTableView(0, masterData.size());
			changeTableView(0, masterData.size());
		});
		// add value into cell

		ID_col.setCellValueFactory(new PropertyValueFactory<>("row"));
		name_col.setCellValueFactory(new PropertyValueFactory<>("employee"));
		money_col.setCellValueFactory(new PropertyValueFactory<>("moneyString"));
		reward_col.setCellValueFactory(new PropertyValueFactory<>("rewardString"));
		work_col.setCellValueFactory(new PropertyValueFactory<>("working_day"));
		timepay_col.setCellValueFactory(new PropertyValueFactory<>("time_to_pay"));
		whopay_col.setCellValueFactory(new PropertyValueFactory<>("who_pay"));
		createat_col.setCellValueFactory(new PropertyValueFactory<>("created_at"));
		descip_col.setCellValueFactory(new PropertyValueFactory<>("description"));

		// add cell of button edit

		Callback<TableColumn<salary, String>, TableCell<salary, String>> cellFoctory = (
				TableColumn<salary, String> param) -> {
			// make cell containing buttons
			final TableCell<salary, String> cell = new TableCell<salary, String>() {
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

						Label eyeicon = GlyphsDude.createIconLabel(FontAwesomeIcons.EYE, "", "25px", "25px",
								ContentDisplay.LEFT);
						deleteIcon.getStyleClass().add("delete-label");
						eyeicon.getStyleClass().add("eye-label");
						// delete event
						deleteIcon.setOnMouseClicked((MouseEvent event) -> {
							// Alert delete
							Alert alert1 = new Alert(AlertType.CONFIRMATION);
							alert1.setTitle("Delete File");
							alert1.setHeaderText("Are you sure want to move this file to the Recycle Bin?");

							// option != null.
							Optional<ButtonType> option = alert1.showAndWait();

							if (option.get() == ButtonType.OK) {
								salary Salary = table_salary.getSelectionModel().getSelectedItem();
								boolean checkDelete = dataDAO.delete(Salary);

								if (checkDelete == true) {
									alert.Success("Delete Salary at  " + Salary.getId() + " ");
									clean();
								}
							}

						});

						// update event

						eyeicon.setOnMouseClicked((MouseEvent event) -> {

							salary salary = table_salary.getSelectionModel().getSelectedItem();
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(getClass().getResource("../view/showsalary.fxml"));
							try {
								loader.load();
							} catch (IOException ex) {
								Logger.getLogger(showsalary_controller.class.getName()).log(Level.SEVERE, null, ex);
							}

							showsalary_controller Controller = loader.getController();
							Controller.setUpdate(true);

							Controller.getprofile(salary.getEmployee_id());
							Parent parent = loader.getRoot();
							Stage stage = new Stage();
							stage.setScene(new Scene(parent));
							stage.initStyle(StageStyle.UTILITY);
							stage.show();

						});
						HBox managebtn = new HBox(eyeicon, deleteIcon);
						managebtn.setStyle("-fx-alignment:center");
						HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
						HBox.setMargin(eyeicon, new Insets(2, 3, 0, 2));

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
		pagiaton.setCurrentPageIndex(0);
		pagiaton.setPageCount(totalPage);
		changeTableView(0, ROWS_PER_PAGE);
		pagiaton.currentPageIndexProperty()
				.addListener((observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));
	}

	public void getList() {
		masterData = dataDAO.getAll();
		depaList = dataDepartment.getAll();
	}

	// change table view Method
	private void changeTableView(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, masterData.size());
		int minIndex = Math.min(toIndex, filteredData.size());
		SortedList<salary> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_salary.comparatorProperty());
		table_salary.setItems(sortedData);
	}

	private void changeTableView1(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, searchData.size());
		int minIndex = Math.min(toIndex, filteredData.size());
		SortedList<salary> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_salary.comparatorProperty());
		table_salary.setItems(sortedData);
	}

	private LocalDate formatDate(String string) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(string, formatter);
		return localDate;
	}

}