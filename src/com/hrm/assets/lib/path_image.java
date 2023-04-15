package com.hrm.assets.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class path_image {
	private static String lineString = "";

	public path_image() {
		// TODO Auto-generated constructor stub
	}

	public static String pathString() throws IOException {

		String url = "../hrm_project_main/Library_jar/imagesave.txt";

		// Đọc dữ liệu từ File với File và FileReader
		File file = new File(url);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		try {
			String line = reader.readLine();
			lineString = line;
		} catch (FileNotFoundException ex) {
			Logger.getLogger(path_image.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(path_image.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				reader.close();
				// file.close();
			} catch (IOException ex) {
				Logger.getLogger(path_image.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return lineString;
	}

}
