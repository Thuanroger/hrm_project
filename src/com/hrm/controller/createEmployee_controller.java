package com.hrm.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;

import com.hrm.assets.lib.alert;
import com.hrm.assets.lib.check;
import com.hrm.assets.lib.path_image;
import com.hrm.model.beans.department;
import com.hrm.model.beans.employee;
import com.hrm.model.beans.module;
import com.hrm.model.beans.principal;
import com.hrm.model.beans.role;
import com.hrm.model.business_objects.bo_department;
import com.hrm.model.business_objects.bo_employee;
import com.hrm.model.business_objects.bo_roles;
import com.mysql.cj.protocol.a.result.ResultsetRowsStatic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

public class createEmployee_controller implements Initializable {

	public createEmployee_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// add toottip
		Tooltip passTooltip = new Tooltip("Password-Minimum 8 digits\n" + "* Atleast 1 upper case letters (A - Z)\n"
				+ "* Atleast 1 Lower case letters (a - z)\n" + "* Atleast 1 number (0-9)\n"
				+ "* Atleast 1 non-alphanumeric symbol (e.g. @Z$%£!')");
		password.setTooltip(passTooltip);

		Tooltip userTooltip = new Tooltip("Username must be between\n3 and 16 characters");
		user_name.setTooltip(userTooltip);
		// add image null
		ImageView imgImageView1 = new ImageView();

		// demo addimage
		Image image1 = new Image("./com/hrm/assets/avatar/avatarnul.png");
		imgImageView1.setImage(image1);
		imgImageView1.setFitWidth(150);
		imgImageView1.setFitHeight(150);
		imgImageView1.scaleXProperty();
		imgImageView1.scaleYProperty();
		imgImageView1.setSmooth(true);
		imgImageView1.setCache(true);
		image_pane.setCenter(imgImageView1);
		getList();
		role.setConverter(new StringConverter<role>() {
			@Override
			public String toString(role object) {
				return object.getRole_name();
			}

			@Override
			public role fromString(String string) {
				return null;
			}
		});
		department_file.setConverter(new StringConverter<department>() {
			@Override
			public String toString(department object) {
				return object.getDepartment_name();
			}

			@Override
			public department fromString(String string) {
				return null;
			}
		});

		// add data combo box module and role
		role.setItems(roleList);
		department_file.setItems(departmentList);
		status_box.setItems(statuslList);
		gender_field.setItems(genderList);
		role.valueProperty().addListener((obs, oldVal, newVal) -> {
			int selectionText = newVal.getId();
			role_id = selectionText;
		});
		department_file.valueProperty().addListener((obs, oldVal, newVal) -> {
			int selectionText = newVal.getDepartment_Id();
			depart_id = selectionText;
		});
		status_box.valueProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal.equals("disable")) {
				status_id = 1;
			} else {
				status_id = 0;
			}
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
		DOB.setConverter(converter);
		create_at.setConverter(converter);
