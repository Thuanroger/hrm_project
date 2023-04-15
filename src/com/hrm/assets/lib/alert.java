package com.hrm.assets.lib;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class alert {
	public alert() {
		// TODO Auto-generated constructor stub
	}

//	 public static  void UpdateAlert() {
//
//	      Alert alert = new Alert(AlertType.CONFIRMATION);
//	      alert.setTitle("Delete File");
//	      alert.setHeaderText("Are you sure want to Update?");
////	      alert.setContentText("C:/MyFile.txt");
//
//	      // option != null.
//	      Optional<ButtonType> option = alert.showAndWait();
//
//	      if (option.get() == null) {
//	         this.label.setText("No selection!");
//	      } else if (option.get() == ButtonType.OK) {
//	         this.label.setText("File deleted!");
//	      } else if (option.get() == ButtonType.CANCEL) {
//	         this.label.setText("Cancelled!");
//	      } else {
//	         this.label.setText("-");
//	      }
//	   }
	public static void UpdateSsuccess() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		// alert.setHeaderText("Results:");
		alert.setContentText("Update successfully!");
		alert.showAndWait();
	}

	public static void Success(String string) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		// alert.setHeaderText("Results:");
		alert.setContentText(string + " successfully!");
		alert.showAndWait();
	}

	public static void Error() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error alert");
		alert.setHeaderText("Can not add user");
		alert.setContentText(" does not exists!");
		alert.showAndWait();

	}

	public static void Decentralization() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning alert");
		alert.setHeaderText("You don't have access!");
		// Set center alignment for OK button
		alert.setOnShown(event -> {
			Node okButton = alert.getDialogPane().lookupButton(ButtonType.OK);
			okButton.setStyle("-fx-alignment: center;");
		});
		alert.showAndWait();
	}

}
