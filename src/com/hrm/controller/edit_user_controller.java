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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.hrm.assets.lib.VisiblePasswordFieldSkin;
import com.hrm.assets.lib.alert;
import com.hrm.assets.lib.check;
import com.hrm.assets.lib.path_image;
import com.hrm.model.usersession;
import com.hrm.model.beans.employee;
import com.hrm.model.business_objects.bo_employee;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class edit_user_controller implements Initializable {
	static int IDEM = 0;
	@FXML
	private Button save_btn;

	@FXML
	private Button refresh_btn;
	@FXML
	private Text text_pass;
	@FXML
	private Label Title;
	@FXML
	private Label pass_label;

	@FXML
	private Label newpass_label;

	@FXML
	private Label confirmpass_label;

	@FXML
	private Label firstname_label;

	@FXML
	private Label middlename_label;

	@FXML
	private Label lastname_label;

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
	private TextField gmail;

	@FXML
	private TextArea descip_field;

	@FXML
	private TextField phone;

	@FXML
	private TextField user_name;

	@FXML
	private TextArea address_field;

	@FXML
	private DatePicker hire_date;

	@FXML
	private TextField on_leave;

	@FXML
	private PasswordField new_pass;

	@FXML
	private Button save_pass_btn;

	@FXML
	private PasswordField old_pass;

	@FXML
	private PasswordField confirm_pass;
	@FXML
	private BorderPane image_pane;
	@FXML
	private Label Title1;
	private static String imagepath = "";
	private static File fileimae;
	private static String oldimageString = "";

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
		fileimae = file;
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

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

		} catch (IOException ex) {
			Logger.getLogger("ss");
		}

	}

	@FXML
	void Refresh(ActionEvent event) {
		clean();
	}

	@FXML
	void SaveEmployee(ActionEvent event) throws IOException {
		Alert alert1 = new Alert(AlertType.CONFIRMATION);
		alert1.setTitle("Update File");
		alert1.setHeaderText("Are you sure update profile?");
		// option != null.
		Optional<ButtonType> option = alert1.showAndWait();
		boolean checksave = false;
		if (option.get() == ButtonType.OK) {
			if (fileimae != null) {
				String path = path_image.pathString();

				Files.copy(fileimae.toPath(), (new File(path + fileimae.getName())).toPath(),
						StandardCopyOption.REPLACE_EXISTING);
				imagepath = "./com/hrm/assets/avatar/" + fileimae.getName();
			}
			bo_employee Bo_Em = new bo_employee();
			if (check.checkFtring(first_name.getText()) && check.checkFtring(middle_name.getText())
					&& check.checkFtring(last_name.getText()) && check.checkdate(String.valueOf(DOB.getValue()))
					&& check.checkFnumber(phone.getText()) && check.checkFgmail(gmail.getText())
					&& check.checkFuser(user_name.getText()) && !address_field.getText().equals("")) {
				employee EE = new employee();
				EE.setUsername(user_name.getText());
				EE.setFirst_name(first_name.getText());
				EE.setTelephone(phone.getText());
				EE.setEmail(gmail.getText());
				EE.setAddress((address_field.getText()));
				EE.setDob(Date.valueOf(DOB.getValue()));
				EE.setDescription(descip_field.getText());
				EE.setLast_name(last_name.getText());
				EE.setMiddle_name(middle_name.getText());
				if (imagepath == null || imagepath.equals("")) {
					EE.setAvatar(oldimageString);
				} else {
					EE.setAvatar(imagepath);
				}

				EE.setId(IDEM);

				checksave = Bo_Em.updateProfile(EE);
			}
			if (checksave == true) {
				alert.Success("Update");
				Stage stage = (Stage) save_btn.getScene().getWindow();
				stage.close();
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
				if (check.checkFnumber(phone.getText()) == false) {
					phone_label.setText(check.ErrorNumber(phone_label.getText()));
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

				if (check.isDateValid(String.valueOf(DOB.getValue())) == false) {
					DOB_label.setText(check.ErrorDate(String.valueOf(DOB.getValue())));
					DOB_label.setVisible(true);
				} else {
					DOB_label.setVisible(false);
				}
				if (check.checkusername(user_name.getText()) == false) {
					username_label.setText(check.ErrorUser(user_name.getText()));
					username_label.setVisible(true);
				} else {
					username_label.setVisible(false);
				}

				if (address_field.getText().equals("")) {
					address_label.setText("Required");
					address_label.setVisible(true);
				} else {
					address_label.setVisible(false);
				}
			}
		}
	}

	@FXML
	void savapass(ActionEvent event) {
		Alert alert1 = new Alert(AlertType.CONFIRMATION);
		alert1.setTitle("Change password");
		alert1.setHeaderText("Are you sure want change password?");
		// option != null.
		Optional<ButtonType> option = alert1.showAndWait();
		if (option.get() == ButtonType.OK) {
			boolean checkpass = true;
			boolean updatepass = true;
			bo_employee Bo_Em = new bo_employee();
			checkpass = Bo_Em.checkPass(IDEM, old_pass.getText());
			System.out.print(checkpass);
			if (checkpass == true && check.checkFpass(new_pass.getText()) && check.checkFpass(confirm_pass.getText())
					&& (new_pass.getText()).equals(confirm_pass.getText())) {
				updatepass = Bo_Em.updatePass(IDEM, new_pass.getText());
				if (updatepass == true) {
					alert.Success("Change password");
					clean();
				}
			} else {
				alert.Error();
				if (checkpass == false) {
					pass_label.setText("Password not found!");
					pass_label.setVisible(true);
				} else {
					pass_label.setVisible(false);
				}
				if ((new_pass.getText()).equals(confirm_pass.getText())) {
					confirmpass_label.setText("Password not matching");
					confirmpass_label.setVisible(true);
				} else {
					confirmpass_label.setVisible(false);
				}
				if (check.checkFpass(new_pass.getText()) == false) {
					newpass_label.setText(check.ErrorPass(new_pass.getText()));
					newpass_label.setVisible(true);
				} else {
					newpass_label.setVisible(false);
				}
				if (check.checkFpass(confirm_pass.getText()) == false) {
					confirmpass_label.setText(check.ErrorPass(confirm_pass.getText()));
					confirmpass_label.setVisible(true);
				} else {
					confirmpass_label.setVisible(false);
				}
			}
		}
	}

	public void clean() {
		confirm_pass.setText("");
		new_pass.setText("");
		old_pass.setText("");
//		address_field.setText("");
//		user_name.setText("");
//		phone.setText("");
//		gmail.setText("");
//		descip_field.setText("");
//		DOB.setValue(LocalDate.now());
//		first_name.setText("");
//		middle_name.setText("");
//		last_name.setText("");
//		ImageView imgImageView1 = new ImageView();
//
//		// demo addimage
//		if(imagepath.equals("")) {
//		Image image1 = new Image("./com/hrm/assets/avatar/avatarnul.png");
//		imgImageView1.setImage(image1);
//		imgImageView1.setFitWidth(150);
//		imgImageView1.setFitHeight(150);
//		imgImageView1.scaleXProperty();
//		imgImageView1.scaleYProperty();
//		imgImageView1.setSmooth(true);
//		imgImageView1.setCache(true);
//		image_pane.setCenter(imgImageView1);
//		}else {
//			Image image1 = new Image(imagepath);
//			imgImageView1.setImage(image1);
//			imgImageView1.setFitWidth(150);
//			imgImageView1.setFitHeight(150);
//			imgImageView1.scaleXProperty();
//			imgImageView1.scaleYProperty();
//			imgImageView1.setSmooth(true);
//			imgImageView1.setCache(true);
//			image_pane.setCenter(imgImageView1);
//		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO Auto-generated method stub
		text_pass.setText("Minimum 8 digits\r\n" + "* Atleast 1 upper case letters (A - Z)\r\n"
				+ "* Atleast 1 Lower case letters (a - z)\r\n" + "* Atleast 1 number (0-9)\r\n"
				+ "* Atleast 1 non-alphanumeric symbol (e.g. @Z$%£!')");
		old_pass.setSkin(new VisiblePasswordFieldSkin(old_pass));
		new_pass.setSkin(new VisiblePasswordFieldSkin(new_pass));
		confirm_pass.setSkin(new VisiblePasswordFieldSkin(confirm_pass));
		ArrayList<employee> getProfile = null;
		DateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		try {
			getProfile = bo_employee.getProfile(usersession.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (employee EE : getProfile) {
			IDEM = EE.getId();
			address_field.setText(EE.getAddress());
			user_name.setText(EE.getUsername());
			phone.setText(EE.getTelephone());
			gmail.setText(EE.getEmail());
			descip_field.setText(EE.getDescription());
			DOB.setValue(formatDate(String.valueOf(EE.getDob())));
			first_name.setText(EE.getFirst_name());
			middle_name.setText(EE.getMiddle_name());
			last_name.setText(EE.getLast_name());
			if (EE.getAvatar() == null || EE.getAvatar().equals("")) {
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
			} else {
				ImageView imgImageView1 = new ImageView();
				oldimageString = EE.getAvatar();
				// demo addimage
				Image image1 = new Image(EE.getAvatar());
				imgImageView1.setImage(image1);
				imgImageView1.setFitWidth(150);
				imgImageView1.setFitHeight(150);
				imgImageView1.scaleXProperty();
				imgImageView1.scaleYProperty();
				imgImageView1.setSmooth(true);
				imgImageView1.setCache(true);
				image_pane.setCenter(imgImageView1);
			}
		}
	}

	private LocalDate formatDate(String string) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(string, formatter);
		return localDate;
	}

}