//			createat_field.setConverter(converter);

	}

	static String imagepath;
	static File imagefileString;
	static int role_id = 0;
	static int depart_id = 0;
	static int status_id = 0;
	private ObservableList<department> departmentList = FXCollections.observableArrayList();
	private ObservableList<role> roleList = FXCollections.observableArrayList();
	private ObservableList<String> statuslList = FXCollections.observableArrayList("disable ", "enable");
	private ObservableList<String> genderList = FXCollections.observableArrayList("Male ", "Female");
	private ObservableList<employee> employees = FXCollections.observableArrayList();
	private bo_department getDepartment = new bo_department();
	private bo_roles getRole = new bo_roles();
	private bo_employee getEmployee = new bo_employee();
	@FXML
	private Button save_btn;

	@FXML
	private Button refresh_btn;

	@FXML
	private Label Title;
	@FXML
	private Label middlename_label;

	@FXML
	private Label lastname_label;

	@FXML
	private Label firstname_label;

	@FXML
	private Label gender_label;

	@FXML
	private Label DOB_label;

	@FXML
	private Label phone_label;

	@FXML
	private Label gmail_label;

	@FXML
	private Label address_label;

	@FXML
	private Label username_label;

	@FXML
	private Label pass_label;

	@FXML
	private Label role_label;

	@FXML
	private Label depart_label;

	@FXML
	private Label onleave_label;

	@FXML
	private Label hiredate_label;

	@FXML
	private Label status_label;

	@FXML
	private Button add_avatar_btn;

	@FXML
	private ImageView avatar;

	@FXML
	private TextField middle_name;

	@FXML
	private TextField last_name;

	@FXML
	private TextField first_name;

	@FXML
	private DatePicker DOB;

	@FXML
	private ComboBox<department> department_file;

	@FXML
	private TextField gmail;

	@FXML
	private TextArea descip_field;

	@FXML
	private ComboBox<String> status_box;
	@FXML
	private ComboBox<String> gender_field;

	@FXML
	private ComboBox<role> role;

	@FXML
	private TextField phone;

	@FXML
	private TextField user_name;

	@FXML
	private TextArea address_field;

	@FXML
	private DatePicker create_at;

	@FXML
	private TextField on_leave;

	@FXML
	private TextField password;
	@FXML
	private BorderPane image_pane;

	@FXML
	void AddAvatar(ActionEvent event) throws IOException {
		ImageView imgImageView = new ImageView();
		FileChooser fileChooser = new FileChooser();
		// Set extension filter
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
		FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
		FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);
		imagefileString = file;

		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);

			imgImageView.setImage(image);
			imgImageView.setFitWidth(150);
			imgImageView.setFitHeight(150);
			imgImageView.scaleXProperty();
			imgImageView.scaleYProperty();
			imgImageView.setSmooth(true);
			imgImageView.setCache(true);
			image_pane.setCenter(imgImageView);
			FileInputStream fin = new FileInputStream(file);
			System.out.println(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];

			for (int readNum; (readNum = fin.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
//            person_image = bos.toByteArray();
		} catch (IOException ex) {
			Logger.getLogger("ss");
		}

	}

	@FXML
	void Refresh(ActionEvent event) {
		clean();

	}

	public void clean() {
		first_name.setText("");
		last_name.setText("");
		middle_name.setText("");
		DOB.setValue(LocalDate.now());
//		department_file.setValue(null);
		gmail.setText("");
		descip_field.setText("");
//		status_box.setValue(null);
//		role.setValue(null);
		phone.setText("");
		user_name.setText("");
		address_field.setText("");
		create_at.setValue(LocalDate.now());
		on_leave.setText("");
		password.setText("");
		ImageView imgImageView1 = new ImageView();
		// demo addimage
		Image image1 = new Image("./com/hrm/assets/avatar/avatarnul.png");
		imgImageView1.setImage(image1);
		imgImageView1.setFitWidth(150);
		imgImageView1.setFitHeight(150);
		imgImageView1.scaleXProperty();
		imgImageView1.scaleYProperty();
		imgImageView1.setSmooth(true);
		imgImageView1.setCache(true);
		image_pane.setCenter(imgImageView1);
		getList();

	}

	public void getList() {
		roleList = getRole.getAll();
		departmentList = getDepartment.getAll();

	}

	@FXML
	void SaveEmployee(ActionEvent event) throws IOException {

		// option != null.
		Alert alert1 = new Alert(AlertType.CONFIRMATION);
		alert1.setTitle("Save File");
		alert1.setHeaderText("Are you sure Save " + first_name.getText() + ".");

		// option != null.
		Optional<ButtonType> option = alert1.showAndWait();
		boolean checkSave = false;
		if (option.get() == ButtonType.OK) {
			System.out.print(imagefileString);
			// save image
			String path = path_image.pathString();
			if (imagefileString != null) {
				Files.copy(imagefileString.toPath(), (new File(path + imagefileString.getName())).toPath(),
						StandardCopyOption.REPLACE_EXISTING);
				imagepath = "./com/hrm/assets/avatar/" + imagefileString.getName();
			}

			employee EM = new employee();
			if (check.checkFtring(first_name.getText()) && check.checkFtring(middle_name.getText())
					&& check.checkFtring(last_name.getText()) && check.checkdate(String.valueOf(DOB.getValue()))
					&& check.checkFnumber(phone.getText()) && check.checkFgmail(gmail.getText())
					&& !address_field.getText().equals("") && check.checkusername(user_name.getText())
					&& check.checkFpass(password.getText()) && check.checknull(gender_field.getValue())
					&& depart_id != 0 && role_id != 0 && check.checkdate(String.valueOf(create_at.getValue()))) {
				EM.setAvatar(imagepath);
				EM.setFirst_name(first_name.getText());
				EM.setGender(gender_field.getValue());
				EM.setLast_name(last_name.getText());
				EM.setMiddle_name(middle_name.getText());
				EM.setDob(Date.valueOf(DOB.getValue()));
				EM.setDepartment_id(depart_id);
				EM.setRole_id(role_id);
				EM.setStatus(status_id);
				EM.setEmail(gmail.getText());
				EM.setAddress(address_field.getText());
				EM.setDescription(descip_field.getText());
				EM.setHire_date(Date.valueOf(create_at.getValue()));
				if (!on_leave.getText().equals("")) {
					EM.setOn_leave(Integer.parseInt(on_leave.getText()));
				}
				EM.setUsername(user_name.getText());
				EM.setPassword(password.getText());
				EM.setTelephone(phone.getText());

				checkSave = getEmployee.save(EM);
			}
			if (checkSave == true) {
				alert.Success("Add Employee ");
				clean();
			} else {
				alert.Error();
				if (check.checkFtring(first_name.getText()) == false) {
					firstname_label.setText(check.ErrorString(first_name.getText()));
					firstname_label.setVisible(true);
				} else {
					firstname_label.setVisible(false);
				}
				if (check.checkFtring(middle_name.getText()) == false) {
					middlename_label.setText(check.ErrorString(middle_name.getText()));
					middlename_label.setVisible(true);
				} else {
					middlename_label.setVisible(false);
				}
				if (check.checkFtring(last_name.getText()) == false) {
					lastname_label.setText(check.ErrorString(last_name.getText()));
					lastname_label.setVisible(true);
				} else {
					lastname_label.setVisible(false);
				}
				if (check.isDateValid(String.valueOf(DOB.getValue())) == false) {
					DOB_label.setText(check.ErrorDate(String.valueOf(DOB.getValue())));
//					dealine_field.setStyle(" -fx-border-color:#e11a1a");
					DOB_label.setVisible(true);
				} else {
					DOB_label.setVisible(false);
				}
				if (check.checkFnumber(phone.getText()) == false) {
					phone_label.setText(check.ErrorNumber(phone.getText()));
					phone_label.setVisible(true);
				} else {
					phone_label.setVisible(false);
				}
				if (check.checkFgmail(gmail.getText()) == false) {
					gmail_label.setText(check.ErrorEmail(gmail.getText()));
					gmail_label.setVisible(true);
				} else {
					gmail_label.setVisible(false);
				}
				if (address_field.getText().equals("")) {
					address_label.setText("Required");
					address_label.setVisible(true);
				} else {
					address_label.setVisible(false);
				}
				if (check.checkusername(user_name.getText()) == false) {
					username_label.setText(check.ErrorUser(user_name.getText()));
					username_label.setVisible(true);
				} else {
					username_label.setVisible(false);
				}
				if (check.checkFpass(password.getText()) == false) {
					pass_label.setText(check.ErrorPass(pass_label.getText()));
					pass_label.setVisible(true);
				} else {
					pass_label.setVisible(false);
				}
				if (check.checknull(gender_field.getValue()) == false) {
					gender_label.setVisible(true);
					gender_label.setText(check.Errorbox(gender_field.getValue()));
				} else {
					gender_label.setVisible(false);
				}
				if (check.checknull(status_box.getValue()) == false) {
					status_label.setVisible(true);
					status_label.setText(check.Errorbox(gender_field.getValue()));
				} else {
					gender_label.setVisible(false);
				}
				if (check.isDateValid(String.valueOf(create_at.getValue())) == false) {
					hiredate_label.setText(check.ErrorDate(String.valueOf(create_at.getValue())));
					hiredate_label.setVisible(true);
				} else {
					hiredate_label.setVisible(false);
				}
				if (depart_id == 0) {
					depart_label.setText("Required");
					depart_label.setVisible(true);
				} else {
					depart_label.setVisible(false);
				}
				if (role_id == 0) {
					role_label.setText("Required");
					role_label.setVisible(true);
				} else {
					role_label.setVisible(false);
				}
				if (!on_leave.getText().equals("")) {
					if (check.checkFnumber(on_leave.getText()) == false) {
						onleave_label.setText(check.ErrorNumber(on_leave.getText()));
						onleave_label.setVisible(true);
					} else {
						phone_label.setVisible(false);
					}
				}
			}

		}
	}
}