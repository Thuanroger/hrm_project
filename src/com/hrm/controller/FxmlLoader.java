package com.hrm.controller;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class FxmlLoader {
	private AnchorPane view;

	public FxmlLoader() {
		// TODO Auto-generated constructor stub
	}

	public AnchorPane getPane(String fileString) {
		try {
			URL fileURl = this.getClass().getResource("../view/" + fileString + ".fxml");
			if (fileString == null) {
				throw new java.io.FileNotFoundException("FXML file can't be found");
			}
			new FXMLLoader();
			view = FXMLLoader.load(fileURl);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return view;
	}

}
